package net.homenet.chapter02.repository;

import net.homenet.chapter02.domain.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CartRepository extends ReactiveCrudRepository<Cart, String> { }
