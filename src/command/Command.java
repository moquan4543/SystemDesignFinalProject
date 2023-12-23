package command;
import object.priority;
public interface Command {
    void execute();
    boolean canExecute(priority p);
}
