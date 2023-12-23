package command;
import object.*;
import controller.Library;
import java.util.stream.Stream;

public class FindChecked implements Command{
    User invoker;
    User user;
    public FindChecked(User invoker, User user) {
        this.invoker = invoker;
        this.user = user;
    }

    @Override
    public void execute() {
        Library lib = Library.getInstance();
        Stream<Book> stream = lib.books.values().stream();
        //一樣，過濾所有被這個使用者借的書，然後印出來
        stream.filter(book -> user.equals(book.getBorrowedBy())).forEach(book -> System.out.println("ID: "+book.getBookID()+" Author: "+book.getBookAuthor()+" Subject: "+book.getBookSubject()));
    }

    @Override
    public boolean canExecute(priority p) {
        return (p.equals(priority.Staff) || invoker.equals(user));
    }
}
