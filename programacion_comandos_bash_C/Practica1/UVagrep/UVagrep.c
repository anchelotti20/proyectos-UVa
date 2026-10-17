#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int main(int argc, char**argv){
	

	FILE *archivos;
	char * linea = NULL;
	size_t num_caracteres = 0;
	char * termino = NULL;
	size_t longitud = 0;


	if(argc == 1){

		printf("UVagrep: searchterm [file ...]\n");
		exit(1);
	}
	
	if((termino = (char *) malloc(sizeof(char) * strlen(argv[1]) + 1)) == NULL){

		printf("UVagrep: memory error\n");
		exit(0);
	}

	strcpy(termino, argv[1]);	

	
	if(strlen(termino) > 0){

		
		 if(argc > 2){
		
			for(int i = 2; i < argc; i++){

				archivos = fopen(argv[i], "r");
				if(archivos == NULL){

					printf("UVagrep: cannot open file\n");
					exit(1);
				}	
			
				while((num_caracteres = getline(&linea, &longitud, archivos)) != -1){
				
					if(strstr(linea, termino) != NULL){

						printf("%s", linea);
					}


				}	

				fclose(archivos);

			}


		}else{
			
			while((num_caracteres = getline(&linea, &longitud, stdin)) != -1){

				if(strstr(linea, termino) != NULL){
					printf("%s", linea);
			
				}
			}

		}	
	
	}
	free(termino);
	free(linea);
	return(0);




}
