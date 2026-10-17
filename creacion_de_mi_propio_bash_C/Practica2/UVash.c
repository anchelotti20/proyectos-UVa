//VERSION DEFINITIVA, FUNCIONA 
//
//
//Hecho por Ángel Antolín Caramazana, Grupo J2
//
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include<unistd.h>
#include<sys/wait.h>


#define MAXARGS 128  //Tamanio maximo del vector de argumentos
#define MAXPARALELOS 10 //Maximo de paralelos a la vez


/*Array para guardar los argumentos, externa para no tener
 *que pasarla todo el rato a todas las funciones
 */

char *comandos_paralelos[MAXPARALELOS];
char *comandos[MAXARGS];
char error_message[30] = "An error has occurred\n"; //Mensaje de error comun en todo

int  parseador(char *cadena, char* delimitador, FILE **salida_redirigir); //Funcion para parsear el comando
int built_in();		//Funcion que llama a las builtins
void comando_cd();	//Ejecuta el comando cd Built-In
int separar_paralelos(char *cadena);

int main(int argc, char**argv){

	FILE *fd; 		//Guarda ya sea la linea de comandos o el archivo batch
	char *buffer = NULL; 			//Donde se guardan las lineas del getline
	size_t tamanio = 0;
	size_t caracteres;			//Caracteres leidos por getline
	__pid_t hijo[MAXPARALELOS];				//Hijo que crea el fork
	char *espacio_tab = " \t";		//Delimitadores para el strsep
	char *aux = NULL;			//Para trabajar con la linea leida
	int ejecutar_builtin = 0;		//Guarda si se ha ejecutado un builtin
	int estado;				//Control de error del exec
	int redirigir = 0;			//Controla si hay redireccion o no
	FILE *salida;				//Archivo salida en caso de redirigir
	int paralelos = 0;			//Controla si hay varios comandos a la vez

	//Si hay mas de un argumento, error
	if(argc > 2){		

		fprintf(stderr, "%s", error_message);
		exit(1);
	}

	//Guarda la entrada estandar, si hay un archivo lo cambia por el archivo
	fd = stdin;

	if(argc == 2){

		fd = fopen(argv[1], "r");
		
		if(fd == NULL){

			fprintf(stderr, "%s", error_message);
			exit(1);
		
		}

	}

	//Bucle principal
	while(1){
		
		//Si esta modo interactivo, imprime el prompt
		if(argc == 1){

			printf("UVash> ");	
		
		}

		/**
		 * Guarda la linea, en caso de error o final de archivo, termina
		 * Elimina el caracter de nueva linea para evitar problemas
		*/
		 caracteres = getline(&buffer, &tamanio, fd);
	
		if(caracteres == -1){


			exit(0);
		}else{

			if(buffer[caracteres - 1] == '\n'){

				buffer[caracteres - 1] = '\0';
			}
		}
		
		//Utiliza aux para poder liberar bien buffer despues,
		//Llama a parseador para sacar los argumentos y comprueba que no es builtin
		aux = buffer;
		
		paralelos = separar_paralelos(aux);
		
		//Si separar_paralelos devuelve 0, es que no habia ningun comando, error
		if(paralelos != 0){
			
			//Ejecuta el parseador y el fork, por cada comando que habia
			for(int i = 0; i < paralelos; i++){

				//Parsea cada comando devueltop por separar_paralelos
				redirigir = parseador(comandos_paralelos[i], espacio_tab, &salida);
				
				//Si hay error en la sintaxis, error
				if(redirigir != -1){

					ejecutar_builtin = built_in();
		
					//Si era un builtin, no ejecuta el exec
					if(ejecutar_builtin == 0){
			
						//Crea el proceso hijo para ejecutar el comando
						hijo[i] = fork();
				
						if(hijo[i] == -1){
				
							//Error al crear el proceso hijo
							fprintf(stderr, "%s", error_message);


						}else if(hijo[i] == 0){
					
							if(redirigir == 1){

								dup2(fileno(salida),1);
        	                                		dup2(fileno(salida),2);
	                                        		fclose(salida);

							}

							//Ejecuta el comando e informa del error si ocurre
							estado = execvp(comandos[0], comandos);
			
							if(estado == -1){

								fprintf(stderr, "%s", error_message);
								exit(1);
							}	
						
						}//If fork

					}//If de builtin
	
				}else{
					//Error sintaxis de redirigir
					fprintf(stderr, "%s", error_message);

				}
		
			
			
			}//Fin del for
			
			//Espera a que termine el proceso hijo, fuera del bucle para que se ejecuten en paralelo y no en serie
			for(int j = 0; j < paralelos; j ++){
				
				//Si el pid es postivo, hijo valido, lo espera
				if(hijo[j] > 0){
					
					waitpid(hijo[j], NULL, 0);

				}
			}
		
		//Escirbe el error si se trata de un & y nada mas, en caso de dar al enter directamente o meter varios espacios sin nada mas, no escribe error
		}else if((caracteres > 1) && (aux[0] != ' ')){
			

			//Error sintaxis de paralelos
                        fprintf(stderr, "%s", error_message);

		}	
		
		//En cualquier caso, reinicia el buffer y el array de comandos
		for(int i = 0; i < MAXPARALELOS; i++){

			comandos_paralelos[i] = NULL;
		}

		free(aux);
                tamanio = 0;
                ejecutar_builtin = 0;

	}//Fin del while



}


