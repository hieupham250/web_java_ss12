package com.example.ss12.controller;

import com.example.ss12.dto.ProductDTO;
import com.example.ss12.model.Product;
import com.example.ss12.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String showProductList(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "productList";
    }

    @GetMapping("/addProduct")
    public String showAddForm(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("productDTO") ProductDTO productDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "addProduct";
        }

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        productService.create(product);

        return "redirect:/products";
    }

    @GetMapping("/updateProduct/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/products";
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setImage(product.getImage());

        model.addAttribute("productDTO", productDTO);
        return "updateProduct";
    }

    @PostMapping("/updateProduct")
    public String update(@Valid @ModelAttribute("productDTO") ProductDTO productDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "updateProduct";
        }

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        productService.update(product);

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
