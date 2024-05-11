package tbc.tbcacademy.ge.data.models.requests.petv3;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "complete",
        "petId",
        "quantity",
        "shipDate",
        "status",
        "id",
})
@Accessors(chain = true, fluent = true)
public class OrderRequest {
    @JsonProperty("quantity")
    private int petQuantity;
    @JsonProperty("id")
    private long id;
    @JsonProperty("petId")
    private long petId;
    @JsonProperty("status")
    private String petStatus;
    @JsonProperty("complete")
    private boolean complete;
    @JsonProperty("shipDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING)
    private LocalDateTime shippingDate;
}