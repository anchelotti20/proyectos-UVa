#include<stdio.h>
#include<stdlib.h>
#include<string.h>



int main(int argc, char**argv){


	FILE * archivos;
	char * linea = NULL;
	size_t num_caracteres = 0;
	size_t longitud;
	int contador;
	char caracter;

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
		
		while((num_caracteres = getline(&linea, &longitud, archivos)) != -1){

			for(int j = 0; j < num_caracteres; j ++){
				
				caracter = linea[j];
				contador = 1;

				while((j + 1 < num_caracteres) && (linea[j + 1] == caracter)){
					
					contador ++;
					j ++;


				}	
				
				if(caracter != '\n'){
					printf("%d", contador);			
				//	fwrite(&contador, sizeof(int), 1, stdout);
				}
				printf("%c", caracter);				
			}	

		}			
	
		fclose(archivos);
	}

	free(linea);
	return(0);


}
