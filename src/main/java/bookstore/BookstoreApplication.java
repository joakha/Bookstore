package bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import bookstore.domain.Book;
import bookstore.domain.BookRepository;
import bookstore.domain.Category;
import bookstore.domain.CategoryRepository;
import bookstore.domain.User;
import bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(BookRepository repository, CategoryRepository secondRepository, UserRepository urepository) {
		return (args) -> {

			Category firstCategory = new Category("Action");
			Category secondCategory = new Category("Horror");

			secondRepository.save(firstCategory);
			secondRepository.save(secondCategory);

			log.info("fetch all categories");
			for (Category category : secondRepository.findAll()) {
				log.info(category.toString());
			}

			Book example1 = new Book("Moby Dick", "Herman Melville", 1989, "12345678-9", 18.34,
					secondRepository.findByName("Action").get(0));
			Book example2 = new Book("Great Expectations", "Charles Dickens", 1992, "98765432-1", 25.00,
					secondRepository.findByName("Horror").get(0));

			repository.save(example1);
			repository.save(example2);

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "example1@gmail.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "example2@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
