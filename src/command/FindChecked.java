package command;
import object.*;
import controller.Library;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class FindChecked implements Command{
    public FindChecked(){}
    private CommandPermissionLevel p = CommandPermissionLevel.Self;
    private String permissionDeniedMsg = "Borrower can not find books checked out by other users";
    @Override
    public void execute(User invoker, String arg) throws RuntimeException{
        Library lib = Library.getInstance();
        User user = lib.users.get(arg);
        if(user == null){
            throw new NullPointerException("User "+arg+" does not exist.");
        }
        Stream<Book> stream = lib.books.values().stream();
        stream.filter(book -> user.equals(book.getBorrowedBy())).forEach(book -> System.out.println("ID: "+book.getBookID()+" Author: "+book.getBookAuthor()+" Subject: "+book.getBookSubject()));
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
