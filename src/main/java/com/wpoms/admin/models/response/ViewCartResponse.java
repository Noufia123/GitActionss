package com.wpoms.admin.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewCartResponse {
    private int cartId;
    private List<CartItemDetail> items;
    private double totalAmount;

    @Data
    @AllArgsConstructors
    @NoArgsConstructor
    public static class CartItemDetail {
        private int cartItemId;
        private int productId;
        private String productName;
        private String manufacturer;
        private double price;
        private int quantity;
        private double subtotal;
    }
}
<<<<<<< HEAD
=======


>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
