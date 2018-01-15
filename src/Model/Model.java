/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author givelpa
 */
public class Model {
    private int value;
    private double mean;
    private int nbChanges;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    public final String PROPERTY_VALUE = "value";
    public final String PROPERTY_MEAN = "mean";
    public final String PROPERTY_NBCHANGES = "nbChanges";
    
    public void setValue(int value){
        int oldValue = getValue();
        int oldNbChanges = getNbChanges();
        this.value = value;
        nbChanges++;
        support.firePropertyChange(PROPERTY_NBCHANGES, oldNbChanges, getNbChanges());
        support.firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
        updateMean();
    }
    
    public int getValue(){
        return this.value;
    }
    
    public void multiply(double c){
        setValue((int) (getValue()*c));
    }
    
    public double getMean(){
        return this.mean;
    }
    
    public int getNbChanges(){
        return this.nbChanges;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener){
        support.addPropertyChangeListener(propertyName, listener);
    }
    
    
    public void removePropertyChangeListener(PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener){
        support.removePropertyChangeListener(propertyName, listener);
    }

    private void updateMean() {
        double oldMean = getMean();
        mean = (getMean()*(nbChanges-1)+ getValue())/nbChanges;
        support.firePropertyChange(PROPERTY_MEAN, oldMean, getMean());
    }
}
