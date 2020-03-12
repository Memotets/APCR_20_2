/*
 * Guillermo Ignacio Bautista Garcia
 * Hilos, instrucciones atomicas y exclusion mutua
 * Ejemplo Hilos que usan un mismo recurso: Consumidor
 * 30/01/20
 */
package unidad4;

/**
 *
 * @author memotets89
 */
public class Consumidor extends Thread{

    private RecursoComp rc;

    Consumidor(RecursoComp rc) {
        this.rc = rc;
    }
    
        @Override
    public void run() {
       int i =1;
       do{
           this.rc.getAux(i%10);
           System.out.println("Salio: "+i+" en: "+(i-1)%10);
       }while(i++!=100);
    }
    
}
