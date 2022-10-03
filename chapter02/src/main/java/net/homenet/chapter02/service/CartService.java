package net.homenet.chapter02.service;

import net.homenet.chapter02.domain.Cart;
import net.homenet.chapter02.domain.CartItem;
import net.homenet.chapter02.repository.CartRepository;
import net.homenet.chapter02.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CartService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public Mono<Cart> addToCart(String cartId, String id) {
        return cartRepository.findById(cartId)
            .defaultIfEmpty(new Cart(cartId))
            .flatMap(cart -> cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getItem().getId().equals(id))
                .findAny()
                .map(cartItem -> {
                    cartItem.increment();
                    return Mono.just(cart);
                })
                .orElseGet(() -> itemRepository.findById(id)
                    .map(CartItem::new)
                    .doOnNext(cartItem -> cart.getCartItems().add(cartItem))
                    .map(cartItem -> cart)
                ))
            .flatMap(cartRepository::save);
    }
}
