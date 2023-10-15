import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryTest {

    private Library library;

    @Before
    public void setUp() {
        library = new Library();
    }

    @Test
    public void testAddBook() {
        // Test adding books
    }

    @Test
    public void testRemoveBookByISBN() {
        // Test removing books by ISBN
    }

    @Test
    public void testRemoveBookByTitle() {
        // Test removing books by title
    }

    @Test
    public void testCheckOutBook() throws ParseException {
        // Test checking out a book
    }

    @Test
    public void testCheckInBook() {
        // Test checking in a book
    }

    // Helper methods for finding books
}
