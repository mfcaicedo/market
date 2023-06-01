/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reloj.frameworkobsobs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahurtado
 */
public class Observado {
    List<Observador> misObservadores=null;
    
    public void addObservador(Observador one){
        if(misObservadores==null){
            misObservadores = new ArrayList<>();
        }
        misObservadores.add(one);
    }
    
    public void notificar(){
        for(Observador each: misObservadores){
            each.actualizar();
        }
    }
    
}
