package command;
import object.User;
import java.io.IOException;
import controller.Library;
import exception.PermissionDeniedException;
import object.Book;
import object.priority;
@SuppressWarnings("all")
public class AddBook implements Command {
    public AddBook(){}

    @Override
    public void execute(User invoker, String arg) throws RuntimeException{
        Library lib = Library.getInstance();
        try{
            if(!invoker.getUserType().equals(priority.Staff)){
                throw new PermissionDeniedException("Borrower can not add book");
            }
            String[] addBooks = lib.br.readLine().split("\\s+");
            Integer addBookID = lib.books.size();
            lib.books.put(addBookID,new Book(addBookID,addBooks[0],addBooks[1]));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
