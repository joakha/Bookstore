package bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import bookstore.domain.Book;
import bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(BookRepository repository) {
		return (args) -> {

			Book example1 = new Book("Moby Dick", "Herman Melville", 1989, "12345678-9", 18.34);
			Book example2 = new Book("Great Expectations", "Charles Dickens", 1992, "98765432-1", 25.00);

			repository.save(example1);
			repository.save(example2);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
