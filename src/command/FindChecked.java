package command;
import object.*;
import controller.Library;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class FindChecked implements Command{
    @Override
    public void execute(User invoker, String arg) throws RuntimeException{
        Library lib = Library.getInstance();
        User user = lib.users.get(arg);
        if(user == null){
            throw new NullPointerException("User "+arg+" does not exist.");
        }
        Stream<Book> stream = lib.books.values().stream();
        //一樣，過濾所有被這個使用者借的書，然後印出來
        stream.filter(book -> user.equals(book.getBorrowedBy())).forEach(book -> System.out.println("ID: "+book.getBookID()+" Author: "+book.getBookAuthor()+" Subject: "+book.getBookSubject()));
    }
}
