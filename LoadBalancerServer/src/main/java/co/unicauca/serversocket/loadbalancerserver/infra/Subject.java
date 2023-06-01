package co.unicauca.serversocket.loadbalancerserver.infra;

import java.util.ArrayList;

/**
 * Sujeto, o tambien el observadoF
 *
 * @author ahurtado
 */
public abstract class Subject {

    ArrayList<Observer> observers;

    public void Subject() {

    }

    /**
     * Agrega un observador
     *
     * @param obs
     */
    public void addObserver(Observer obs) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(obs);
    }
    
    /**
     * Elimina un observador
     *
     * @param obs
     */
    public void removeObserver(Observer obs) {
        if (observers.contains(obs))
            observers.remove(obs);
    }

    /**
     * Notifica a todos los observadores que hubo un cambio en el modelo
     */
    public void notifyAllObserves() {
        observers.forEach(each -> {
            each.update(this);
        });
    }

}
