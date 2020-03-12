/*
 * Guillermo Ignacio Bautista Garcia
 * Hilos, instrucciones atomicas y exclusion mutua
 * Ejemplo Hilos que usan un mismo recurso: Recurso compartido
 * 30/01/20
 */
package unidad4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author memotets89
 */
class RecursoComp {
    int aux[] = new int[10];
    private boolean[] lleno = new boolean[10];
    //private boolean vacio= !lleno;
    
    public synchronized int getAux(int pos) {
       if(!lleno[pos]){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Error de interrupcion");
            }
        }
            this.lleno[pos]=false;
            notifyAll();
            return aux[pos];
        
    }

    public synchronized void setAux(int aux, int pos) {
        if(lleno[pos]){//si tiene valor
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Error de interrupcion");
            }
        }
            this.aux[pos] = aux;
            this.lleno[pos]=true;
            notifyAll();
        
    }
    
    
}
