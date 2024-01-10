import controller.*;
import object.*;
import command.Command;
import command.ConfirmDecorator;
import command.CommandPermissionLevel;
import command.AddBook;
import command.Checkout;
import command.FindChecked;
import command.FindBorrower;
import command.ListAuthor;
import command.ListSubject;
import command.RemoveBook;
import command.Return;
import exception.*;
import java.io.*;
@SuppressWarnings("all")
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
                currentUser = new User(Permission.Staff,strArray[1]);
            }else{
                currentUser = new User(Permission.Borrower,strArray[1],Integer.parseInt(strArray[2]));
            }
            lib.users.put(strArray[1],currentUser);
        }
    }

    public static void main(String[] args) throws IOException{
        Library lib = Library.getInstance();
        File input = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(input));
        Init(lib,br);
        String line;
        while((line = br.readLine()) != null){
            lib.processTransaction(line,br);
        }
        br.close();
    }
}