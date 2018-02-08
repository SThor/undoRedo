/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.Stack;
import javax.swing.Timer;

/**
 *
 * @author givelpa
 */
public class CommandManager{
    
    private Stack<Command> undoableCommands = new Stack<>();
    private Stack<Command> redoableCommands = new Stack<>();
    
    public void registerCommand(Command command){        
        undoableCommands.add(command);
        redoableCommands.clear();
        command.execute();
    }
    
    public void redo(){        
        if(redoableCommands.size()>0){
            Command lastCommand = redoableCommands.pop();
            undoableCommands.push(lastCommand);
            lastCommand.execute();
        }
    }
    
    public void undo(){
        if(undoableCommands.size()>0){
            Command lastCommand = undoableCommands.pop();
            redoableCommands.push(lastCommand);
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
