package tbc.tbcacademy.ge.data.dataproviders;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class DataProviders {
    Faker faker = new Faker();

    @DataProvider
    public Object[][] passwordProvider(){
        return new Object[][]{
                {faker.lorem().characters(8)},
                {faker.letterify("????????")},
                {faker.numerify("########")},
                {faker.bothify("???##???")},
                {faker.letterify("????????").toLowerCase()},
                {faker.letterify("????????").toUpperCase()},
                {"!@#$%^&*"}
        };
    }
}
