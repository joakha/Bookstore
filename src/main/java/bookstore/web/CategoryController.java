package bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import bookstore.domain.Category;
import bookstore.domain.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping("/categorylist")
    public String categorylist(Model model) {

        model.addAttribute("categories", repository.findAll());
        return "categorylist";
    }

    @GetMapping("/addcategory")
    public String addCategory(Model model) {

        model.addAttribute("category", new Category());
        return "categoryform";

    }

    @PostMapping("/addcategory")
    public String saveCategory(Category category) {
        
        repository.save(category);
        return "redirect:/categorylist";
    }
    
}
