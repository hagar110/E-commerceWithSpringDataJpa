package com.example.EcommerceWithSpringDataJpa.service.product;

import com.example.EcommerceWithSpringDataJpa.entity.Category;
import com.example.EcommerceWithSpringDataJpa.entity.Product;
import com.example.EcommerceWithSpringDataJpa.repository.product.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepoImpl) {
        this.productRepository = productRepoImpl;

    }

    /**
     * @InheritedDoc
     */
    @Override
    public void addProduct(Product product){
        if(product==null){
            throw new NullPointerException();
        }
       // if(productRepository.getByName(product.getName())!=null)
          //  throw new IllegalArgumentException("name already exists");
        product.setName(product.getName().toLowerCase());
        productRepository.save(product);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public void updateProduct(Product product) {//updates all except category

        if(product==null){
            throw new NullPointerException();
        }
        Optional<Product> productFromDb=productRepository.findById(product.getId());
        if(!productFromDb.isPresent()) {
            throw new NoSuchElementException("product doesn't exist");
        }
        product.setName(product.getName().toLowerCase());
        product.setCategory(productFromDb.get().getCategory());
        productRepository.save(product);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public void deleteProductById(Long productId) {
        if(productId <= -1)
            throw new IllegalArgumentException("invalid id");
        Optional<Product> product=productRepository.findById(productId);
        if(!product.isPresent())
            throw new NoSuchElementException("no product with the given id");
        deleteProduct(product.get());

    }

    private void deleteProduct(Product product) {
        if(product==null){
            throw new NullPointerException();
        }

        productRepository.delete(product);
    }



    /**
     * @InheritedDoc
     */
    @Transactional
    @Override
    public void updateProductQuantity(Long productId, int quantity) {
         if(productId<0 || quantity<0){
            throw new IllegalArgumentException("invalid product or quantity");
        }
        Optional<Product> product=productRepository.findById(productId);
        if(!product.isPresent())
            throw new NoSuchElementException("no product with the given id");
        productRepository.updateProductQuantity(productId, quantity);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /**
     * @InheritedDoc
     */
    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        if(categoryId<0){
            throw new IllegalArgumentException();
        }
        return productRepository.getProductsByCategory(categoryId);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Product getProductById(Long productId) {
        if(productId<0){
            throw new IllegalArgumentException("invalid id");
        }
        Optional<Product> product=productRepository.findById(productId);
        if(!product.isPresent())
            throw new NoSuchElementException("no product with the given id");

        return product.get();
    }


    /**
     * @InheritedDoc
     */
    @Override
    public List<Product> searchByProductName(String productName) {
        if(productName==null){
            throw new NullPointerException();
        }
        else if(productName.isBlank()){
            throw new IllegalArgumentException();
        }
        return productRepository.searchByProductName(productName,"%" + productName + "%");
    }


}