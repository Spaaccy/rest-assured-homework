package tbc.tbcacademy.ge.data.models.shared.ergast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;



@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "driverId",
        "permanentNumber",
        "code",
        "url",
        "givenName",
        "familyName",
        "dateOfBirth",
        "nationality",
})
public class DriverShared {
    @JsonProperty("driverId")
    private String driverId;
    @JsonProperty("permanentNumber")
    private String permanentNumber;
    @JsonProperty("code")
    private String code;
    @JsonProperty("url")
    private String url;
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("familyName")
    private String familyName;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonProperty("nationality")
    private String nationality;
}
