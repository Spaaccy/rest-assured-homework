package tbc.tbcacademy.ge.data.helpers;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static tbc.tbcacademy.ge.data.steps.CommonSteps.faker;

public class HelperFunctions {
    public static XMLGregorianCalendar getRandomGregorianFormatDate() {
        XMLGregorianCalendar date = null;
        try {
            date = DatatypeFactory.newInstance().newXMLGregorianCalendar(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
        } catch(DatatypeConfigurationException ignored) {}
        return date;
    }
    public static XMLGregorianCalendar formatIntoGregorianDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        XMLGregorianCalendar xmlCalendar = null;

        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
        } catch (DatatypeConfigurationException ignored) {}
        return xmlCalendar;
    }
}
