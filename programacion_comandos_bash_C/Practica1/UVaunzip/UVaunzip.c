#include<stdio.h>
#include<stdlib.h>
#include<string.h>


#define BYTES 4

int main(int argc, char**argv){


	FILE *archivos;
	int numero;
	char caracter;
	size_t leerNum;
	size_t leerCar;
	char caracterViejo;
	int contador = 0;

	if(argc == 1){

		printf("UVaunzip: file1 [file2 ...]\n");
                exit(1);
        }

	for (int i = 1; i < argc; i++){

		archivos = fopen(argv[i], "r");
		if(archivos == NULL){

			printf("UVaunzip: cannot open file\n");
                        exit(1);
		}
		
		while((leerNum = fread(&numero, BYTES, 1, archivos)) == 1){

			while((leerCar = fread(&caracter, 1, 1, archivos)) == 1){
				
				contador ++;

				for(int i = 0; i < numero; i++){

					printf("%c", caracter);

				}
				if((caracterViejo != caracter) && (contador > 1)){

					printf("\n");
			
				}
				
				caracterViejo = caracter;
			}
		}

		fclose(archivos);

	}
	
	return(0);
}
		
