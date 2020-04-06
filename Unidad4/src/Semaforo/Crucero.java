/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semaforo;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author memotets89
 */
public class Crucero {
    private Semaphore s;
    private ArrayList<Coche> carretera;
    private int Lim;
    private String [] marcas ={
        "Volkswagen",
        "Nissan",
        "Jeep",
        "Ferrari",
        "Audi"
    };
    public Crucero(int i){
        this.Lim = i;
        s = new Semaphore(this.Lim,true);
        addCoches();
    }
    private void addCoches(){
        carretera = new ArrayList<>();
        Random rnd = new Random();
        for (int i =0; i<10;i++)
        carretera.add(new Coche(marcas[rnd.nextInt(marcas.length)],s));
    }
    public static void main (String []arr){
        Crucero wop = new Crucero(4); 
        for(int i = 0 ; i< 10; i++){
            wop.carretera.get(i).start();
        }
    }
}
