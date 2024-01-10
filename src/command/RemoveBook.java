package command;
import controller.Library;
import exception.PermissionDeniedException;
import object.User;
import object.priority;

@SuppressWarnings("all")
public class RemoveBook implements Command{

    public RemoveBook() {}

    @Override
    public void execute(User invoker, String arg) {
        Library lib = Library.getInstance();
        Integer bookID = Integer.parseInt(arg);
        if(lib.books.containsKey(bookID)){
            lib.books.remove(bookID);
        }else{
            throw new NullPointerException("Can't not remove since book " + arg + " does not exist.");
        }
    }
}