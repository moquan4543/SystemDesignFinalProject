package command;
import controller.Library;
import object.Book;
import object.User;

@SuppressWarnings("all")
public class FindBorrower implements Command{
    public FindBorrower(){}
    private CommandPermissionLevel p = CommandPermissionLevel.Staff;
    private String permissionDeniedMsg = "Borrower can not find borrower";
    @Override
    public void execute(User invoker, String arg) throws RuntimeException{
        Library lib = Library.getInstance();
        Integer bookID = Integer.parseInt(arg);
        Book targetBook = lib.books.get(bookID);
        if(targetBook == null){
            throw new NullPointerException("The book "+ bookID +" does not exist.");
        }
        if(targetBook.getBorrowedBy() == null){
            throw new NullPointerException("The book " + bookID + " isn't checked out.");
        }
        System.out.println("User: "+targetBook.getBorrowedBy().getUserName());
    }
    @Override
    public CommandPermissionLevel getCommandPermissionLevel() {
        return p;
    }
    @Override
    public String getPermissionDeniedMsg() {
        return permissionDeniedMsg;
    }
}