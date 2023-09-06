package com.perficient.hub.adapter.endpoints;

import com.perficient.hub.port.in.SendOrder;
import io.grpc.internal.testing.StreamRecorder;
import openapitools.GreetingOuterClass;
import openapitools.com.perficient.hub.adapter.endpoints.api.ordersservice.OrdersServiceOuterClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderEndpointTest {

    @Mock
    SendOrder sendOrderService;

    @InjectMocks
    OrderEndpoint orderEndpointService;

    @Test
    void generateOrder() {
        // given
        var BACK_END_RESPONSE = "Welcome, the order was register successfully";
        var orderRequest = OrdersServiceOuterClass.GenerateOrderRequest.newBuilder()
                .setGreeting(GreetingOuterClass.Greeting.newBuilder()
                        .setMessage("Hello I'm Ralph")
                        .build()
                ).build();
        StreamRecorder<GreetingOuterClass.Greeting> responseObserver = StreamRecorder.create();
        // when
        when(sendOrderService.placeOrder(any())).thenReturn(BACK_END_RESPONSE);
        orderEndpointService.generateOrder(orderRequest, responseObserver);

        // then

        assertNull(responseObserver.getError());
        var greetings = responseObserver.getValues();

        assertEquals(1, greetings.size());
        assertEquals(BACK_END_RESPONSE,greetings.get(0).getMessage());
    }
}