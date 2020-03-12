/*
 * Guillermo Ignacio Bautista Garcia
 * Hilos, instrucciones atomicas y exclusion mutua
 * Ejemplo Hilos que usan un mismo recurso
 * 30/01/20
 */
package unidad4;

/**
 *
 * @author memotets89
 */
public class Unidad4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RecursoComp rc = new RecursoComp();
        Productor p = new Productor(rc);
        Consumidor c = new Consumidor(rc);
        
        p.start();
        c.start();
    }
    
}
