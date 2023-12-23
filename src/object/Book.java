package object;

import exception.BookCheckoutException;

import java.util.Objects;

public class Book {
    private final Integer bookID;
    private final String bookAuthor;
    private final String bookSubject;
    private boolean borrowed = false;
    private User borrowedBy = null;

    public Book(Integer bookID, String bookAuthor, String bookSubject) {
        this.bookID = bookID;
        this.bookAuthor = bookAuthor;
        this.bookSubject = bookSubject;
    }
    public void doBorrow(User user){
        if(this.isBorrowed()){
            throw new BookCheckoutException();
        }
        this.borrowed = true;
        this.borrowedBy = user;
    }
    public void doReturn(){
        if(this.isBorrowed()){
            this.borrowed = false;
            this.borrowedBy = null;
        }else{
            throw new BookCheckoutException();
        }
    }

    public Integer getBookID() {
        return bookID;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookSubject() {
        return bookSubject;
    }

    public User getBorrowedBy() {
        return borrowedBy;
    }

    public boolean isBorrowed() {
        return borrowed;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;
        Book book = (Book) o;
        return Objects.equals(bookID, book.bookID) && Objects.equals(bookAuthor, book.bookAuthor) && Objects.equals(bookSubject, book.bookSubject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID, bookAuthor, bookSubject);
    }
}
