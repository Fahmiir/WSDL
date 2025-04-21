package com.example.demo.service;

import com.example.demo.dto.AddRequest;
import com.example.demo.dto.AddResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CalculatorEndpoint {

    private static final String NAMESPACE_URI = "http://www.example.com/calculator";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addRequest")
    @ResponsePayload
    public AddResponse add(@RequestPayload AddRequest request) {
        int sum = request.getNum1() + request.getNum2();
        AddResponse response = new AddResponse();
        response.setResult(sum);
        return response;
    }
}
