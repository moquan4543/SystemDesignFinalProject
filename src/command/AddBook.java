package command;
import object.User;
import java.io.IOException;
import controller.Library;
import object.Book;
@SuppressWarnings("all")
public class AddBook implements Command {

    public AddBook(){}
    private CommandPermissionLevel p = CommandPermissionLevel.Staff;
    private String permissionDeniedMsg = "Borrower can not add book.";

    @Override
    public void execute(User invoker, String arg) throws RuntimeException{
        Library lib = Library.getInstance();
        try{
            String[] addBook = lib.br.readLine().split("\\s+");
            Integer addBookID = lib.bookID++;
            lib.books.put(addBookID,new Book(addBookID,addBook[0],addBook[1]));
        }catch(IOException e){
            e.printStackTrace();
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
