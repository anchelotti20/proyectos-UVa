%{
    #include "y.tab.h"
    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>
    void yyerror(char *s);
%}

DIGITOS [0-9]
NUMERO [+-]?{DIGITOS}+(\.{DIGITOS}+)?[fFlL]?
CADENA \"[^\"]*\"
ID [a-zA-Z][a-zA-Z0-9_]*

%x COMENTARIO

%%

"/*"                {BEGIN(COMENTARIO);}
<COMENTARIO>"*/"    {BEGIN(INITIAL);}
<COMENTARIO>.|\n    {}

"//".*              {}



"public"[ \t]*"class"      {printf("%s", yytext); return PUBLIC_CLASS;}
"public"[ \t]*"static"[ \t]*"void"[ \t]*"main"[ \t]*"(String[] args)" {printf("%s", yytext);return PUBLIC_STATIC_VOID_MAIN;}
"if"                      {printf("%s", yytext);return IF;}
(String|char|double|float|long|int|short|byte|boolean) {printf("%s", yytext); yylval.str = strdup(yytext); return TIPO;}
"true"                    {printf("%s", yytext);yylval.booleano = true; return BOOLEAN;}       
"false"                   {printf("%s", yytext);yylval.booleano = false; return BOOLEAN;}

{NUMERO}                {
                            printf("%s", yytext);
                            yylval.valor.tipo_dato = 0;
                            yylval.valor.valor_numero =  atof(yytext);
                            
                            if (strchr(yytext, 'f') || strchr(yytext, 'F')) {
                                
                                yylval.valor.valor_numerico = NIVEL_FLOAT; 
                            
                            } else if (strchr(yytext, 'l') || strchr(yytext, 'L')) {
                                
                                yylval.valor.valor_numerico = NIVEL_LONG;  
                            
                            }else if (strchr(yytext, '.')) {
                                
                                yylval.valor.valor_numerico = NIVEL_DOUBLE; 
                            } else {
                                
                                yylval.valor.valor_numerico = NIVEL_INT;    
                            }
                            
                            ; return NUMERO;}
{CADENA}                {
                            printf("%s", yytext);
                            yytext[strlen(yytext) - 1] = '\0';
                            yylval.str = strdup(yytext + 1); 
                            return CADENA;
                        }
{ID}                    {
                            printf("%s", yytext);
                            if(strlen(yytext) > 200) {
                                yyerror("Identificador demasiado largo");
                                
                            }
                            yylval.str = strdup(yytext); 
                            return ID;
                        }

"+"                    {printf("%s", yytext); return MAS;}
"-"                    {printf("%s", yytext); return MENOS;}
"*"                    {printf("%s", yytext); return POR;}
"/"                    {printf("%s", yytext); return DIV;}
"%"                    {printf("%s", yytext); return MOD;}
"=="                   {printf("%s", yytext); return IGUAL_IGUAL;}
"="                    {printf("%s", yytext); return IGUAL;}
"+="                   {printf("%s", yytext); return MAS_IGUAL;}
"-="                   {printf("%s", yytext); return MENOS_IGUAL;}
"*="                   {printf("%s", yytext); return POR_IGUAL;}
"/="                   {printf("%s", yytext); return DIV_IGUAL;}
"%="                   {printf("%s", yytext); return MOD_IGUAL;}
"++"                   {printf("%s", yytext); return MAS_MAS;}
"--"                   {printf("%s", yytext); return MENOS_MENOS;}
"&&"                   {printf("%s", yytext); return AND;}
"||"                   {printf("%s", yytext); return OR;}
"!"                    {printf("%s", yytext); return NOT;}
">"                    {printf("%s", yytext); return MAYOR;}
"<"                    {printf("%s", yytext); return MENOR;}
">="                   {printf("%s", yytext); return MAYOR_IGUAL;}
"<="                   {printf("%s", yytext); return MENOR_IGUAL;}
"!="                   {printf("%s", yytext); return DIFERENTE;}
"("                    {printf("%s", yytext); return PARENTESIS_IZQ;}
")"                    {printf("%s", yytext); return PARENTESIS_DER;}
"{"                    {printf("%s", yytext); return LLAVE_IZQ;}
"}"                    {printf("%s", yytext); return LLAVE_DER;}
";"                    {printf("%s", yytext); return PUNTO_Y_COMA;}

[ \t\n]+                {printf("%s", yytext);}
.                       {printf("%s", yytext); return yytext[0];} 



%%