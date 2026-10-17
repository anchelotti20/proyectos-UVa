%code requires{

    #define NIVEL_BYTE 1
    #define NIVEL_SHORT 2
    #define NIVEL_INT 3
    #define NIVEL_LONG 4
    #define NIVEL_FLOAT 5
    #define NIVEL_DOUBLE 6

    typedef struct {
    
        int tipo_dato; // 0: número, 1: cadena, 2: booleano
        int valor_numerico; // Para numeros, 1: byte, 2: short, 3: int, 4: long, 5: float, 6: double
        double valor_numero;
        char valor_cadena[512];
        bool valor_booleano;
    }Valor;

}

%{
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h>

extern FILE* yyin;
extern int yylex();
extern int yyparse();
void yyerror(char* s);



typedef struct {
        char tipo[20];
        char id[201];
        char valor[512];
    } Variable;

    Variable variables[100];
    int num_variables = 0;


    bool ejecutar = true;

%}

%union{

    
    char* str;
    bool booleano;
    Valor valor;

}

%{
    void imprimir_variables();
    void actualizar_variable(char* id, Valor valor);
    void guardar_variable(char* tipo, char* id, Valor valor);
    Valor obtener_valor(char* id);

    bool es_numerica(Valor valor);
    bool son_numericas(Valor valor1, Valor valor2);
    bool son_booleanas(Valor valor1, Valor valor2);
    bool division_por_cero(Valor valor);
    bool valor_cabe(Valor valor1, Valor valor2);
    bool cabe_en_byte(Valor valorActual, Valor valorNuevo);
    bool cabe_en_short(Valor valorActual, Valor valorNuevo);
    int tipo_numerico(char* tipo);

    Valor evaluar_suma_concatenacion(Valor v1, Valor v2);    
%}

%token <valor> NUMERO
%token <str> TIPO ID CADENA
%token <booleano> BOOLEAN

%token PUBLIC_CLASS PUBLIC_STATIC_VOID_MAIN IF
%token MAS MENOS POR DIV MOD
%token MAS_IGUAL MENOS_IGUAL POR_IGUAL DIV_IGUAL MOD_IGUAL
%token MAS_MAS MENOS_MENOS
%token IGUAL_IGUAL IGUAL DIFERENTE MAYOR MENOR MAYOR_IGUAL MENOR_IGUAL
%token AND OR NOT
%token PARENTESIS_IZQ PARENTESIS_DER LLAVE_IZQ LLAVE_DER PUNTO_Y_COMA

%type <valor> expresion expresion_logica

%left OR
%left AND
%left IGUAL_IGUAL DIFERENTE
%left MAYOR MENOR MAYOR_IGUAL MENOR_IGUAL
%left MAS MENOS
%left POR DIV MOD
%right NOT MAS_MAS MENOS_MENOS

%%

programa: PUBLIC_CLASS ID LLAVE_IZQ PUBLIC_STATIC_VOID_MAIN LLAVE_IZQ sentencias LLAVE_DER LLAVE_DER
        ;

sentencias: sentencia
        | sentencias sentencia
        ;

sentencia: declaracion PUNTO_Y_COMA {imprimir_variables();}
        | asignacion PUNTO_Y_COMA {imprimir_variables();}
        | incremento_decremento PUNTO_Y_COMA {imprimir_variables();}
        | condicional
        ;

declaracion: TIPO ID IGUAL expresion {
                                        if(ejecutar){
                                            
                                            Valor valor_destino; 
                                            valor_destino.valor_numerico = tipo_numerico($1);

                                            if($4.tipo_dato == 0){

                                                if(valor_destino.valor_numerico >= $4.valor_numerico){

                                                    guardar_variable($1, $2, $4);
                                                
                                                }else if(cabe_en_byte(valor_destino, $4) || cabe_en_short(valor_destino, $4)){

                                                    guardar_variable($1, $2, $4);
                                                
                                                }else{

                                                    yyerror("Error: El valor asignado excede el rango del tipo declarado");
                                                }

                                            
                                            }else{

                                                guardar_variable($1, $2, $4);
                                            }

                                            
                                        } 
                                        
                                    }
            | TIPO ID IGUAL expresion_logica {
                                                if(ejecutar){
                                                    
                                                    if(strcmp($1, "boolean") == 0){
                                                        
                                                        guardar_variable($1, $2, $4);
                                                    
                                                    } else {
                                                        
                                                        yyerror("Error: Tipos incompatibles (se esperaba un booleano)");
                                                    }
                                                }
                                            }

