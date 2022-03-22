package visitorservice.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostVisitorResponse {

    private String id, firstName, lastName, email, phoneNumber;
}
