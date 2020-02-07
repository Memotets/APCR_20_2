/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaNIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author memotets89
 */
public class LectorArchivo {
    private FileInputStream fis;
    private FileChannel channel;
    private ByteBuffer buffer;
    private String name;
    
    public LectorArchivo () throws IOException{
        this.name= "Ejemplo.xml";
        this.fis = new FileInputStream(this.name);
        this.channel = this.fis.getChannel();
        this.buffer = ByteBuffer.allocate(48);
        int readedBytes = this.channel.read(this.buffer);
        while(readedBytes != -1){
            System.out.println("los datos leidos: "+ readedBytes);
           this.buffer.flip();
           while(this.buffer.hasRemaining()){
               System.out.print((char)this.buffer.get());
           }
           this.buffer.clear();
           
           readedBytes = this.channel.read(buffer);
        }
         System.out.println("");
        fis.close();
                
    }
     public static void main (String []ad) throws IOException{
         LectorArchivo a = new LectorArchivo();
     }
}
