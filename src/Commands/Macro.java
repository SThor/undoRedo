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
public class Macro implements Command{
    List<Command> commands = new ArrayList<Command>();
    String name;

    public Macro(Macro tmpMacro) {
        this.commands = tmpMacro.commands;
        this.name = tmpMacro.name;
    }

    @Override
    public void execute() {
        for (int i=0;i<commands.size();i++) {
            Command command = commands.get(i);
            command.execute();
        }
    }

    @Override
    public void undo() {
        for (int i=commands.size();i<=0;i--) {
            Command command = commands.get(i);
            command.undo();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Macro(String name) {
        this.name = name;
    }
    
    public void addCommand(Command command){
        commands.add(command);
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public int getSize() {
        return commands.size();
    }
}
