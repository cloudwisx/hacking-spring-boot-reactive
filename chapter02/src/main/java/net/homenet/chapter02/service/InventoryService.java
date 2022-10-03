package net.homenet.chapter02.service;

import net.homenet.chapter02.domain.Item;
import net.homenet.chapter02.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class InventoryService {

    private final ItemRepository itemRepository;
    private final ReactiveFluentMongoOperations operations;

    @Autowired
    public InventoryService(ItemRepository itemRepository, ReactiveFluentMongoOperations operations) {
        this.itemRepository = itemRepository;
        this.operations = operations;
    }

    public Flux<Item> searchByExample(String name, String description, boolean useAnd) {
        Example<Item> probe = createItemExample(name, description, useAnd);
        return itemRepository.findAll(probe);
    }

    @SuppressWarnings("unused")
    public Flux<Item> searchByFluentExample(String name, String description, boolean useAnd) {
        Example<Item> probe = createItemExample(name, description, useAnd);
        return operations.query(Item.class)
            .matching(query(byExample(probe)))
            .all();
    }

    private Example<Item> createItemExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, null, description);
        ExampleMatcher matcher = (useAnd ? ExampleMatcher.matchingAll() : ExampleMatcher.matchingAny())
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
            .withIgnoreCase()
            .withIgnorePaths("price");

        return Example.of(item, matcher);
    }

}
