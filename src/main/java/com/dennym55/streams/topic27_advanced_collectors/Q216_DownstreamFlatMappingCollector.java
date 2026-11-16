package com.dennym55.streams.topic27_advanced_collectors;

import com.dennym55.streams.support.PracticeData.Order;
import com.dennym55.streams.support.PracticeData.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Q216: Use Collectors.flatMapping() downstream of groupingBy() to collect every product name
 * ordered by customer.
 *
 * <p>Input: multiple orders, including two orders for Asha.
 * Expected output: each customer maps to a sorted, duplicate-free set of product names.
 */
public class Q216_DownstreamFlatMappingCollector {

    public static Map<String, Set<String>> productNamesByCustomer(List<Order> orders) {
        return orders.stream().collect(Collectors.groupingBy(
                Order::customer,
                Collectors.flatMapping(
                        order -> order.products().stream().map(Product::name),
                        Collectors.toCollection(TreeSet::new))));
    }

    public static void main(String[] args) {
        Product keyboard = new Product(1, "Keyboard", "Tech", 70);
        Product book = new Product(2, "Book", "Books", 25);
        Product mouse = new Product(3, "Mouse", "Tech", 35);
        List<Order> input = List.of(
                new Order(1, "Asha", List.of(keyboard, book)),
                new Order(2, "Ben", List.of(mouse)),
                new Order(3, "Asha", List.of(mouse, keyboard)));

        System.out.println("Input: " + input);
        System.out.println("Output: " + productNamesByCustomer(input));
    }
}
