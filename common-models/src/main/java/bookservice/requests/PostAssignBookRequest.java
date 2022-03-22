package bookservice.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostAssignBookRequest {

    private String id, visitorId;
}