asignacion: ID IGUAL expresion {
                                    if(ejecutar){

                                        Valor valor_actual = obtener_valor($1);

                                        if(valor_actual.tipo_dato == 0 && $3.tipo_dato == 0){

                                            if($3.valor_numerico <= valor_actual.valor_numerico){

                                                actualizar_variable($1, $3);
                                            
                                            }else if (cabe_en_byte(valor_actual, $3) || cabe_en_short(valor_actual, $3)){

                                                actualizar_variable($1, $3);

                                            }else{

                                                yyerror("Error: El valor asignado excede el rango del tipo declarado");
                                            }

                                        }else if(valor_actual.tipo_dato == 1 && $3.tipo_dato == 1){

                                            actualizar_variable($1, $3);
                                        
                                        }else if(valor_actual.tipo_dato == 2 && $3.tipo_dato == 2){
                                            
                                            actualizar_variable($1, $3);
                                        
                                        }else{

                                            yyerror("Error: Tipos incompatibles en la asignación");
                                        }
                                    }
                                    
                                    
                                }
        | ID IGUAL expresion_logica {if(ejecutar) actualizar_variable($1, $3);}
        | ID MAS_IGUAL expresion {
                                    if(ejecutar){

                                        Valor valor_actual = obtener_valor($1);
                                        Valor valor_a_sumar = $3;
                                        
                                        if(son_numericas(valor_actual, valor_a_sumar)){
                                            
                                            if(valor_actual.valor_numerico >= valor_a_sumar.valor_numerico || cabe_en_byte(valor_actual, valor_a_sumar) || cabe_en_short(valor_actual, valor_a_sumar)){
                                                
                                                valor_actual.valor_numero += valor_a_sumar.valor_numero;
                                                actualizar_variable($1, valor_actual);
                                                
                                            } else {
                                                
                                                yyerror("Error: El resultado de += no cabe en la variable destino");
                                            
                                            }
                                        
                                        }else if(valor_actual.tipo_dato == 1 && valor_a_sumar.tipo_dato == 1){

                                            strcat(valor_actual.valor_cadena, valor_a_sumar.valor_cadena);
                                            actualizar_variable($1, valor_actual);
                                        
                                        }else{
                                            
                                            yyerror("Error: No se pueden sumar tipos de datos diferentes");
                                        
                                        }

                                    }
                                }
        
        | ID MENOS_IGUAL expresion {
                                    if(ejecutar){

                                        Valor valor_actual = obtener_valor($1);
                                        Valor valor_a_restar = $3;

                                        if(son_numericas(valor_actual, valor_a_restar)){
                                            
                                            if(valor_actual.valor_numerico >= valor_a_restar.valor_numerico || cabe_en_byte(valor_actual, valor_a_restar) || cabe_en_short(valor_actual, valor_a_restar)){
                                                
                                                valor_actual.valor_numero -= valor_a_restar.valor_numero;
                                                actualizar_variable($1, valor_actual);
                                                
                                            } else {
                                                
                                                yyerror("Error: El resultado de -= no cabe en la variable destino");
                                            
                                            }
                                        }

                                    }
                                }
        
        | ID POR_IGUAL expresion {
                                    if(ejecutar){

                                        Valor valor_actual = obtener_valor($1);
                                        Valor valor_a_multiplicar = $3;

                                        if(son_numericas(valor_actual, valor_a_multiplicar)){
                                            
                                            if(valor_actual.valor_numerico >= valor_a_multiplicar.valor_numerico || cabe_en_byte(valor_actual, valor_a_multiplicar) || cabe_en_short(valor_actual, valor_a_multiplicar)){
                                                
                                                valor_actual.valor_numero *= valor_a_multiplicar.valor_numero;
                                                actualizar_variable($1, valor_actual);
                                                
                                            } else {
                                                
                                                yyerror("Error: El resultado de *= no cabe en la variable destino");
                                            
                                            }
                                        }

                                    }
                }
        
        | ID DIV_IGUAL expresion {
                                    if(ejecutar){

                                        Valor valor_actual = obtener_valor($1);
                                        Valor valor_a_dividir = $3;

                                        if(son_numericas(valor_actual, valor_a_dividir)){
                                            
                                            if(valor_actual.valor_numerico >= valor_a_dividir.valor_numerico || cabe_en_byte(valor_actual, valor_a_dividir) || cabe_en_short(valor_actual, valor_a_dividir)){
                                                
                                                valor_actual.valor_numero /= valor_a_dividir.valor_numero;
                                                actualizar_variable($1, valor_actual);
                                                
                                            } else {
                                                
                                                yyerror("Error: El resultado de /= no cabe en la variable destino");
                                            
                                            }
                                        }
                                    }
                                }
        
        | ID MOD_IGUAL expresion {
                                    if(ejecutar){

                                        Valor valor_actual = obtener_valor($1);
                                        Valor valor_a_modificar = $3;

                                        if(son_numericas(valor_actual, valor_a_modificar)){
                                            
                                            if(valor_actual.valor_numerico >= valor_a_modificar.valor_numerico || cabe_en_byte(valor_actual, valor_a_modificar) || cabe_en_short(valor_actual, valor_a_modificar)){
                                                
                                                valor_actual.valor_numero = (int) valor_actual.valor_numero % (int) valor_a_modificar.valor_numero;
                                                actualizar_variable($1, valor_actual);
                                                
                                            } else {
                                                
                                                yyerror("Error: El resultado de %= no cabe en la variable destino");
                                            
                                            }
                                        }
                                    

                                    }
                                }
        ;

