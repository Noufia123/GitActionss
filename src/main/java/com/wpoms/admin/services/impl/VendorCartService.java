package com.wpoms.admin.services.impl;

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
import com.wpoms.admin.models.entities.Cart;
import com.wpoms.admin.models.entities.CartItem;
import com.wpoms.admin.models.entities.ManufacturerMaster;
import com.wpoms.admin.models.entities.Product;
import com.wpoms.admin.models.payloads.AddToCartPayload;
import com.wpoms.admin.models.response.AddToCartResponse;
import com.wpoms.admin.models.response.AddToCartResponse.CartItemInfo;
import com.wpoms.admin.models.response.RemoveCartItemResponse;
import com.wpoms.admin.models.response.ViewCartResponse;
import com.wpoms.admin.models.response.ViewCartResponse.CartItemDetail;
import com.wpoms.admin.repositories.CartItemRepository;
import com.wpoms.admin.repositories.CartRepository;
import com.wpoms.admin.repositories.ManufacturerMasterRepository;
import com.wpoms.admin.repositories.ProductRepository;
import com.wpoms.admin.services.IVendorCartService;

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
@Service
public class VendorCartService implements IVendorCartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManufacturerMasterRepository manufacturerRepository;

=======

@Service
public class VendorCartService implements IVendorCartService {


    @Autowired
    private CartRepository cartRepository;


    @Autowired
    private CartItemRepository cartItemRepository;


    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ManufacturerMasterRepository manufacturerRepository;


>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
    // ========================= ADD TO CART =========================
    @Override
    @Transactional
    public AddToCartResponse addToCart(int vendorId, AddToCartPayload payload) {

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
        // 1. Get or create cart for vendor
        Cart cart = cartRepository.findByVendorId(vendorId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setVendorId(vendorId);
                    newCart.setCreatedAt(LocalDateTime.now());
                    return cartRepository.save(newCart);
                });

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
        // 2. Validate product exists
        Product product = productRepository.findById(payload.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
        // 3. Check stock
        Optional<CartItem> existingItem = cartItemRepository
                .findByCartIdAndProductId(cart.getCartId(), payload.getProductId());

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
        int newQuantity;
        if (existingItem.isPresent()) {
            // Update existing item quantity
            CartItem item = existingItem.get();
<<<<<<< HEAD
            newQuantity = item.getQuantity() + payload.getQuantity();
=======
            newQuantity = payload.getQuantity();

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3

            if (newQuantity > product.getStockQuantity()) {
                throw new RuntimeException("Insufficient stock available");
            }

<<<<<<< HEAD
            item.setQuantity(newQuantity);
            CartItem savedItem = cartItemRepository.save(item);

=======

            item.setQuantity(newQuantity);
            CartItem savedItem = cartItemRepository.save(item);


>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
            CartItemInfo cartItemInfo = new CartItemInfo();
            cartItemInfo.setCartItemId(savedItem.getCartItemId());
            cartItemInfo.setProductId(product.getProductId());
            cartItemInfo.setQuantity(newQuantity);
            cartItemInfo.setSubtotal(product.getPrice() * newQuantity);
<<<<<<< HEAD
=======
            cartItemInfo.setProductName(product.getProductName());

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3

            AddToCartResponse response = new AddToCartResponse();
            response.setMessage("Cart quantity updated");
            response.setCartItem(cartItemInfo);
            return response;

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
        } else {
            // Add new item
            newQuantity = payload.getQuantity();

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
            if (newQuantity > product.getStockQuantity()) {
                throw new RuntimeException("Insufficient stock available");
            }

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
            CartItem newItem = new CartItem();
            newItem.setCartId(cart.getCartId());
            newItem.setProductId(payload.getProductId());
            newItem.setQuantity(newQuantity);
            newItem.setAddedAt(LocalDateTime.now());

<<<<<<< HEAD
            CartItem savedItem = cartItemRepository.save(newItem);

=======

            CartItem savedItem = cartItemRepository.save(newItem);


>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
            CartItemInfo cartItemInfo = new CartItemInfo();
            cartItemInfo.setCartItemId(savedItem.getCartItemId());
            cartItemInfo.setProductId(product.getProductId());
            cartItemInfo.setProductName(product.getProductName());
            cartItemInfo.setQuantity(newQuantity);
            cartItemInfo.setPrice(product.getPrice());

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
            AddToCartResponse response = new AddToCartResponse();
            response.setMessage("Product added to cart");
            response.setCartItem(cartItemInfo);
            return response;
        }
    }

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
    // ========================= VIEW CART =========================
    @Override
    public ViewCartResponse viewCart(int vendorId) {

<<<<<<< HEAD
        Cart cart = cartRepository.findByVendorId(vendorId)
                .orElseThrow(() -> new RuntimeException("Cart not found for vendor"));

        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getCartId());

        List<CartItemDetail> itemDetails = new ArrayList<>();
        double totalAmount = 0;

=======

        Cart cart = cartRepository.findByVendorId(vendorId)
                .orElseThrow(() -> new RuntimeException("Cart not found for vendor"));


        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getCartId());


        List<CartItemDetail> itemDetails = new ArrayList<>();
        double totalAmount = 0;


>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
        for (CartItem item : cartItems) {
            Product product = productRepository.findById(item.getProductId()).orElse(null);
            if (product == null) continue;

<<<<<<< HEAD
            ManufacturerMaster manufacturer = manufacturerRepository
                    .findById(product.getManufacturerId()).orElse(null);

            double subtotal = product.getPrice() * item.getQuantity();

=======

            ManufacturerMaster manufacturer = manufacturerRepository
                    .findById(product.getManufacturerId()).orElse(null);


            double subtotal = product.getPrice() * item.getQuantity();


>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
            CartItemDetail detail = new CartItemDetail();
            detail.setCartItemId(item.getCartItemId());
            detail.setProductId(product.getProductId());
            detail.setProductName(product.getProductName());
            detail.setManufacturer(manufacturer != null ? manufacturer.getCompanyName() : "Unknown");
            detail.setPrice(product.getPrice());
            detail.setQuantity(item.getQuantity());
            detail.setSubtotal(subtotal);

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
            itemDetails.add(detail);
            totalAmount += subtotal;
        }

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
        ViewCartResponse response = new ViewCartResponse();
        response.setCartId(cart.getCartId());
        response.setItems(itemDetails);
        response.setTotalAmount(totalAmount);

<<<<<<< HEAD
        return response;
    }

=======

        return response;
    }


>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
    // ========================= REMOVE ITEM FROM CART =========================
    @Override
    @Transactional
    public RemoveCartItemResponse removeItem(int cartItemId) {

<<<<<<< HEAD
=======

>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new RuntimeException("Cart item not found");
        }

<<<<<<< HEAD
        cartItemRepository.deleteById(cartItemId);

=======

        cartItemRepository.deleteById(cartItemId);


>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
        RemoveCartItemResponse response = new RemoveCartItemResponse();
        response.setMessage("Item removed from cart");
        return response;
    }
}
<<<<<<< HEAD
=======


>>>>>>> f1162e77d936e9e9169a77427f042d2df3bebce3
