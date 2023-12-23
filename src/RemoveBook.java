public class RemoveBook implements Command{
    private int bookID;
    public RemoveBook(int id) {
        this.bookID = id;
    }
    @Override
    public void execute() {
        Library lib = Library.getInstance();
        lib.books.remove(bookID);

    }

    @Override
    public boolean canExecute(priority p) {
        return p.equals(priority.Staff);
    }
}
