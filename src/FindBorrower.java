public class FindBorrower implements Command{
    Integer bookID;

    public FindBorrower(Integer bookID) {
        this.bookID = bookID;
    }

    @Override
    public void execute() {
        Library lib = Library.getInstance();
        Book targetBook = lib.books.get(bookID);
        System.out.println("User: "+targetBook.getBorrowedBy().getUserName());

    }

    @Override
    public boolean canExecute(priority p) {
        return p.equals(priority.Staff);
    }

}
