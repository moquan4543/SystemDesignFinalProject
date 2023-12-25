package command;
import controller.Library;
import exception.PermissionDeniedException;
import object.Book;
import object.User;
import object.priority;
@SuppressWarnings("all")
public class FindBorrower implements Command{
    public FindBorrower() {}

    @Override
    public void execute(User invoker, String arg) throws RuntimeException{
        if(!invoker.getUserType().equals(priority.Staff)){
            throw new PermissionDeniedException("Borrower can not find borrower");
        }
        Library lib = Library.getInstance();
        Integer bookID = Integer.parseInt(arg);
        Book targetBook = lib.books.get(bookID);
        System.out.println("User: "+targetBook.getBorrowedBy().getUserName());
    }
}
