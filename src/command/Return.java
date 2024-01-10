package command;
import controller.Library;
import exception.PermissionDeniedException;
import object.User;
import object.priority;

@SuppressWarnings("all")
public class Return implements Command{

    public Return() {}

    @Override
    public void execute(User invoker, String arg) throws RuntimeException {
        Library lib = Library.getInstance();
        Integer returnedBookID = Integer.parseInt(arg);
        try{
            lib.books.get(returnedBookID).doReturn();
        }catch(NullPointerException e){
            throw new NullPointerException("Can not return since book " + arg + " does not exist.");
        }
    }
}
