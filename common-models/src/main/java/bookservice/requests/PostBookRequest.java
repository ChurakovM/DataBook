package bookservice.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBookRequest {

    @NotNull(message = "Author name cannot be null")
    @Size(min = 3, message = "Author Name length must be at least 3 character")
    private String authorName;

    @NotNull(message = "Book name cannot be null")
    @Size(min = 1, message = "Book Name length must be at least 1 character")
    private String bookName;
}
