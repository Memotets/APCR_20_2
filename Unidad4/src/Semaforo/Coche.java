/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semaforo;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author memotets89
 */
public class Coche extends Thread{
    private Semaphore sem;
    private String marca;
    
    public Coche(String n, Semaphore s){
        this.marca = n;
        this.sem = s;
    }
    
    @Override
    public void run() {
        try {
            sem.acquire();
            System.out.println("Verde, salio: "+marca);
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            //System.out.println("Rojo");
            sem.release();
            
        }
    }
    
}
