/**Hito 3 de la practica, programa principal que llama con exec a procesa, además genera una serie de hilos productores y consumidores
 * que realizan una serie de cálculos con lo obtenido en procesa
 *Realizado por Ángel Antolín Caramazana y Marcos Gómez Medina, Grupo J4
 *11-12-2024 
*/
#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include<string.h>
#include<sys/wait.h>
#include<unistd.h>
#include<pthread.h>
#include<semaphore.h>
#include<math.h>



#define ARGS 5

typedef struct nodo{        //Estructura que modela la lista enlazada
   
    long suma;
    int id;
    struct nodo* sig;

}Nodo;


typedef struct {            //El bufer guarda tanto la linea como el tamaño de la linea

    int tamanio;
    char linea[33];
    sem_t sobreescribir;

}Bufer;

typedef struct{         //Parametros a pasar al productor

    int tambufer;
    FILE* archivo;
    int* productor_termina;
    int* elementos_buffer;

}param_productor;

typedef struct{         //Parametros a pasar a los consumidores

    int tambufer;
    int id;
    Nodo** lista;
    int* productor_termina;
    int nhilos;
    int* elementos_buffer;
    int* consumidores_esperando;
    int* hilos_dormidos;

}param_consumidores;

typedef struct{         //Parametros del hilo sumador

    FILE* archivo;
    Nodo** lista;
    int nhilos;

}param_sumador;



Bufer* buffer = NULL;          //Variables compartidas por todos los hilos
sem_t hay_hueco;
sem_t hay_dato;
sem_t mutex_elementos;
sem_t mutex_consumidores;
sem_t mutex_terminar;
sem_t termina_consumidor;
sem_t mutex_lista;
sem_t* esperar_consumir = NULL;
sem_t mutex_despertar;
sem_t mutex_dormidos;

int sig_vaciar = 0;
int sig_llenar = 0;



void* funcionProductor(void *arg);
void* funcionConsumidores(void * arg);
void* funcionSumador (void * args);

bool esBinario(char str[], int tamanio);
int binarioADecimal(char str[]);
Nodo* insertarFinal (Nodo* cabeza, int identificador, int suma);
bool comprobarVacio (int tambuffer);

