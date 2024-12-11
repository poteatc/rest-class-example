package com.pluralsight.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private List<Product> products = new ArrayList<>();

    @Autowired
    public ProductController(List<Product> products) {
        this.products = products;
        this.products.add( new Product(1, "Laptop", "Electronics", 1200.00));
        this.products.add(new Product(2, "Headphones", "Audio", 150.00));
        this.products.add(new Product(3, "Coffee Maker", "Appliances", 80.00));
    }

    public Product findById(int productId) {
        return new Product();
    }

//    List<Product> findAll() {
//        List<Product> results = List.of(
//                new Product(),
//                new Product()
//        );
//        return results;
//    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", products);
        return "product_list"; // Matches the file name 'product_list.html'
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam("productId") int productId,
                             @RequestParam("name") String name,
                             @RequestParam("category") String category,
                             @RequestParam("price") double price,
                             Model model) {

        // Create a new product and add it to the list
        Product newProduct = new Product(productId, name, category, price);
        products.add(newProduct);

        // Update the model with the updated product list
        model.addAttribute("products", products);

        // Redirect to the product list page
        return "redirect:/products";
    }
}
