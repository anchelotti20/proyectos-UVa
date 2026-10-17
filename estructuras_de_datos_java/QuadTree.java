//Realizado por Ángel Antolín Caramazana y Marcos Gómez Medina

import java.util.*;

public class QuadTree<T extends QuadTree.ICoordenable> {

    public interface ICoordenable {
        
        public float getX();
        public float getY();
        
    }

    public static int MAX_ELEMENTOS = 15; //Numero maximo de goticulas almacenables en las hojas

    private float minimoX;              //Limites del area 2D
    private float minimoY;
    private float maximoX;
    private float maximoY;
    private float medioX;
    private float medioY;

    private List<T> goticulas;          //Lista de goticulas de la hoja

    private QuadTree<T> arribaIzquierda;    //Hijos del arbol
    private QuadTree<T> arribaDerecha;
    private QuadTree<T> abajoIzquierda;
    private QuadTree<T> abajoDerecha;

    //Crear un arbol vacío
    public QuadTree(float minimoX, float minimoY, float maximoX, float maximoY){

        goticulas = new ArrayList<>();
        this.minimoX = minimoX;
        this.minimoY = minimoY;
        this.maximoX = maximoX;
        this.maximoY = maximoY;

        medioX = (minimoX + maximoX)/2;
        medioY = (minimoY + maximoY)/2;

        arribaIzquierda = null;
        arribaDerecha = null;
        abajoIzquierda = null;
        abajoDerecha = null;

    }


    public void insertarGoticula(T goticula){

        //Comprueba si hay que dividir el arbol en sus hijos
        if(arribaIzquierda == null && goticulas.size() >= MAX_ELEMENTOS - 1){


            arribaIzquierda = new QuadTree<>(minimoX, minimoY, medioX, medioY);
            arribaDerecha = new QuadTree<>(medioX, minimoY, maximoX, medioY);
            abajoIzquierda = new QuadTree<>(minimoX, medioY, medioX, maximoY);
            abajoDerecha = new QuadTree<>(medioX, medioY, maximoX, maximoY);

            for(T gota : goticulas){    //Recoloca las goticulas en los hijos

                insertarGoticula(gota);

            }

            goticulas.clear();


        }

        if(arribaIzquierda == null){    //Si el nodo no tiene hijos, la añade

            goticulas.add(goticula);

        }else{                          //Si tiene hijos, elige en que hijo depositarla

            if(goticula.getX() > medioX){

                if(goticula.getY() > medioY){

                    abajoDerecha.insertarGoticula(goticula);

                }else{

                    arribaDerecha.insertarGoticula(goticula);

                }

            }else{

                if(goticula.getY() > medioY){

                    abajoIzquierda.insertarGoticula(goticula);

                }else{

                    arribaIzquierda.insertarGoticula(goticula);

                }

            }


        }

    }

    
    public void goticulasEnRango(float minRangoX, float minRangoY, float maxRangoX, float maxRangoY, List<T>enRango){

        //Si no intersecta con el rango de búsqueda, no miro el nodo
        if(maxRangoX < minimoX || minRangoX > maximoX || maxRangoY < minimoY || minRangoY > maximoY){

            return;
        }

        //Si no tiene hijos, busco en la lista
        if(arribaIzquierda == null){


            for(T gota : goticulas){

                if(gota.getX() >= minRangoX && gota.getX() <= maxRangoX && gota.getY() >= minRangoY && gota.getY() <= maxRangoY){

                    enRango.add(gota);


                }

            }
        
        //Si tiene hijos, cada hijo llama al metodo
        }else{

            arribaIzquierda.goticulasEnRango(minRangoX, minRangoY, maxRangoX, maxRangoY, enRango);
            arribaDerecha.goticulasEnRango(minRangoX, minRangoY, maxRangoX, maxRangoY, enRango);
            abajoIzquierda.goticulasEnRango(minRangoX, minRangoY, maxRangoX, maxRangoY, enRango);
            abajoDerecha.goticulasEnRango(minRangoX, minRangoY, maxRangoX, maxRangoY, enRango);
        }


    }




}