int main(int argc, char**argv){
    
    

    FILE *arc_entrada, *arc_salida, *arc_resultados;
    __pid_t pid;

    char *archivo = "./procesa";

    char *lista_argumentos[] = {"./procesa", argv[1], argv[2], NULL};
    int estado, estado_hijo, codigo_salida, nhilos, tbufer;

    pthread_t productor;
    pthread_t *consumidores;
    pthread_t sumador;

    Nodo *lista_resultados = NULL;
 
    int *identificador = NULL;
    int productor_termina = 0;
    int elementos_buffer = 0;
    int hilos_dormidos = 0;

    int consumidores_esperando[nhilos];


    param_productor args_productor;
    param_consumidores* args_consumidores;
    param_sumador args_sumador;

    if (argc != ARGS + 1){      //Comprobacion del numero de argumentos sea valido

        fprintf(stderr, "main: El numero de argumentos no es valido\n");
        exit(1);

    }
    
    if ((nhilos = atoi(argv[3])) < 1 || (nhilos = atoi(argv[3])) > 1000){

        fprintf(stderr, "main: El número de hilos debe estar entre 2 y 1000 inclusive\n");
        exit(7);


    }

    if ((tbufer = atoi(argv[4])) < 10 || (tbufer = atoi(argv[4])) > 1000){

        fprintf(stderr, "main: El tamaño del bufer debe de estar entre 10 y 1000 inclusive\n");
        exit(8);


    }


    if ((pid = fork()) == -1){              //Crea el proceso hijo que ejecutara procesa

        fprintf(stderr, "main: Error al crear el proceso hijo\n");
        exit(2);


    }else if (pid == 0){

        estado = execvp(archivo, lista_argumentos);         //El proceso hijo ejecuta procesa, pasando como argumentos los archivos de entrada y salida

        if (estado == -1){

            fprintf(stderr, "main: Error al ejecutar el archivo procesa\n");
            exit(3);
        }

    }else{

        wait(&estado_hijo);                                 //El padre espera a que acabe el hijo y guarda el estado de salida que devolvió el hijo
        codigo_salida = WEXITSTATUS(estado_hijo);

        if (codigo_salida == 1){

            fprintf(stderr, "main: Número de argumentos érroneo en procesa\n");
            exit(4);
                                                                                                //Mensajes para cada posible codigo de error
        }else if(codigo_salida == 2){

            fprintf(stderr, "main: Error al abrir el archivo de entrada en procesa\n");
            exit(5);

        }else if (codigo_salida == 3){

            fprintf(stderr, "main: Error al crear el archivo de salida en procesa\n");
            exit(6);

        }else{                                                                          //Mensaje en caso de que no hubiera errores


            fprintf(stdout,"main: Procesado de fichero terminado.\n");
        }
        

    }

    if((arc_salida = fopen(argv[2], "r")) == NULL){                                 //Abre el archivo procesado para pasárselo al productor

        fprintf(stderr, "main: Error al abrir el archivo procesado\n");
        exit(9);

    }

    if((arc_resultados = fopen(argv[5], "w")) == NULL){

        fprintf(stderr, "main: Error al abrir el archivo donde se van a escribir los resultados\n");
        exit(9);
    }


    if ((buffer = (Bufer *) malloc(sizeof(Bufer) * tbufer)) == NULL){                     //Reservar memoria para los distintos arrays

        fprintf(stderr, "main: Error al asignar memoria\n");
        exit(10);

    }

    
    if ((identificador = (int *) malloc(sizeof(int) * nhilos)) == NULL){            //Array de identificadores


        fprintf(stderr, "main: Error al asignar memoria\n");
        exit(10);
    }

    if ((consumidores = (pthread_t *) malloc(sizeof(pthread_t) * nhilos)) == NULL){

        fprintf(stderr, "main: Error al asignar memoria\n");
        exit(10);

    }

    if ((args_consumidores = (param_consumidores *) malloc(sizeof(param_consumidores) * nhilos)) == NULL){

        fprintf(stderr, "main: Error al asignar memoria\n");
        exit(10);

    }


    if((esperar_consumir = (sem_t*) malloc(sizeof(sem_t) * nhilos)) == NULL){

        fprintf(stderr, "main: Error al asignar memoria\n");
        exit(10);

    }
    
    sem_init(&hay_hueco, 0, tbufer);            //Inicializar semaforos
    sem_init(&hay_dato, 0, 0);
    sem_init(&mutex_elementos, 0, 1);
    sem_init(&mutex_consumidores, 0, 1);
    sem_init(&mutex_terminar, 0, 1);
    sem_init(&termina_consumidor, 0, 0);
    sem_init(&mutex_lista, 0, 1);
    sem_init(&mutex_despertar, 0, 1);
    sem_init(&mutex_dormidos, 0, 1);

    for(int i = 0; i < tbufer; i++){

        sem_init(&buffer[i].sobreescribir, 0, 1);
    }

    for(int i = 0; i < nhilos; i++){

        sem_init(&esperar_consumir[i], 0, 1);
        consumidores_esperando[i] = -1;

    }
   

    

    args_productor.archivo = arc_salida;                            //Inicializar parametros productor y lanzar hilo
    args_productor.tambufer = tbufer;
    args_productor.productor_termina = &productor_termina;
    args_productor.elementos_buffer = &elementos_buffer;
    

    pthread_create(&productor, NULL, funcionProductor, (void *) &args_productor);

     for (int i = 0; i < nhilos; i++){               //Guardar en el array un identificador unico para cada hilo consumidor

        identificador[i] = i;
        args_consumidores[i].id = identificador[i];
        args_consumidores[i].nhilos = nhilos;
        args_consumidores[i].productor_termina = &productor_termina;
        args_consumidores[i].lista = &lista_resultados;
        args_consumidores[i].tambufer = tbufer;
        args_consumidores[i].elementos_buffer = &elementos_buffer;
        args_consumidores[i].consumidores_esperando = consumidores_esperando;
        args_consumidores[i].hilos_dormidos = &hilos_dormidos;

        pthread_create(&consumidores[i], NULL, funcionConsumidores, (void *) &args_consumidores[i]);
    }


    args_sumador.archivo = arc_resultados;
    args_sumador.lista = &lista_resultados;
    args_sumador.nhilos = nhilos;

    pthread_create(&sumador, NULL, funcionSumador, (void *) &args_sumador);


    pthread_join(productor, NULL);                                      //Esperar a que terminen todos los hilos

    for(int i = 0; i < nhilos; i++){

        pthread_join(consumidores[i], NULL);

    }

    pthread_join(sumador, NULL);


    sem_destroy(&hay_dato);
    sem_destroy(&hay_hueco);
    sem_destroy(&mutex_consumidores);
    sem_destroy(&mutex_elementos);
    sem_destroy(&mutex_terminar);
    sem_destroy(&termina_consumidor);
    sem_destroy(&mutex_lista);
    sem_destroy(&mutex_despertar);
    sem_destroy(&mutex_dormidos);

    for(int i = 0; i < tbufer; i++){

        sem_destroy(&buffer[i].sobreescribir);
    }

    for(int i = 0; i < nhilos; i++){

        sem_destroy(&esperar_consumir[i]);

    }


    
    free(buffer);
    free(identificador);
    free(consumidores);
    free(args_consumidores);
    free(esperar_consumir);    
    
    fclose(arc_salida);
    fclose(arc_resultados);
    exit(0);


}

