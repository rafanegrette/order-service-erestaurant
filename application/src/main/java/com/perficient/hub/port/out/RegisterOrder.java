package com.perficient.hub.port.out;

import com.perficient.hub.domain.Order;

public interface RegisterOrder {
    String saveOrder(Order order);
}
