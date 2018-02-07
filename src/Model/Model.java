/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.UUID;

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
    public final String PROPERTY_LASTUUID = "lastUUID";
    
    private UUID lastUUID;
    
    public void setValue(UUID uuid, int value){
        setLastUUID(uuid);
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
    
    public void multiply(UUID uuid, double c){
        setLastUUID(uuid);
        setValue(uuid, (int) (getValue()*c));
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
    
    public void returnTo(int returnValue) {
        undoMean(returnValue);
        int oldValue = getValue();
        this.value = returnValue;
        support.firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
    }
    
    private void undoMean(int returnValue) {
        double oldMean = getMean();
        double oldNumberOfChanges = getNbChanges();
        nbChanges--;
        mean = (oldMean * oldNumberOfChanges - returnValue) / getNbChanges();
        support.firePropertyChange(PROPERTY_MEAN, oldMean, getMean());
        support.firePropertyChange(PROPERTY_NBCHANGES, oldNumberOfChanges, getNbChanges());
    }

    private void setLastUUID(UUID uuid) {
        UUID oldLastUUID = getLastUUID();
        this.lastUUID = uuid;
        support.firePropertyChange(PROPERTY_LASTUUID, oldLastUUID, getLastUUID());
    }

    public UUID getLastUUID() {
        return lastUUID;
    }
}
