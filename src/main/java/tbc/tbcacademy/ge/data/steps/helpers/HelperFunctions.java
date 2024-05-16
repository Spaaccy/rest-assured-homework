package tbc.tbcacademy.ge.data.steps.helpers;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.ZoneId;
import static tbc.tbcacademy.ge.data.steps.CommonSteps.faker;

public class HelperFunctions {
    public static XMLGregorianCalendar getRandomGregorianFormatDate() {
        XMLGregorianCalendar date = null;
        try {
            date = DatatypeFactory.newInstance().newXMLGregorianCalendar(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
        } catch(DatatypeConfigurationException ignored) {}
        return date;
    }
}
