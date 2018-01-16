/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author givelpa
 */
public class CommandManager{
    
    private List<Command> undoableCommands = new ArrayList<>();
    private List<Command> redoableCommands = new ArrayList<>();
    
    public void registerCommand(Command command){        
        undoableCommands.add(command);
        redoableCommands.clear();
        command.execute();
    }
    
    public void redo(){        
        if(redoableCommands.size()>1){
            Command lastCommand = redoableCommands.get(redoableCommands.size()-1);
            undoableCommands.add(lastCommand);
            redoableCommands.remove(lastCommand);
            lastCommand.execute();
        }
    }
    
    public void undo(){
        if(undoableCommands.size()>1){
            Command lastCommand = undoableCommands.get(undoableCommands.size()-1);
            redoableCommands.add(lastCommand);
            undoableCommands.remove(lastCommand);
            lastCommand.execute();
        }
    }
}
