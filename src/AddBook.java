import java.io.BufferedReader;
import java.io.IOException;

public class AddBook implements Command {
    BufferedReader br;
    public AddBook(BufferedReader br) {
        this.br = br;
    }
    @Override
    public void execute() {
        Library lib = Library.getInstance();
        try{
            String[] addBooks = br.readLine().split("\\s+");
            Integer addBookID = lib.books.size();
            lib.books.put(addBookID,new Book(addBookID,addBooks[0],addBooks[1]));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public boolean canExecute(priority p) {
        return p.equals(priority.Staff);
    }
}