incremento_decremento: ID MAS_MAS {
                                    if(ejecutar) {
                                        
                                        Valor valor_actual = obtener_valor($1);

                                        if(es_numerica(valor_actual)){
                                            valor_actual.valor_numero++;
                                            actualizar_variable($1, valor_actual);
                                        }
                                    }
                                }
                    | ID MENOS_MENOS {
                                    if(ejecutar) {
                                        
                                        Valor valor_actual = obtener_valor($1);

                                        if(es_numerica(valor_actual)){
                                            valor_actual.valor_numero--;
                                            actualizar_variable($1, valor_actual);
                                        }
                                    }
                                }
                    ;

expresion: NUMERO {$$ = $1;}
        | CADENA {$$.tipo_dato = 1; strcpy($$.valor_cadena, $1);}
        | BOOLEAN {$$.tipo_dato = 2; $$.valor_booleano = $1;}
        | ID {$$ = obtener_valor($1);}
        | expresion MAS expresion {$$ = evaluar_suma_concatenacion($1, $3);}
        
        | expresion MENOS expresion {
                                    if(son_numericas($1, $3)){

                                        Valor resultado;
                                        resultado.tipo_dato = 0;
                                        resultado.valor_numero = $1.valor_numero - $3.valor_numero;
                                        resultado.valor_numerico = ($1.valor_numerico > $3.valor_numerico) ? $1.valor_numerico : $3.valor_numerico;
                                        $$ = resultado;
                                    
                                    }else{
                                        
                                        $$ = $1;
                                    }
                                }
        | expresion POR expresion {
                                    if(son_numericas($1, $3)){

                                        Valor resultado;
                                        resultado.tipo_dato = 0;
                                        resultado.valor_numero = $1.valor_numero * $3.valor_numero;
                                        resultado.valor_numerico = ($1.valor_numerico > $3.valor_numerico) ? $1.valor_numerico : $3.valor_numerico;
                                        $$ = resultado;
                                    
                                    }else{
                                        
                                        $$ = $1;
                                    }
            
                                }
        | expresion DIV expresion {
                                    if(son_numericas($1, $3)){

                                        if(division_por_cero($3)){
                                            
                                            Valor resultado;
                                            resultado.tipo_dato = 0;
                                            resultado.valor_numero = $1.valor_numero / $3.valor_numero;
                                            resultado.valor_numerico = ($1.valor_numerico > $3.valor_numerico) ? $1.valor_numerico : $3.valor_numerico;
                                            $$ = resultado;

                                        }else{
                                            $$ = $1;
                                        }

                                    }
                                }
        | expresion MOD expresion {
                                    if(son_numericas($1, $3)){

                                        if(division_por_cero($3)){
                                            
                                            Valor resultado;
                                            resultado.tipo_dato = 0;
                                            resultado.valor_numero = (int)$1.valor_numero % (int)$3.valor_numero;
                                            resultado.valor_numerico = ($1.valor_numerico > $3.valor_numerico) ? $1.valor_numerico : $3.valor_numerico;
                                            $$ = resultado;

                                        }else{
                                            $$ = $1;
                                        }

                                    }
                                }
        | PARENTESIS_IZQ expresion PARENTESIS_DER {$$ = $2;}
        ;

