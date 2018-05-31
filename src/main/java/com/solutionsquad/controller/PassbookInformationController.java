package com.solutionsquad.controller;

import com.solutionsquad.util.CommonConstant;
import com.solutionsquad.util.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/book")
public class PassbookInformationController {

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> getAll(){
        RestResponse restResponse = new RestResponse();
        restResponse.setMessageType(CommonConstant.MESSAGE_TYPE_SUCCESS);
        restResponse.setMessage("Parsing Successful");
        restResponse.setData(new Date().toString());
        return new ResponseEntity<RestResponse>(restResponse, HttpStatus.OK);
    }
}
