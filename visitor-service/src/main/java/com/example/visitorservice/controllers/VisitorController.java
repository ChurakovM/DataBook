package com.example.visitorservice.controllers;

import com.example.visitorservice.models.VisitorModel;
import com.example.visitorservice.models.VisitorContactQueryParameters;
import com.example.visitorservice.requests.PostVisitorRequest;
import com.example.visitorservice.requests.PutVisitorRequest;
import com.example.visitorservice.responces.GetVisitorResponse;
import com.example.visitorservice.responces.GetVisitorsResponse;
import com.example.visitorservice.responces.PostVisitorResponse;
import com.example.visitorservice.services.VisitorService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("visitors")// http://localhost:8080/visitors
public class VisitorController {

    private static final String VISITOR_ID_PATH = "/{visitorId}";

    // TODO read about reflection
    private final VisitorService visitorService;

    // TODO test this request with XML
    @PostMapping(
        consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PostVisitorResponse> createVisitor(@Valid @RequestBody PostVisitorRequest postVisitorRequest) {
        PostVisitorResponse postVisitorResponse = visitorService.createVisitor(postVisitorRequest);
        return new ResponseEntity<>(postVisitorResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = VISITOR_ID_PATH,
        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GetVisitorResponse> getVisitor(@PathVariable Long visitorId) {
        GetVisitorResponse getVisitorResponse = visitorService.getVisitor(visitorId);
        return new ResponseEntity<>(getVisitorResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetVisitorsResponse> getVisitors(
        @RequestParam(value = "firstNameQuery", defaultValue = "", required = false) String firstNameQuery,
        @RequestParam(value = "lastNameQuery", defaultValue = "", required = false) String lastNameQuery,
        @RequestParam(value = "emailQuery", defaultValue = "", required = false) String emailQuery,
        @RequestParam(value = "phoneNumberQuery", defaultValue = "", required = false) String phoneNumberQuery) {
        VisitorContactQueryParameters queries = new VisitorContactQueryParameters(firstNameQuery, lastNameQuery, emailQuery,
            phoneNumberQuery);
        GetVisitorsResponse getVisitorsResponse = visitorService.getVisitors(queries);
        return new ResponseEntity<>(getVisitorsResponse, HttpStatus.OK);
    }

    // TODO make it work
//    @PutMapping(path = VISITOR_ID_PATH,
//        consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
//        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
//    )
//    public ResponseEntity<VisitorModel> updateVisitor(@PathVariable Long visitorId,
//        @Valid @RequestBody PutVisitorRequest putVisitorRequest) {
//        VisitorModel visitorModel = visitorService.updateVisitor(visitorId, putVisitorRequest);
//        return new ResponseEntity<>(visitorModel, HttpStatus.OK);
//    }

    @DeleteMapping(path = VISITOR_ID_PATH)
    public ResponseEntity<VisitorModel> deleteVisitor(@PathVariable Long visitorId) {
        visitorService.deleteVisitor(visitorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
