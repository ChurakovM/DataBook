package visitorservice.responses;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetVisitorsResponse {

    private List<?> listOfVisitors;
}
