package command;

import java.util.stream.Stream;
import object.Book;
import object.priority;
import controller.Library;
public class ListSubject implements Command{
    String subject;

    public ListSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void execute() {
        Library lib = Library.getInstance();
        Stream<Book> stream = lib.books.values().stream();
        //跟ListAuthor一樣
        stream.filter(book -> book.getBookSubject().equals(subject)).forEach(book -> System.out.println("ID: "+book.getBookID()+" Author: "+book.getBookAuthor()+" Subject: "+book.getBookSubject()));
    }
    @Override
    public boolean canExecute(priority p) {
        return true;
    }
}
