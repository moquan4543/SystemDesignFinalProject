package command;
import controller.Library;
import object.User;

@SuppressWarnings("all")
public class RemoveBook implements Command{

    public RemoveBook() {}
    private CommandPermissionLevel p = CommandPermissionLevel.Staff;
    private String permissionDeniedMsg = "Borrower can not remove book";
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
    @Override
    public CommandPermissionLevel getCommandPermissionLevel() {
        return p;
    }
    @Override
    public String getPermissionDeniedMsg() {
        return permissionDeniedMsg;
    }
}