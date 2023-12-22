package com.example.spring_main_basic.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
