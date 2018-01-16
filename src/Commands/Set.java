/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Model.Model;

/**
 *
 * @author givelpa
 */
public class Set implements Command{
    
    private Model model;
    private final int value;

    public Set(Model model, int value) {
        this.model = model;
        this.value = value;
    }
    
    @Override
    public void execute() {
        model.setValue(value);
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
