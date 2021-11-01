package com.example.visitorservice.persistence;

import com.example.visitorservice.models.VisitorModel;
import com.example.visitorservice.exceptions.VisitorNotFoundException;
import com.example.visitorservice.exceptions.VisitorsNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Deprecated
public class VisitorPersistenceService {

    // TODO replace this implementation in the future with a real DB
    // TODO read about Concurrent Hash Map
    private final Map<Long, VisitorModel> visitors = new HashMap<>();

    public void addNewVisitorModel(VisitorModel visitorModel) {
        visitors.put(visitorModel.getId(), visitorModel);
    }

    @Deprecated
    public VisitorModel getVisitorModel(String visitorId) {
        if (visitors.containsKey(visitorId)) {
            return visitors.get(visitorId);
        } else {
            log.debug("Visitor id = '{}' was not found in the DB", visitorId);
            throw new VisitorNotFoundException(visitorId);
        }
    }

    @Deprecated
    public List<VisitorModel> getVisitorModels() {
        if (visitors.isEmpty()) {
            log.debug("No visitors were found in the DB");
            throw new VisitorsNotFoundException();
        } else {
            return new ArrayList<>(visitors.values());
        }
    }

    @Deprecated
    public void updateVisitorModel(Long visitorId, VisitorModel visitorModel) {
        if (visitors.containsKey(visitorId)) {
            visitors.replace(visitorId, visitorModel);
        } else {
            log.debug("Visitor id = '{}' was not found in the DB", visitorId);
            throw new VisitorNotFoundException(visitorId.toString());
        }
    }

    @Deprecated
    public void deleteVisitorModel(String visitorId) {
        if (visitors.containsKey(visitorId)) {
            visitors.remove(visitorId);
        } else {
            log.debug("Visitor id = '{}' was not found in the DB", visitorId);
            throw new VisitorNotFoundException(visitorId);
        }
    }
}
