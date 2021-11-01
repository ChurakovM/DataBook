package com.example.visitorservice.responces;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetVisitorsResponse {

    private List<GetVisitorResponse> listOfVisitors;
}
