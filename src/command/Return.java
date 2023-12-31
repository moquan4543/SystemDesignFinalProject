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
        if(!invoker.getUserType().equals(priority.Staff)){
            throw new PermissionDeniedException("Borrower can not return book.");
        }
        Library lib = Library.getInstance();
        Integer returnedBookID = Integer.parseInt(arg);
        try{
            lib.books.get(returnedBookID).doReturn();
        }catch(NullPointerException e){
            throw new NullPointerException("Can not return book since the book does not exist.");
        }
    }
}
