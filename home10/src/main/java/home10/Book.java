package home10;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Class to realize Serialization Proxy Pattern
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 2087368867376448459L;
    private String title;
    private String author;
    private int countSheets;

    public Book(String title, String author, int countSheets) {
        this.title = title;
        this.author = author;
        this.countSheets = countSheets;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCountSheets() {
        return countSheets;
    }

    public void setCountSheets(int countSheets) {
        this.countSheets = countSheets;
    }

    @Override
    public String toString() {
        return "Book: " + "title= " + title + ", author= " + author + ", count sheets=" + countSheets;
    }

    /**
     * Method replace Book to BookProxy before serialization
     * @return BookProxy object
     * @throws ObjectStreamException
     */
    private Object writeReplace() throws ObjectStreamException {
        return new BookProxy(this);
    }

    /**
     * Class wrapper
     */
    private static class BookProxy implements Serializable {

        private static final long serialVersionUID = 8333905273185436744L;
        private String data;

        public BookProxy(Book orig) {
            data = orig.getTitle() + "," + orig.getAuthor() + "," + orig.getCountSheets();
        }

        /**
         * Method resolve BookProxy to Book after deserialization
         * @return Book object
         * @throws ObjectStreamException
         */
        private Object readResolve() throws ObjectStreamException {
            String[] pieces = data.split(",");
            Book result = new Book(pieces[0], pieces[1], Integer.parseInt(pieces[2]));
            return result;
        }
    }
}
