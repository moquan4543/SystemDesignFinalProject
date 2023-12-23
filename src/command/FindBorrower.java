package command;
import controller.Library;
import object.Book;
import object.priority;

public class FindBorrower implements Command{
    Integer bookID;

    public FindBorrower(Integer bookID) {
        this.bookID = bookID;
    }

    @Override
    public void execute() {
        Library lib = Library.getInstance();
        Book targetBook = lib.books.get(bookID);
        System.out.println("object.User: "+targetBook.getBorrowedBy().getUserName());

    }

    @Override
    public boolean canExecute(priority p) {
        return p.equals(priority.Staff);
    }

}
