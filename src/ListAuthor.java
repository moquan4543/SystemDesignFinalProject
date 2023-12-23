import java.util.stream.Stream;

public class ListAuthor implements Command{
    String author;

    public ListAuthor(String author) {
        this.author = author;
    }

    @Override
    public void execute() {
        Library lib = Library.getInstance();

        Stream<Book> stream = lib.books.values().stream();
        //對流式思想不太熟，怕他被關了，用鏈式寫我比較安心
        //首先filter過濾所有是這個作者的書，然後forEach迭代印出來
        stream.filter(book -> book.getBookAuthor().equals(author)).forEach(book -> System.out.println("ID: "+book.getBookID()+" Author: "+book.getBookAuthor()+" Subject: "+book.getBookSubject()));

    }
    @Override
    public boolean canExecute(priority p) {
        return true;
    }
}
