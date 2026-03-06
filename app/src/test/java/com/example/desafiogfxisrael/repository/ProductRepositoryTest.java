package com.example.desafiogfxisrael.repository;

import com.example.desafiogfxisrael.domain.Category;
import com.example.desafiogfxisrael.domain.Product;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductRepositoryTest {

    @Test
    public void filterByCategory_returnsOnlyMatchingCategory() {
        ProductRepository repository = new ProductRepository(null);
        List<Product> products = Arrays.asList(
                new Product(1, "Item A", 10.0, "Desc", Category.MENS_CLOTHING, ""),
                new Product(2, "Item B", 15.0, "Desc", Category.JEWELERY, ""),
                new Product(3, "Item C", 20.0, "Desc", Category.MENS_CLOTHING, "")
        );

        List<Product> filtered = repository.filterByCategory(products, Category.MENS_CLOTHING);

        assertEquals(2, filtered.size());
        assertTrue(filtered.stream().allMatch(product -> product.getCategory() == Category.MENS_CLOTHING));
    }

    @Test
    public void filterByCategory_returnsEmptyWhenNoMatches() {
        ProductRepository repository = new ProductRepository(null);
        List<Product> products = Arrays.asList(
                new Product(1, "Item A", 10.0, "Desc", Category.JEWELERY, ""),
                new Product(2, "Item B", 15.0, "Desc", Category.JEWELERY, "")
        );

        List<Product> filtered = repository.filterByCategory(products, Category.ELECTRONICS);

        assertTrue(filtered.isEmpty());
    }

    @Test
    public void filterByCategory_handlesEmptyInputList() {
        ProductRepository repository = new ProductRepository(null);

        List<Product> filtered = repository.filterByCategory(new ArrayList<>(), Category.ELECTRONICS);

        assertTrue(filtered.isEmpty());
    }
}
