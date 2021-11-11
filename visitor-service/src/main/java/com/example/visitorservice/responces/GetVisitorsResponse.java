package com.example.visitorservice.responces;

import com.example.visitorservice.models.VisitorModel;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetVisitorsResponse {

    private List<VisitorModel> listOfVisitors;
}
