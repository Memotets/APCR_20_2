/*
 * Guillermo Ignacio Bautista Garcia
 * Hilos, instrucciones atomicas y exclusion mutua
 * Ejemplo Hilos que usan un mismo recurso: Productor
 * 30/01/20
 */
package unidad4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author memotets89
 */
class Productor extends Thread{
    private RecursoComp rc;
    
    Productor(RecursoComp rc) {
        this.rc = rc;
    }

    @Override
    public void run() {
       int i =1;
       do{
           this.rc.setAux(i,i%10);
           System.out.println("Entro: "+i+" en: "+(i-1)%10);
           try {
               sleep(10);
           } catch (InterruptedException ex) {
               Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
           }
       }while(i++!=100);
    }
    
    
    
}
