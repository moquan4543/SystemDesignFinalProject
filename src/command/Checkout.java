package command;
import exception.BookCheckoutException;
import exception.ExceedLimitationException;
import object.*;
import controller.Library;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class Checkout implements Command{
    public Checkout(){}
    private CommandPermissionLevel p = CommandPermissionLevel.Staff;
    private String permissionDeniedMsg = "Borrower can not check out the books.";
    @Override
    public void execute(User invoker, String arg) throws RuntimeException {
        Library lib = Library.getInstance();
        try{
            User borrower = lib.users.get(arg);
            if(borrower == null){
                throw new NullPointerException("Username "+ arg +" not found.");
            }
            List<Integer> borrowedBooks = Arrays.stream(lib.br.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
            if(borrowedBooks.size() > borrower.getPredefinedBorrowBookNumber()){
                throw new ExceedLimitationException("Can not check out since the number of books(" + borrowedBooks.size() + ") exceed the limitation of user can check-out("+borrower.getPredefinedBorrowBookNumber()+").");
            }
            borrowedBooks.forEach(id -> {
                try{
                    lib.books.get(id).doBorrow(borrower);
                }catch(BookCheckoutException e){
                    System.out.println(e.getMessage());
                }
            });
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
