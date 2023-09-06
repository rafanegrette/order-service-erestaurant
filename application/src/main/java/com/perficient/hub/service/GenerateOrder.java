package com.perficient.hub.service;

import com.perficient.hub.domain.Order;
import com.perficient.hub.port.in.SendOrder;
import com.perficient.hub.port.out.RegisterOrder;
import org.springframework.stereotype.Service;

@Service
public class GenerateOrder implements SendOrder {

    private final RegisterOrder registerOrder;

    public GenerateOrder (RegisterOrder registerOrder) {
        this.registerOrder = registerOrder;
    }

    @Override
    public String placeOrder(Order order) {

        var result = registerOrder.saveOrder(order);
        return result = order.message()  + "Business rules applied";
    }
}
