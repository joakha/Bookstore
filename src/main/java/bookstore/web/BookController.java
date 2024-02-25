package bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import bookstore.domain.Book;
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

    @GetMapping("/addbook")
    public String addBook(Model model) {

        model.addAttribute("book", new Book());

        return "bookform";

    }

    @PostMapping("/savebook")
    public String saveBook(Book book) {

        repository.save(book);

        return "redirect:/booklist";

    }

	@GetMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable("id") Long bookId) {
		repository.deleteById(bookId);
		return "redirect:/booklist";

	}

}