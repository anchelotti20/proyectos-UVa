#include<stdio.h>
#include<stdlib.h>


#define TAM_MAX 5000

int main (int argc, char**argv){
	
	
	FILE *archivos;	
	char linea [TAM_MAX]; 
	
	if(argc == 1){
	
		exit(0);
	}

	for(int i = 1; i< argc; i++){

		archivos = fopen(argv[i], "r");

		if(archivos == NULL){
			
			printf("UVacat: no puedo abrir fichero\n");
			exit(1);
		}
		
		
		while(fgets(linea, TAM_MAX, archivos) != NULL){

			printf("%s", linea);
		}

		fclose(archivos);
	}


	return(0);

}
