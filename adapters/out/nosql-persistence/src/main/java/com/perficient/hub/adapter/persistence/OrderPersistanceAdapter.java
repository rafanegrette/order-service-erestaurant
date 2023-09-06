package com.perficient.hub.adapter.persistence;

import com.perficient.hub.domain.Order;
import com.perficient.hub.port.out.RegisterOrder;
import org.springframework.stereotype.Repository;

@Repository
public class OrderPersistanceAdapter implements RegisterOrder {
    @Override
    public String saveOrder(Order order) {

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }

        return "buu Order save::";
    }
}
