package tbc.tbcacademy.ge.data.models.responses.faker;
import lombok.Data;
import java.util.List;

@Data
public class CreditCardResponse {
    private String status;
    private int code;
    private int total;
    private List<CreditCard> data;
}

