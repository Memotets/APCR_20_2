/*
 * Guillermo Ignacio Bautista Garcia
 * Sockets
 * Estructura DNS
 * 30/01/20
 */
package PrimerClienteServidorTCP.Tarea2;

/**
 *
 * @author memotets89
 */
public class EstructuraDNS {
    private String dominio, IP;
    
    EstructuraDNS(String n, String num){
        dominio=n;
        IP = num;
    }

    public String getDominio() {
        return dominio;
    }

    public String getIP() {
        return IP;
    }
    
    
    public boolean esIgual(String n){
        return (IP.equals(n) || dominio.equals(n));
    }
}
