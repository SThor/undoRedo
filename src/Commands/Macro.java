/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author givelpa
 */
public class Macro implements Command{
    List<Command> commands = new ArrayList<Command>();
    String name;
    private UUID uuid;

    public Macro copy() {
        Macro macroCopy = new Macro(this.name);
        for (Command command : this.commands) {
            macroCopy.commands.add(command.copy());
        }
        return macroCopy;
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
        System.out.println("commands.size() = " + commands.size());
        for (int i=commands.size()-1;i>=0;i--) {
            Command command = commands.get(i);
            System.out.println("Undoing command " + command);
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

    @Override
    public String toString() {
        return "Macro "+ name;
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
        for (Command command : commands) {
            command.setUUID(uuid);
        }
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }
}
