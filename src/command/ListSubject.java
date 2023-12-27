package command;
import java.util.stream.Stream;
import object.Book;
import object.User;
import controller.Library;

@SuppressWarnings("all")
public class ListSubject implements Command{

    public ListSubject() {}

    @Override
    public void execute(User invoker, String subject) {
        Library lib = Library.getInstance();
        Stream<Book> stream = lib.books.values().stream();

        stream.filter(book -> book.getBookSubject().equals(subject)).forEach(book -> System.out.println("ID: "+book.getBookID()+" Author: "+book.getBookAuthor()+" Subject: "+book.getBookSubject()));
    }
}
