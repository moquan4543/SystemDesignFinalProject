package command;
import object.User;
public interface Command {
    void execute(User invoker, String arg);
}
