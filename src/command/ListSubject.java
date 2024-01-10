package command;
import java.util.stream.Stream;
import object.Book;
import object.User;
import controller.Library;

@SuppressWarnings("all")
public class ListSubject implements Command{

    public ListSubject() {}
    private CommandPermissionLevel p = CommandPermissionLevel.Borrower;
    @Override
    public void execute(User invoker, String subject) {
        Library lib = Library.getInstance();
        Stream<Book> stream = lib.books.values().stream();
        stream.filter(book -> book.getBookSubject().equals(subject)).forEach(book -> System.out.println("ID: "+book.getBookID()+" Author: "+book.getBookAuthor()+" Subject: "+book.getBookSubject()));
    }
    @Override
    public CommandPermissionLevel getCommandPermissionLevel() {
        return p;
    }
    @Override
    public String getPermissionDeniedMsg() {
        return "";
    }
}
