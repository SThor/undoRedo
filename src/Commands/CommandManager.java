/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.Timer;

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
        if(redoableCommands.size()>0){
            Command lastCommand = redoableCommands.get(redoableCommands.size()-1);
            undoableCommands.add(lastCommand);
            redoableCommands.remove(lastCommand);
            lastCommand.execute();
        }
    }
    
    public void undo(){
        if(undoableCommands.size()>0){
            Command lastCommand = undoableCommands.get(undoableCommands.size()-1);
            redoableCommands.add(lastCommand);
            undoableCommands.remove(lastCommand);
            lastCommand.undo();
        }
    }
    
    public boolean canUndo(){
        return undoableCommands.size()>0;
    }
    
    public boolean canRedo(){
        return redoableCommands.size()>0;
    }

    public void registerCommand(Command command, Integer delay) {
        Timer timer = new Timer(delay*1000, (e) -> {
            registerCommand(command);
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void flushUndoStack() {
        undoableCommands.clear();
    }
}