//Parsea los argumentos y los guarda en el array
int  parseador(char *cadena, char* delimitador, FILE **salida_redirigir){
	
	char *argumentos;
	int redireccion = 0;
	int archivo_salida = 0;
	int cantidad_simbolos = 0;	
	
	//Reinicia el array de argumentos para evitar que en caso de paralelos queden residuos de los comandos anteriores
	for(int i = 0; i < 128; i++){
		
		comandos[i] = NULL;
	}



	for(int i = 0; (argumentos = strsep(&cadena, delimitador)); i++){
		
		//Si hay varios delimitadores seguidos, devuelve \0, los ignoro
		if((strcmp(argumentos, "\0")) != 0){
			
			//Encuentra la primera aparicion del caracter de redireccion
			if(strchr(argumentos, '>')){
				
				//Cuenta el numero de caracteres que hay seguidos
				cantidad_simbolos = strspn(argumentos, ">");
				
				//Si hay mas de uno, error
				if(cantidad_simbolos > 1){
					
					redireccion = -1;
					return redireccion;
				}

				//Si estaban separados por espacios o tabulador, tambien
				//detecta el error
				if(redireccion == 1){

					redireccion = -1;
					return redireccion;
				}
				
				redireccion = 1;

			//Encuentra el archivo de salida			
			}else if(redireccion == 1){
				
				//Si ya habia uno, error
				if(archivo_salida == 1){

					archivo_salida = -1;
					return archivo_salida;

				}
			
				archivo_salida = 1;
				
				//Abre el archivo de salida, si hay error devuelve -1
				*salida_redirigir = fopen(argumentos, "w");
				
				if(*salida_redirigir == NULL){

					return -1;
				}

			//El resto de cosas, las guarda normal
			}else{

				comandos[i] = argumentos;
			
			}
		}
	}	

	//Si encontro > pero no habia archivo, error	
	if((redireccion == 1) && (archivo_salida == 0)){

		return -1;
	
	//Si no habia comando, error
	}else if (comandos[0] == NULL){
		
		return -1;
	
	//Resto de casos, normal
	}else{
		
		return redireccion;
	}

}


//Comprueba si hay comandos en paralelo
int separar_paralelos(char *cadena){
	
	char *argumentos;
	char *delimitador = "&";
	int total = 0;
	

	//Separa el input en los comandos separados por &
	for(int i = 0; (argumentos = strsep(&cadena, delimitador)); i++){
		
		//Elimina los espacios o tabuladores al inicio, dan problemas
		while((*argumentos == ' ') || (*argumentos == '\t')){

			argumentos ++;
		}
		
		//Evita las cadenas vacias cuando solo hay &
		if((strlen(argumentos) > 0) && (argumentos[0] != '\0')){


			//Si habia algo, lo guarda y aumenta el contador
			comandos_paralelos[i] = argumentos;
			total++;
	
		
		}
	
	}
	return total;	

}


//Comprueba si el comando parseado es alguno de los Built-In
int built_in(){

	int ejecucion = 0;
	

	if((strcmp(comandos[0], "exit")) == 0){
		
		if(comandos[1] != NULL){
			
			fprintf(stderr, "%s", error_message);
			ejecucion = 1;
		}else{


			exit(0);
	
		}

	}else if((strcmp(comandos[0], "cd")) == 0){
		
		ejecucion = 1;
		
		if((comandos[1] == NULL) || (comandos[2] != NULL)){

			fprintf(stderr, "%s", error_message);
		}else{

			comando_cd();

		}
	}

	return ejecucion;


}

//Ejecuta el comando cd
void comando_cd(){
	
	if((chdir(comandos[1]) == -1)){

		fprintf(stderr, "%s", error_message);
	
	}


}



