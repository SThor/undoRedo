/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Model.Model;
import java.util.UUID;

/**
 *
 * @author givelpa
 */
public class Set implements Command{
    
    private Model model;
    private final int value;
    private int oldValue;
    private UUID uuid;

    public Set(Model model, int value) {
        this.model = model;
        this.value = value;
    }
    
    @Override
    public void execute() {
        oldValue = model.getValue();
        model.setValue(uuid, value);
    }

    @Override
    public void undo() {
        model.returnTo(oldValue);
    }

    @Override
    public Command copy() {
        return new Set(model, value);
    }

    @Override
    public String toString() {
        return "Set "+value;
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }
}
