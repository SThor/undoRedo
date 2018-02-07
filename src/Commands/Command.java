/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.UUID;

/**
 *
 * @author givelpa
 */
public interface Command {
    void execute();
    void undo();
    
    String toString();
    public Command copy();

    void setUUID(UUID uuid);
    UUID getUUID();
}
