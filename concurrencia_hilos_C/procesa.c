/**Hito 2 de la practica, procesa un archivo y devuelve en otro aquellas líneas solo numéricas
*Realizado por Ángel Antolín Caramazana y Marcos Gómez Medina, Grupo J4 
*11-12-2024 
*/
#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include<string.h>

#define ARGS 2

bool es_numerico(char str[]);

int main (int argc, char**argv){

    FILE *arc_entrada, *arc_salida;

    char* linea = NULL;
    size_t tamanio;
    size_t i;

    if (argc != ARGS + 1){      //Comprobar que el número de argumentos de entrada es correcto

        fprintf(stderr, "procesa: El correcto uso de procesa es ./procesa archivo_entrada archivo_salida\n");
        exit(1);

    }

    if ((arc_entrada = fopen(argv[1], "r")) == NULL){       //Comprobar que se abre bien el fichero de entrada

        fprintf(stderr, "procesa: Error al abrir el fichero de entrada\n");
        exit(2);


    }


    if ((arc_salida = fopen(argv[2], "w")) == NULL){          //Comprobar que se crea bien el fichero de salida

        fprintf(stderr, "procesa: Error al crear el fichero de salida\n");
        exit(3);
    }


    while ((i = getline(&linea, &tamanio, arc_entrada)) != -1){       //Recorre todas las líneas y las guarda en line

        
        if (es_numerico(linea)){                                //Si la funcion devuelve true, guarda la linea en el fichero de salida
            
            fwrite(linea, strlen(linea), 1, arc_salida);
        }
        
        

    }

    fwrite("\n", 1, 1, arc_salida);                                    //Para que todas las lineas tengan la misma longitud

    free(linea);

    fprintf(stdout, "procesa: Procesado el fichero %s. Resultado escrito en %s.\n", argv[1], argv[2]);

    fclose(arc_entrada);
    fclose(arc_salida);

    exit(0);




}

//Devuelve true si la linea introducida es completamente numérica

bool es_numerico (char str[]){
   
    long tamanio = strlen(str);

    bool solo_nums = true;

    for (int i = 0; i < tamanio - 1; i++ ){

        if (str[i] < '0' || str[i] > '9'){
            
            solo_nums = false;

        }

    }

    return solo_nums;


}