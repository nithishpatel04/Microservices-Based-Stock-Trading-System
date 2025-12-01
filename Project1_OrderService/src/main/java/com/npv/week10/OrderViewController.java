package com.npv.week10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class OrderViewController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;   // <-- Add this line

    @GetMapping("/orders/form")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order-form";
    }

    @PostMapping("/orders/form")
    public String submitOrderForm(@ModelAttribute("order") Order order, Model model) {
        Order saved = orderService.placeOrder(order);
        model.addAttribute("order", saved);
        return "order-success";
    }

    @GetMapping("/orders/list")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll()); // <-- FIXED
        return "order-list";
    }
}
