package com.api.verba.service;

import com.api.verba.dto.product.ProductRequest;
import com.api.verba.dto.product.ProductResponse;
import com.api.verba.model.Product;
import com.api.verba.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public ProductResponse createProduct(ProductRequest request){

        Product newProduct = new Product();

        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setAmount(request.getAmount());
        newProduct.setValue(request.getValue());
        newProduct.setProductType(request.getProductType());

        productRepository.save(newProduct);

        ProductResponse response = new ProductResponse();
        response.setId(newProduct.getId());
        response.setName(newProduct.getName());
        response.setDescription(newProduct.getDescription());
        response.setAmount(newProduct.getAmount());
        response.setValue(newProduct.getValue());
        response.setProductType(newProduct.getProductType());

        return response;
    }
}
