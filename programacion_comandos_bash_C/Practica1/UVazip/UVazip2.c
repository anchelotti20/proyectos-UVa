#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h>


int main(int argc, char**argv){


	FILE * archivos;
	char * linea = NULL;
	size_t num_caracteres = 0;
	size_t longitud;
	int contador;
	char caracter;
	bool salir;
	char caracterViejo;

	if(argc == 1){

		printf("UVazip: file1 [file2 ...]\n");
		exit(1);
	}


	for(int i = 1; i < argc; i ++){

	
		archivos = fopen(argv[i], "r");
		if(archivos == NULL){

			printf("UVazip: cannot open file\n");
			exit(1);
		}
		
		caracter = fgetc(archivos);

		while(salir == false){

			num_caracteres = getline(&linea, &longitud, archivos);

			if(num_caracteres != -1){

			
				for(int j = 0; j < num_caracteres; j++){
					
					if(caracterViejo != caracter){


						caracter = linea[j];
						contador = 1;
					}

					while((j + 1 < num_caracteres) && (linea[j + 1] == caracter)){

						contador++;
						j++;


					}
					
					if(caracter != '\n'){
						
						caracterViejo = caracter;
						fwrite(&contador, sizeof(int), 1, stdout);
					}

					printf("%c", caracter);
				
				
				}	
				


			}else{

				salir = true;
				fclose(archivos);


			}

		}


	}

	free(linea);
	return (0);
	


}	
