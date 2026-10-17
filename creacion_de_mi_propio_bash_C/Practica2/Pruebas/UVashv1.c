//EN ESTE FUNCIONA LO BASICO CON LOS 2 BUILT-IN
//FALTAN REDIRECCIONES, PIPES.
//
//SI HAY UN ERROR LUEGO EL EXIT ES COMO QUE NO VA Y TENGO QUE DARLE VARIAS VECES
//
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include<unistd.h>
#include<sys/wait.h>


char *comandos[128] = {NULL};	/*Array para guardar los argumentos, externa para no tener
				*que pasarla todo el rato a todas las funciones
				*/
char error_message[30] = "An error has occurred\n"; //Mensaje de error comun en todo

void  parseador(char *cadena, char* delimitador); //Funcion para parsear el comando
int built_in();		//Funcion que llama a las builtins
void comando_cd();	//Ejecuta el comando cd Built-In


int main(int argc, char**argv){

	FILE *fd; 		//Guarda ya sea la linea de comandos o el archivo batch
	char *buffer = NULL; 			//Donde se guardan las lineas del getline
	size_t tamanio = 0;
	size_t caracteres;			//Caracteres leidos por getline
	__pid_t hijo;				//Hijo que crea el fork
	char *espacio_tab = " \t";		//Delimitadores para el strsep
	int estado_hijo;			//Para el wait del hijo
	char *aux = NULL;			//Para trabajar con la linea leida
	int ejecutar_builtin = 0;		//Guarda si se ha ejecutado un builtin
	int estado;

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
		parseador(aux, espacio_tab);
		ejecutar_builtin = built_in();
		
		//Si era un builtin, no ejecuta el exec
		if(ejecutar_builtin == 0){
			
			//Crea el proceso hijo para ejecutar el comando
			hijo = fork();

			if(hijo == -1){
				
				//Error al crear el proceso hijo
				fprintf(stderr, "%s", error_message);


			}else if(hijo == 0){
				
				//Ejecuta el comando e informa del error si ocurre
				estado = execvp(comandos[0], comandos);
							

				if(estado == -1){

					fprintf(stderr, "%s", error_message);
				}	
			
			}else{
			
				//Espera a que termine el proceso hijo
				wait(&estado_hijo);	
			}

		}
	
		//En cualquier caso, reinicia el buffer y el array de comandos
		for(int i = 0; i < 128; i++){

			comandos[i] = NULL;
		}
		
		free(buffer);
                tamanio = 0;
                ejecutar_builtin = 0;

	}



}


//Parsea los argumentos y los guarda en el array
void parseador(char *cadena, char* delimitador){
	
	char *argumentos;	

	for(int i = 0; (argumentos = strsep(&cadena, delimitador)); i++){

		if((strcmp(argumentos, "\0")) != 0){

			comandos[i] = argumentos;
		
		}
	}	

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



