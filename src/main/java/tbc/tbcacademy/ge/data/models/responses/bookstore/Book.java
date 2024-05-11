package tbc.tbcacademy.ge.data.models.responses.bookstore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "books",
})
public class Book {
    @JsonProperty("books")
    private List<BooksData> books;

    public int getBookSize () {
        return books.size();
    }
}