void * funcionProductor (void * arg){

    
    param_productor * p;
    char * linea = NULL;
    size_t num_caracteres = 0;
    p = (param_productor *) arg;
    int temp = 0;
    size_t len;
    
    while((num_caracteres = getline(&linea, &len, p ->archivo)) != -1){

        

        if (num_caracteres >= 1 && num_caracteres <= 33){

            if (esBinario(linea, num_caracteres)){
                
                sem_wait(&hay_hueco);
                
                temp = sig_llenar;

                sig_llenar = (sig_llenar + 1) % (p-> tambufer);

                sem_wait(&buffer[temp].sobreescribir);                
                
                strcpy(buffer[temp].linea, linea);
                buffer[temp].tamanio = strlen(linea);

                sem_wait(&mutex_elementos);
                (*(p -> elementos_buffer))++;
                sem_post(&mutex_elementos);

                sem_post(&hay_dato);

            }
        }    
    }

    free(linea);
    
    sem_wait(&mutex_terminar);
    *(p -> productor_termina) = 1;
    sem_post(&mutex_terminar);


    pthread_exit(NULL);

}




void * funcionConsumidores (void * arg){
    param_consumidores * c;
    c = (param_consumidores *) arg;
    int temp = 0;
    char linea [33];
    long suma = 0;
    int tamanio = 0;
    
    
    sem_wait(&mutex_terminar);
    while(*(c -> productor_termina) == 0 || *(c->elementos_buffer) > 0){

        sem_post(&mutex_terminar);

        sem_wait(&esperar_consumir[c->id]);

        sem_wait(&hay_dato);

        sem_wait(&mutex_terminar);
        
        if(*(c -> productor_termina) == 0 || *(c->elementos_buffer) > 0){
            
            sem_post(&mutex_terminar);

            sem_wait(&mutex_consumidores);
            temp = sig_vaciar;
            sig_vaciar = (sig_vaciar + 1) % (c -> tambufer);
            sem_post(&mutex_consumidores);

            strcpy(linea, buffer[temp].linea);
            tamanio = buffer[temp].tamanio;
            
            if(tamanio == 33 && linea[0] == '0'){
            
                if(((c->id % 2) == 0 && linea[tamanio - 2] == '0') || ((c->id % 2) != 0 && linea[tamanio - 2] == '1')){
                    
                    buffer[temp].tamanio = 0;

                    sem_wait(&mutex_elementos);
                    (*(c->elementos_buffer))--;
                    sem_post(&mutex_elementos);

                    sem_post(&esperar_consumir[c->id]);

                    suma = (suma + binarioADecimal(linea)) % (RAND_MAX / 2);
                    
                    
                    for(int i = 0; i < c->nhilos; i++){

                        sem_wait(&mutex_despertar);
                        if(c->consumidores_esperando[i] != -1){
                            sem_post(&esperar_consumir[i]);

                            c->consumidores_esperando[i] = -1;
                            

                            sem_wait(&mutex_dormidos);
                            (*(c-> hilos_dormidos)) --;
                            sem_post(&mutex_dormidos);

                            
                        }
                        
                        sem_post(&mutex_despertar);
                        

                    }

                    
                    sem_post(&hay_hueco);
                    sem_post(&buffer[temp].sobreescribir);
                
                }else{

                    sem_post(&hay_dato);
                    sem_wait(&mutex_despertar);
                    
                    c->consumidores_esperando[c->id] = c->id;
                    
                    sem_post(&mutex_despertar);

                    sem_wait(&mutex_dormidos);
                    (*(c-> hilos_dormidos)) ++;
                    sem_post(&mutex_dormidos);

                    
                    sem_wait(&mutex_dormidos);
                    if(*(c->hilos_dormidos) == c->nhilos){
                        
                        sem_post(&mutex_dormidos);
                        sem_post(&esperar_consumir[c->id]);
                        
                        sem_wait(&mutex_despertar);
                    
                        c->consumidores_esperando[c->id] = -1;
                    
                        sem_post(&mutex_despertar);

                        sem_wait(&mutex_dormidos);
                        (*(c->hilos_dormidos)) --;
                        sem_post(&mutex_dormidos);

                    }else{
                        sem_post(&mutex_dormidos);
                        
                    }
                    
                }
                
            }else{
                

                buffer[temp].tamanio = 0;
                
                if(tamanio != 0){
                    
                    sem_wait(&mutex_elementos);
                    (*(c->elementos_buffer))--;
                    sem_post(&mutex_elementos);


                }else{
                    
                    if(comprobarVacio(c-> tambufer) == true){

                        sem_wait(&mutex_elementos);
                        *(c->elementos_buffer) = 0;
                        sem_post(&mutex_elementos);

                    }

                    sem_wait(&mutex_terminar);
                    if(*(c->productor_termina) == 0 && comprobarVacio(c-> tambufer) == false){ 
                        
                        sem_post(&mutex_terminar);
                        sem_post(&hay_dato);
                    
                    }else{

                        sem_post(&mutex_terminar);

                    }
                }
            
                sem_wait(&mutex_terminar);
                if(*(c->productor_termina) == 1){

                    sem_post(&mutex_terminar);
                    
                    sem_post(&hay_dato);

                }else{
                    
                    sem_post(&mutex_terminar);
                }


                sem_post(&esperar_consumir[c->id]);

                sem_post(&hay_hueco);
                sem_post(&buffer[temp].sobreescribir);

            }

           
        }else{

            sem_post(&mutex_terminar);
        }

    }

    sem_post(&hay_dato);

    for(int i = 0; i < c->nhilos; i++){

        sem_wait(&mutex_despertar);
        if(c->consumidores_esperando[i] != -1){
                                     
            sem_post(&esperar_consumir[i]);
  
            c->consumidores_esperando[i] = -1;
                            

            sem_wait(&mutex_dormidos);
            (*(c-> hilos_dormidos)) --;
            sem_post(&mutex_dormidos);

        }

        sem_post(&mutex_despertar);
                
    }
    
    sem_wait(&mutex_lista);

    *(c->lista) = insertarFinal(*c->lista, c->id, suma);


    sem_post(&mutex_lista);


    sem_post(&termina_consumidor);
    
    pthread_exit(NULL);

}

