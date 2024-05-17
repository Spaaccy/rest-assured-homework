package tbc.tbcacademy.ge.data.models.responses.faker;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreditCard {
    private String type;
    private String number;
    private String expiration;

    @JsonProperty("owner")
    private String cardOwner;
}
