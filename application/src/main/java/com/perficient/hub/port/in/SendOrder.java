package com.perficient.hub.port.in;

import com.perficient.hub.domain.Order;

public interface SendOrder {

    String placeOrder(Order order);
}
