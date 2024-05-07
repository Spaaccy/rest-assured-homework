package tbc.tbcacademy.ge.data;
import org.testng.annotations.DataProvider;
import tbc.tbcacademy.ge.steps.booksteps.BookExtractionSteps;

public class DataProviders {
    private static final BookExtractionSteps bookExtractionSteps = new BookExtractionSteps();

    @DataProvider(name = "IsbnAuthorProviderUsingIndex")
    public static Object[][] ISBNProvider(){
        bookExtractionSteps.getBookResponseNoQuery();
        return new Object[][]{
                {bookExtractionSteps.extractBookISBNUsingIndex(0), bookExtractionSteps.extractBookAuthorUsingIndex(0)},
                {bookExtractionSteps.extractBookISBNUsingIndex(1), bookExtractionSteps.extractBookAuthorUsingIndex(1)},
        };
    }
}
