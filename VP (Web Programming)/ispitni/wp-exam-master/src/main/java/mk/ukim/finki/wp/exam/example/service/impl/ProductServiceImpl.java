package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.Product;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidProductIdException;
import mk.ukim.finki.wp.exam.example.repository.CategoryRepository;
import mk.ukim.finki.wp.exam.example.repository.ProductRepository;
import mk.ukim.finki.wp.exam.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> listAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(InvalidProductIdException::new);
    }

    @Override
    public Product create(String name, Double price, Integer quantity, List<Long> categories) {
        List<Category> categoryList = this.categoryRepository.findAllById(categories);
        Product product = new Product(name, price, quantity, categoryList);
        return this.productRepository.save(product);
    }

    @Override
    public Product update(Long id, String name, Double price, Integer quantity, List<Long> categories) {
        Product product = findById(id);
        List<Category> categoryList = this.categoryRepository.findAllById(categories);
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategories(categoryList);
        return this.productRepository.save(product);
    }

    @Override
    public Product delete(Long id) {
        Product product = findById(id);
        this.productRepository.delete(product);
        return product;
    }

    @Override
    public List<Product> listProductsByNameAndCategory(String name, Long categoryId) {
        if (name != null && categoryId != null){
            Category category = this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
            return this.productRepository.findAllByNameLikeAndCategoriesContaining("%"+name+"%", category);
        }else if(name == null && categoryId == null){
            return listAllProducts();
        }
        else if (categoryId == null){
            return this.productRepository.findAllByNameLike("%"+name+"%");
        }else {
            Category category = this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
            return this.productRepository.findAllByCategoriesContaining(category);
        }
    }
}