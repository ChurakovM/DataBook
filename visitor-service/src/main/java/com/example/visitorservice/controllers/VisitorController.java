package com.example.visitorservice.controllers;

import com.example.visitorservice.models.VisitorModel;
import com.example.visitorservice.services.VisitorService;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import visitorservice.queries.VisitorContactQueryParameters;
import visitorservice.requests.PostVisitorRequest;
import visitorservice.requests.PutVisitorRequest;
import visitorservice.responses.GetVisitorResponse;
import visitorservice.responses.GetVisitorsResponse;
import visitorservice.responses.PostVisitorResponse;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("visitors")// http://localhost:8080/visitors
public class VisitorController {

    private static final String VISITOR_ID_PATH = "/{visitorId}";
    // TODO read about reflection
    private final VisitorService visitorService;

    @PostMapping(
        consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PostVisitorResponse> createVisitor(
        @Valid @RequestBody PostVisitorRequest postVisitorRequest) {
        PostVisitorResponse postVisitorResponse = visitorService.createVisitor(postVisitorRequest);
        return new ResponseEntity<>(postVisitorResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = VISITOR_ID_PATH,
        consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GetVisitorResponse> getVisitor(@PathVariable String visitorId) {
        GetVisitorResponse getVisitorResponse = visitorService.getVisitor(visitorId);
        return new ResponseEntity<>(getVisitorResponse, HttpStatus.OK);
    }

    @GetMapping(
        consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GetVisitorsResponse> getVisitors(
        @RequestParam(value = "firstName", defaultValue = "", required = false) String firstName,
        @RequestParam(value = "lastName", defaultValue = "", required = false) String lastName,
        @RequestParam(value = "email", defaultValue = "", required = false) String email,
        @RequestParam(value = "phoneNumber", defaultValue = "", required = false) String phoneNumber) {
        VisitorContactQueryParameters queries = new VisitorContactQueryParameters(firstName, lastName,
            email, phoneNumber);
        log.debug(
            "Get visitor with the following query parameters, firstName = {}, lastName = {}, email = {} and phoneNumber = {}",
            firstName, lastName, email, phoneNumber);
        GetVisitorsResponse getVisitorsResponse = visitorService.getVisitors(queries);
        return new ResponseEntity<>(getVisitorsResponse, HttpStatus.OK);
    }

    @PutMapping(path = VISITOR_ID_PATH,
        consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<VisitorModel> updateVisitor(@PathVariable String visitorId,
        @Valid @RequestBody PutVisitorRequest putVisitorRequest) {
        VisitorModel visitorModel = visitorService.updateVisitor(visitorId, putVisitorRequest);
        return new ResponseEntity<>(visitorModel, HttpStatus.OK);
    }

    @DeleteMapping(path = VISITOR_ID_PATH)
    public ResponseEntity<VisitorModel> deleteVisitor(@PathVariable String visitorId) {
        visitorService.deleteVisitor(visitorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
