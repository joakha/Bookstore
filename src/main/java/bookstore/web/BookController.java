package bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import bookstore.domain.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping("/index")
    public String showIndex(Model model) {

        return "index";
        
    }

    @GetMapping("/booklist")
    public String bookList(Model model) {

        model.addAttribute("books", repository.findAll());

        return "booklist";

    }

}