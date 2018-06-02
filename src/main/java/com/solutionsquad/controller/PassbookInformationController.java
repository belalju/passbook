package com.solutionsquad.controller;

import com.solutionsquad.model.PassbookInformation;
import com.solutionsquad.service.PassbookService;
import com.solutionsquad.util.CommonConstant;
import com.solutionsquad.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/book")
public class PassbookInformationController {

    @Autowired
    PassbookService passbookService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> getAll(){
        RestResponse restResponse = new RestResponse();
        try{
            restResponse.setData(passbookService.getAll());
            restResponse.setMessageType(CommonConstant.MESSAGE_TYPE_SUCCESS);
            restResponse.setMessage("Data retrieval successful");
        }catch (Exception ex){
            restResponse.setMessageType(CommonConstant.MESSAGE_TYPE_FAILED);
            restResponse.setMessage(ex.getMessage());
        }

        return new ResponseEntity<RestResponse>(restResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RestResponse create(@Valid @RequestBody PassbookInformation passbookInformation, BindingResult result){
        RestResponse restResponse = new RestResponse();
        try{
            if(result.hasErrors()){
                restResponse.setMessageType(CommonConstant.MESSAGE_TYPE_FAILED);
                restResponse.setMessage(result.toString());
            }else{
                restResponse.setData(passbookService.add(passbookInformation));
                restResponse.setMessageType(CommonConstant.MESSAGE_TYPE_SUCCESS);
                restResponse.setMessage("Data saved successfully");
            }

        }catch (Exception ex){
            restResponse.setMessageType(CommonConstant.MESSAGE_TYPE_FAILED);
            restResponse.setMessage(ex.getMessage());
        }

        return restResponse;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> get(@PathVariable("id") Long id){
        RestResponse restResponse = new RestResponse();
        try{
            if(id != null){
                restResponse.setData(passbookService.get(id));
                restResponse.setMessageType(CommonConstant.MESSAGE_TYPE_SUCCESS);
                restResponse.setMessage("Data retrieval successful");
            }else{
                restResponse.setMessageType(CommonConstant.MESSAGE_TYPE_FAILED);
                restResponse.setMessage("Id can not be empty");
            }

        }catch (Exception ex){
            restResponse.setMessageType(CommonConstant.MESSAGE_TYPE_FAILED);
            restResponse.setMessage(ex.getMessage());
        }

        return new ResponseEntity<RestResponse>(restResponse, HttpStatus.OK);
    }
}
