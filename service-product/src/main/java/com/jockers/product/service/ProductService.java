package com.jockers.product.service;


import com.jockers.product.entity.Category;
import com.jockers.product.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProducts();

    Product getProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    Product deleteProduct(Long id);

    List<Product> findByCategory(Category category);

    Product updateStock(Long id, Double quantity);
}
