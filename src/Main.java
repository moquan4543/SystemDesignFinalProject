import command.AddBook;
import command.Checkout;
import command.Command;
import command.Return;
import command.FindChecked;
import command.FindBorrower;
import command.ListAuthor;
import command.ListSubject;
import command.RemoveBook;
import controller.Library;
import controller.CommandFactory;
import exception.BookCheckoutException;
import exception.PermissionDeniedException;
import exception.ExceedLimitationException;
import object.Book;
import object.priority;
import object.User;
import java.io.*;
/**
 * Consider a small library system with the following transactions:
 * 1. Check out a copy of book/ command.Return a copy of a book
 * 2. Add a copy of a book to/ Remove a copy of a book from the library.
 * 3. Get the list of books by a particular author or in a particular subject area.
 * 4. Find out the list of books currently checked out by a particular borrower,
 * 5. Find out what borrower last checked out a particular copy of a book.
 *
 * There are two types of users : staff users and ordinary borrowers.
 * Transactions 1, 2, 4, and 5 are restricted to staff users,
 * except that ordinary borrowers can perform transaction 4
 * to find out the list of books currently borrowed by themselves.
 *
 * The system must also satisfy the following constraints:
 *  1. All copies in the library must be available for check-out or checked out.
 *  2. No copy of a book may be both available and checked out at the same time.
 *  3. A borrower may not have more than a pre-defined number of books checked out at one time.
 *
 * */
public class Main{
    public static void Init(Library lib, BufferedReader br) throws IOException{
        int numberOfBook = Integer.parseInt(br.readLine());
        for(int i = 0; i < numberOfBook;++i){
            String[] strArray = br.readLine().split("\\s+");
            Book currentBook = new Book(i,strArray[0],strArray[1]);
            lib.books.put(i,currentBook);
        }
        int numberOfUser = Integer.parseInt(br.readLine());
        for(int i = 0; i < numberOfUser;++i){
            String[] strArray = br.readLine().split("\\s+");
            User currentUser;
            if(strArray[0].equals("Staff")){
                currentUser = new User(priority.Staff,strArray[1]);
            }else{
                currentUser = new User(priority.Borrower,strArray[1],Integer.parseInt(strArray[2]));
            }
            lib.users.put(strArray[1],currentUser);
        }
    }

    public static void main(String[] args) throws IOException{
        Library lib = Library.getInstance();

        String filePath;
        if(args.length == 0){
            filePath = "sampleInput";
        }else{
            filePath = args[0];
        }

        File input = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(input));
        Init(lib,br);

        String line;
        while((line = br.readLine()) != null){
            lib.processTransaction(line,br);
        }
        br.close();
    }
}