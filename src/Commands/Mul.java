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
public class Mul implements Command{
    
    private Model model;
    private double value;

    public Mul(Model model, double value) {
        this.model = model;
        this.value = value;
    }

    @Override
    public void execute() {
        model.multiply(value);
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
