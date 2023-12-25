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
        if(!invoker.getUserType().equals(priority.Staff)){
            throw new PermissionDeniedException("Borrower can not remove book");
        }
        Library lib = Library.getInstance();
        Integer bookID = Integer.parseInt(arg);
        lib.books.remove(bookID);
    }
}
