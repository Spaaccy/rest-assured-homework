package tbc.tbcacademy.ge.data.dataproviders;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;


public class DataProviders {
    Faker faker = new Faker();
    @DataProvider
    public Object[][] bookingData() {
        return new Object [][]{
                {faker.name().firstName(), faker.name().lastName(), faker.number().numberBetween(50, 100), faker.bool().bool(), faker.number().numberBetween(50, 100), "2025-10-20", "2025-10-20", "Cat", null},
                {faker.name().firstName(), faker.name().lastName(), faker.number().numberBetween(50, 100), faker.bool().bool(), faker.number().numberBetween(50, 100), "2025-10-20", "2025-10-20", "Cat", null},
        };
    }
}