expresion_logica: expresion IGUAL_IGUAL expresion {
                                                    if(son_numericas($1, $3)){
                                                    
                                                        Valor resultado;
                                                        resultado.valor_booleano = ($1.valor_numero == $3.valor_numero);
                                                        resultado.tipo_dato = 2;
                                                        $$ = resultado;
                                                    
                                                    }else if(son_booleanas($1, $3)){
                                                    
                                                        Valor resultado;
                                                        resultado.valor_booleano = ($1.valor_booleano == $3.valor_booleano);
                                                        resultado.tipo_dato = 2;
                                                        $$ = resultado;

                                                
                                                    }else{

                                                        $$ = $1;
                                                    }
                                                }
        | expresion DIFERENTE expresion {
                                            if(son_numericas($1, $3)){
                                            
                                                Valor resultado;
                                                resultado.valor_booleano = ($1.valor_numero != $3.valor_numero);
                                                resultado.tipo_dato = 2;
                                                $$ = resultado;
                                            
                                            }else if(son_booleanas($1, $3)){
                                            
                                                Valor resultado;
                                                resultado.valor_booleano = ($1.valor_booleano != $3.valor_booleano);
                                                resultado.tipo_dato = 2;
                                                $$ = resultado;

                                        
                                            }else{

                                                $$ = $1;
                                            }
                                        }
        | expresion MAYOR expresion {
                                        if(son_numericas($1, $3)){
                                        
                                            Valor resultado;
                                            resultado.valor_booleano = ($1.valor_numero > $3.valor_numero);
                                            resultado.tipo_dato = 2;
                                            $$ = resultado;
                                        
                                        }else{

                                            $$ = $1;
                                        }
                                    }
        | expresion MENOR expresion {
                                        if(son_numericas($1, $3)){
                                        
                                            Valor resultado;
                                            resultado.valor_booleano = ($1.valor_numero < $3.valor_numero);
                                            resultado.tipo_dato = 2;
                                            $$ = resultado;
                                        
                                        }else{

                                            $$ = $1;
                                        }
                                    }
        | expresion MAYOR_IGUAL expresion {
                                            if(son_numericas($1, $3)){
                                            
                                                Valor resultado;
                                                resultado.valor_booleano = ($1.valor_numero >= $3.valor_numero);
                                                resultado.tipo_dato = 2;
                                                $$ = resultado;
                                            
                                            }else{

                                                $$ = $1;
                                            }
                                        }
        | expresion MENOR_IGUAL expresion {
                                            if(son_numericas($1, $3)){
                                            
                                                Valor resultado;
                                                resultado.valor_booleano = ($1.valor_numero <= $3.valor_numero);
                                                resultado.tipo_dato = 2;
                                                $$ = resultado;
                                            
                                            }else{

                                                $$ = $1;
                                            }
                                        }
        | expresion AND expresion {
                                    if(son_booleanas($1, $3)){
                                    
                                        Valor resultado;
                                        resultado.valor_booleano = ($1.valor_booleano && $3.valor_booleano);
                                        resultado.tipo_dato = 2;
                                        $$ = resultado;
                                    
                                    }else{

                                        $$ = $1;
                                    }
                                }
        | expresion OR expresion {
                                    if(son_booleanas($1, $3)){
                                    
                                        Valor resultado;
                                        resultado.valor_booleano = ($1.valor_booleano || $3.valor_booleano);
                                        resultado.tipo_dato = 2;
                                        $$ = resultado;
                                    
                                    }else{

                                        $$ = $1;
                                    }
        }
        | NOT expresion {
                            if(son_booleanas($2, $2)){
                            
                                Valor resultado;
                                resultado.valor_booleano = !$2.valor_booleano;
                                resultado.tipo_dato = 2;
                                $$ = resultado;
                            
                            }else{

                                $$ = $2;
                            }
        }
        ;

