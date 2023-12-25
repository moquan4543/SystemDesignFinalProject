package command;

import exception.ExceedLimitationException;
import exception.PermissionDeniedException;
import object.*;
import controller.Library;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@SuppressWarnings("all")
public class Checkout implements Command{
    public Checkout() {}

    @Override
    public void execute(User invoker, String arg) throws RuntimeException {
        Library lib = Library.getInstance();
        try{
            if(!invoker.getUserType().equals(priority.Staff)){
                throw new PermissionDeniedException("Borrower can not check out the books ");
            }
            User borrower = lib.users.get(arg);
            List<Integer> borrowedBooks = Arrays.stream(lib.br.readLine().split("\\s+")).map(Integer::parseInt).toList();
            if(borrowedBooks.size() > borrower.getPredefinedBorrowBookNumber()){
                throw new ExceedLimitationException("Can not check out since the number of books exceed the limitation of user can check-out");
            }
            borrowedBooks.forEach(id -> lib.books.get(id).doBorrow(borrower));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
