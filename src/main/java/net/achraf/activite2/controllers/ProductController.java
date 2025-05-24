package net.achraf.activite2.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import net.achraf.activite2.model.Product;
import net.achraf.activite2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public String toIndex(Model model) {
        return "redirect:/user/index";
    }

    @GetMapping("/user/index")
    @PreAuthorize("hasRole('USER')")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("productList", products);
       return "products";
    }

    @PostMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }


    @GetMapping("/admin/newProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";
    }
    @PostMapping("/admin/saveProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "new-product";
        }
        productRepository.save(product);
        return "redirect:/admin/newProduct";

    }

    @PostMapping("/admin/updateProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateProduct(@Valid Product updatedProduct, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "new-product";
        }

        Optional<Product> updatedProductOptional = productRepository.findById(updatedProduct.getId());
        if (updatedProductOptional.isEmpty()){
            model.addAttribute("error", "Produit non trouvé.");
            return "error";
        }
        Product existingProduct = updatedProductOptional.get();
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());

        productRepository.save(existingProduct);

        return "redirect:/user/index";
    }

    @GetMapping("/admin/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            model.addAttribute("error", "Produit non trouvé.");
            return "error";
        }

        model.addAttribute("product", optionalProduct.get());
        return "new-product";
    }


    @GetMapping("/not-authorized")
    public String notAuthorized(Model model) {
       return "notAuthorized";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "login";
    }

}
