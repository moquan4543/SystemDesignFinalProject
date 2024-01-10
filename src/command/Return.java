package command;
import controller.Library;
import object.User;

@SuppressWarnings("all")
public class Return implements Command{

    public Return() {}
    private CommandPermissionLevel p = CommandPermissionLevel.Staff;
    private String permissionDeniedMsg = "Borrower can not return book.";
    @Override
    public void execute(User invoker, String arg) throws RuntimeException {
        Library lib = Library.getInstance();
        Integer returnedBookID = Integer.parseInt(arg);
        try{
            lib.books.get(returnedBookID).doReturn();
        }catch(NullPointerException e){
            throw new NullPointerException("Can not return since book " + arg + " does not exist.");
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