void * funcionSumador (void * args){
    
    param_sumador * s;
    s = (param_sumador *) args;
    int hilos_terminados = 0;
    long suma_total = 0;

    while (hilos_terminados < s -> nhilos){

        sem_wait(&termina_consumidor);

        hilos_terminados ++;

        sem_wait(&mutex_lista);

        suma_total = (suma_total + (*s->lista)->suma) % (RAND_MAX / 2);

        fprintf(s->archivo, "Hilo %d suma parcial: %ld\n", (*s->lista)->id, (*s->lista)->suma);

        *(s->lista) = (*s->lista)->sig; 

        sem_post(&mutex_lista);

    }

    fprintf(s->archivo, "Suma total: %ld\n", suma_total);


    pthread_exit(NULL);


}

bool esBinario (char str[], int tamanio){

    bool binario = true;

    for (int i = 0; i < tamanio - 1; i++){

        
        if (str[i] != '1' && str[i] != '0'){
            
            binario = false;

        }


    }
    return binario;
}

int binarioADecimal (char str[]){

    int decimal = 0;

    for (int i = 0; i < 32; i++){

        if (str[i] == '1'){

            decimal += pow(2, 32 - i - 1);

        }

    }

    return decimal;

}


Nodo* insertarFinal (Nodo* cabeza, int identificador, int suma) {
    
    Nodo* nuevoNodo = (Nodo*) malloc (sizeof(Nodo)) ;
    
    if (nuevoNodo == NULL) {
        fprintf (stderr , "Error al asignar memoria para los nodos.\n") ;
        exit (11);
    }
    
    nuevoNodo -> id = identificador;
    nuevoNodo -> suma = suma;
    nuevoNodo ->sig = NULL;

    if (cabeza == NULL) {
    
        return nuevoNodo;
    }
    

    Nodo* temp = cabeza;
    
    while (temp -> sig != NULL) {
        
        temp = temp -> sig;
    }
    

    temp -> sig = nuevoNodo;
    
    return cabeza;

}

bool comprobarVacio (int tambuffer){

    bool vacio = true;

    for(int i = 0; i < tambuffer; i++){
        sem_wait(&mutex_consumidores);
        if(buffer[i].tamanio != 0){
            sem_post(&mutex_consumidores);

            vacio = false;
        }else{
            sem_post(&mutex_consumidores);

        }

    }

    return vacio;

}