condicional: IF PARENTESIS_IZQ expresion_logica PARENTESIS_DER{if(!$3.valor_booleano){

                                                                    ejecutar = false;
                                                                }
                                                            } 
            
            LLAVE_IZQ sentencias LLAVE_DER{ejecutar = true;}
        
            | IF PARENTESIS_IZQ expresion PARENTESIS_DER {
                                                            if($3.tipo_dato != 2) {
                                                                
                                                                yyerror("Error: La condición del if debe ser booleana");
                                                            
                                                            } else if(!$3.valor_booleano){
                                                                
                                                                ejecutar = false;
                                                            }
                                                        } 
            
            LLAVE_IZQ sentencias LLAVE_DER { ejecutar = true; }
        ;

%%

int main( int argc, char** argv ) {
    
    if ( argc > 1 ) {
        
        yyin = fopen( argv[1], "r" );

        if ( yyin == NULL ) {
            fprintf( stderr, "Error al abrir el archivo: %s\n", argv[1] );
            return 1;
        }

    }else{

        yyin = stdin;
    
    }

    yyparse();

    if(yyin != stdin) {
        fclose(yyin);
    }

    return 0;
}


void yyerror(char* s) {
    fprintf(stderr, "Error: %s\n", s);
}

void imprimir_variables() {
        
    printf("\n* List of variables:\n");
        
    for (int i = 0; i < num_variables; i++) {
            
        printf("\t%s %s = %s\n", variables[i].tipo, variables[i].id, variables[i].valor);
        
    }

}

void actualizar_variable(char* id, Valor valor) {
    
    if(strlen(id) > 200){
        return; 
    }

    for (int i = 0; i < num_variables; i++) {
        
        if (strcmp(variables[i].id, id) == 0) {
            
            if(valor.tipo_dato == 0){

                snprintf(variables[i].valor, sizeof(variables[i].valor), "%.15g", valor.valor_numero);

            }else if(valor.tipo_dato == 1){

                strncpy(variables[i].valor, valor.valor_cadena, sizeof(variables[i].valor) - 1);
                variables[i].valor[sizeof(variables[i].valor) - 1] = '\0';
            
            }else if(valor.tipo_dato == 2){

                strcpy(variables[i].valor, valor.valor_booleano ? "true" : "false");
            }

            break;
        }

    }
}

void guardar_variable(char* tipo, char* id, Valor valor) {


    if(strlen(id) > 200){
        return; 
    }

    strcpy(variables[num_variables].tipo, tipo);
    strcpy(variables[num_variables].id, id);

    if(valor.tipo_dato == 0){

        snprintf(variables[num_variables].valor, sizeof(variables[num_variables].valor), "%.15g", valor.valor_numero);

    }else if(valor.tipo_dato == 1){

        strncpy(variables[num_variables].valor, valor.valor_cadena, sizeof(variables[num_variables].valor) - 1);
        variables[num_variables].valor[sizeof(variables[num_variables].valor) - 1] = '\0';
    
    }else if(valor.tipo_dato == 2){

        strcpy(variables[num_variables].valor, valor.valor_booleano ? "true" : "false");
    }

    num_variables++;
        
}

Valor obtener_valor(char* id) {
    
    Valor valor;
    for(int i = 0; i < num_variables; i++){

        if(strcmp(variables[i].id, id) == 0){

            if(strcmp(variables[i].tipo, "boolean") == 0){

                valor.tipo_dato = 2;
                valor.valor_booleano = (strcmp(variables[i].valor, "true") == 0) ? true : false;

            }else if(strcmp(variables[i].tipo, "String") == 0 || strcmp(variables[i].tipo, "char") == 0){

                valor.tipo_dato = 1;
                strcpy(valor.valor_cadena, variables[i].valor);
                
            }else{

                valor.tipo_dato = 0;
                valor.valor_numero = atof(variables[i].valor);
                valor.valor_numerico = tipo_numerico(variables[i].tipo);

            }

            return valor;

        }

    }

    yyerror("Error: Variable no declarada");
    return valor;



}

