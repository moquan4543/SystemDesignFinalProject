import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.List;

public class Checkout implements Command{
    User borrower;
    BufferedReader br;

    public Checkout(User borrower,BufferedReader br) {
        this.borrower = borrower;
        this.br = br;
    }

    @Override
    public void execute() {
        Library lib = Library.getInstance();
        try{
            List<Integer> borrowedBooks = Arrays.stream(br.readLine().split("\\s+")).map(s -> Integer.parseInt(s)).toList();
            if(borrowedBooks.size() > borrower.getPredefinedBorrowBookNumber()){
                throw new ExceedLimitationException();
            }
            borrowedBooks.forEach(id -> {
                lib.books.get(id).doBorrow(borrower);
            });
        }catch(BookCheckoutException e){
            throw e;
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean canExecute(priority p) {
        return p.equals(priority.Staff);
    }
}
