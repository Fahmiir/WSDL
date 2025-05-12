package com.example.demo.service;

import com.example.demo.dto.SubRequest;
import com.example.demo.dto.SubResponse;
import org.springframework.ws.server.endpoint.annotation.*;

@Endpoint
public class SubEndpoint {

    private static final String NAMESPACE_URI = "http://www.example.com/calculator2";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "subRequest")
    @ResponsePayload
    public SubResponse handleSubRequest(@RequestPayload SubRequest request) {
        SubResponse response = new SubResponse();
        response.setResult(request.getNum1() - request.getNum2());
        return response;
    }
}
