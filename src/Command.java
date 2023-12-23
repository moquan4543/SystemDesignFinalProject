public interface Command {
    void execute();
    boolean canExecute(priority p);
}
