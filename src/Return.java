public class Return implements Command{
    Integer returnedBookID;

    public Return(Integer returnedBookID) {
        this.returnedBookID = returnedBookID;
    }

    @Override
    public void execute() throws BookCheckoutException{
        Library lib = Library.getInstance();
        lib.books.get(returnedBookID).doReturn();
    }
    @Override
    public boolean canExecute(priority p) {
        return p.equals(priority.Staff);
    }
}
