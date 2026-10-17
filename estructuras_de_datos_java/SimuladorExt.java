//Realizado por Ángel Antolín Caramazana y Marcos Gómez Medina

import java.util.*;


public class SimuladorExt extends Simulador {

    public class GoticulaExt extends Simulador.Goticula implements QuadTree.ICoordenable{

        @Override
        public float getX(){
    
            return x;
        }
    
        @Override
        public float getY(){
    
            return y;
        }
    }
  
    QuadTree<GoticulaExt> arbol;

    public SimuladorExt(int n){

        super(n);
        arbol = new QuadTree<>(0, 0, lx, ly);
        for(GoticulaExt gota : (GoticulaExt[]) gotas){

            arbol.insertarGoticula(gota);

        }

    }
   
    @Override
    public void CreaGoticulas(){

        gotas = new GoticulaExt[n];

        for(int i = 0; i < n; i++){

            gotas[i] = new GoticulaExt();

        }

    }

    //Creamos el QuadTree de nuevo cuando se reposicionen las goticulas
    @Override
    public void ReestructuraED(){

        arbol = new QuadTree<>(0, 0, lx, ly);
        
        for(GoticulaExt gota : (GoticulaExt[]) gotas){
            arbol.insertarGoticula(gota);

        }

    }
    
    @Override
    protected float CalcDensidad(float x, float y){

        float densidad = 0;
        List<GoticulaExt> goticulasACalcularDensidad = new ArrayList<>();

        arbol.goticulasEnRango(x - 1, y - 1, x + 1, y + 1, goticulasACalcularDensidad);

        for(GoticulaExt gota : goticulasACalcularDensidad){

            densidad += CalcDensidadIter(x, y, gota);

        }

        return densidad;

    }

    @Override
    protected VecXY CalcPresion(Goticula goticula){

        VecXY f = new VecXY(0, 0);
        List<GoticulaExt> goticulasACalcularPresion = new ArrayList<>();

        arbol.goticulasEnRango(goticula.x - 1, goticula.y - 1, goticula.x + 1, goticula.y + 1, goticulasACalcularPresion);

        for(GoticulaExt gota : goticulasACalcularPresion){

            f.Add(CalcPresionIter(goticula, gota));

        }
        f.Scale(1/goticula.d);

        return f;

    }


    @Override
    protected VecXY CalcViscosidad(Goticula goticula){

        VecXY f = new VecXY(0, 0);
        List<GoticulaExt> goticulasACalcularViscosidad = new ArrayList<>();

        arbol.goticulasEnRango(goticula.x - 1, goticula.y - 1, goticula.x + 1, goticula.y + 1, goticulasACalcularViscosidad);

        for(GoticulaExt gota : goticulasACalcularViscosidad){

            f.Add(CalcViscosidadIter(goticula, gota));

        }
    
        return f;
    
    }

    public static void main(String[] args) {
        
        double mediaop = 0;
        double mediatiempo = 0;
        long num_operacionestotales = 0;
        double tiempototal = 0;


        SimuladorExt sim = new SimuladorExt(10000);
        
      
        // Avanzamos 600 pasos
        for(int i = 0; i < 600; i++) { 
            
            
            sim.PasoSimulacion(); 
            
            num_operacionestotales += Simulador.NOPER;
            tiempototal += sim.tpo;
            
            System.out.println("Iteracion: " + i);
        
        }
        
        mediaop = num_operacionestotales/600;
        mediatiempo = tiempototal/600;
        
        System.out.println("Media de operaciones: " + mediaop);
        System.out.println("Media de tiempo: " + mediatiempo);
        
        
        
        
        // Lo mostramos en GUIPtos
        javax.swing.SwingUtilities.invokeLater(() -> { new GUIPtos(sim); });

    }
}