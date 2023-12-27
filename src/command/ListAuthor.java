package command;
import controller.Library;
import object.*;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class ListAuthor implements Command{
    @Override
    public void execute(User invoker, String author) {
        Library lib = Library.getInstance();
        Stream<Book> stream = lib.books.values().stream();

        stream.filter(book -> book.getBookAuthor().equals(author)).forEach(book -> System.out.println("ID: "+book.getBookID()+" Author: "+book.getBookAuthor()+" Subject: "+book.getBookSubject()));

    }
}
