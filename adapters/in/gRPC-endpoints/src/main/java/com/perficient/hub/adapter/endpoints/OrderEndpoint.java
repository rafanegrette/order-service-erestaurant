package com.perficient.hub.adapter.endpoints;

import com.perficient.hub.domain.Order;
import com.perficient.hub.port.in.SendOrder;
import io.grpc.stub.StreamObserver;
import openapitools.GreetingOuterClass;
import openapitools.com.perficient.hub.adapter.endpoints.api.ordersservice.OrdersServiceGrpc;
import openapitools.com.perficient.hub.adapter.endpoints.api.ordersservice.OrdersServiceOuterClass;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class OrderEndpoint extends OrdersServiceGrpc.OrdersServiceImplBase {

    private final SendOrder sendOrder;

    public OrderEndpoint(SendOrder sendOrder) {
        this.sendOrder = sendOrder;
    }

    @Override
    public void generateOrder(OrdersServiceOuterClass.GenerateOrderRequest request, StreamObserver<GreetingOuterClass.Greeting> responseObserver) {
        var greetingBuilder = GreetingOuterClass.Greeting.newBuilder();
        var order = new Order(request.getGreeting().getMessage());
        var orderStatus = sendOrder.placeOrder(order);
        greetingBuilder.setMessage(orderStatus);
        responseObserver.onNext(greetingBuilder.build());
        responseObserver.onCompleted();
    }
}