bool es_numerica(Valor valor) {
    
    if(valor.tipo_dato != 0) {
        
        yyerror("Error: Solo se pueden operar variables numéricas");
        return false;
    }
    return true;
}

bool son_numericas(Valor valor1, Valor valor2) {
    
    
    if(valor1.tipo_dato != 0 || valor2.tipo_dato != 0) {
        
        yyerror("Error: Solo se pueden operar variables numéricas");
        return false;

    }
    return true;

}

bool son_booleanas(Valor valor1, Valor valor2) {
    
    
    if(valor1.tipo_dato != 2 || valor2.tipo_dato != 2) {
        
        yyerror("Error: Solo se pueden operar variables booleanas");
        return false;

    }
    return true;

}

bool division_por_cero(Valor valor1) {
    
    if(valor1.valor_numero == 0) {
        yyerror("Error: División por cero");
        return false;
    }
    
    return true;
}

bool valor_cabe(Valor valor1, Valor valor2) {
    
    if (valor2.valor_numerico > valor1.valor_numerico) {
        
        yyerror("Error: El resultado de la operacion excede el rango del tipo de destino");
        return false;
    }
    return true;
}

bool cabe_en_byte(Valor valorActual, Valor valorNuevo) {

        if(valorNuevo.valor_numerico == NIVEL_INT && valorActual.valor_numerico == NIVEL_BYTE){

            if(valorNuevo.valor_numero >= -128 && valorNuevo.valor_numero <= 127){
                
                return true;
            }
        }

    return false;
}

bool cabe_en_short(Valor valorActual, Valor valorNuevo) {

        if(valorNuevo.valor_numerico == NIVEL_INT && valorActual.valor_numerico == NIVEL_SHORT){

            if(valorNuevo.valor_numero >= -32768 && valorNuevo.valor_numero <= 32767){
                
                return true;
            }
        }

    return false;
}

int tipo_numerico(char* tipo){

    if(strcmp(tipo, "byte") == 0){
        return NIVEL_BYTE;
    }else if(strcmp(tipo, "short") == 0){
        return NIVEL_SHORT;
    }else if(strcmp(tipo, "int") == 0){
        return NIVEL_INT;
    }else if(strcmp(tipo, "long") == 0){
        return NIVEL_LONG;
    }else if(strcmp(tipo, "float") == 0){
        return NIVEL_FLOAT;
    }else if(strcmp(tipo, "double") == 0){
        return NIVEL_DOUBLE;
    }
    return 0;
}

Valor evaluar_suma_concatenacion(Valor v1, Valor v2){

    Valor resultado;

    if(v1.tipo_dato == 1 || v2.tipo_dato == 1){

        resultado.tipo_dato = 1;
        char temp1[512];
        char temp2[512];

        if(v1.tipo_dato == 0){

            snprintf(temp1, sizeof(temp1), "%.15g", v1.valor_numero);
        
        }else if(v1.tipo_dato == 2){

            strcpy(temp1, v1.valor_booleano ? "true" : "false");

        }else{
            strcpy(temp1, v1.valor_cadena);
        }

        if(v2.tipo_dato == 0){

            snprintf(temp2, sizeof(temp2), "%.15g", v2.valor_numero);
        }else if(v2.tipo_dato == 2){
            strcpy(temp2, v2.valor_booleano ? "true" : "false");
        }else{
            strcpy(temp2, v2.valor_cadena);
        }

        snprintf(resultado.valor_cadena, sizeof(resultado.valor_cadena), "%s%s", temp1, temp2);


    }else if(son_numericas(v1, v2)){

        resultado.tipo_dato = 0;
        resultado.valor_numero = v1.valor_numero + v2.valor_numero;
        resultado.valor_numerico = (v1.valor_numerico > v2.valor_numerico) ? v1.valor_numerico : v2.valor_numerico;

    }else{
        resultado = v1;
    }

    return resultado;

}




