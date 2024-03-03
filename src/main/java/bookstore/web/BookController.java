package bookstore.web;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import bookstore.domain.Book;
import bookstore.domain.BookRepository;
import bookstore.domain.CategoryRepository;

@Controller
public class BookController {

    private boolean editing;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

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

        editing = false;
        model.addAttribute("editing", editing);
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());

        return "bookform";

    }

    @PostMapping("/addbook")
    public String saveBook(Book newbook) {

            repository.save(newbook);

            return "redirect:/booklist";

    }

	@GetMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable("id") Long bookId) {
		repository.deleteById(bookId);
		return "redirect:/booklist";

	}

    @GetMapping("/editbook/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		Optional<Book> bookToEdit = repository.findById(bookId);
        editing = true;
        model.addAttribute("editing", editing);
        model.addAttribute("book", bookToEdit);
        model.addAttribute("categories", categoryRepository.findAll());

		return "bookform";

	}

}