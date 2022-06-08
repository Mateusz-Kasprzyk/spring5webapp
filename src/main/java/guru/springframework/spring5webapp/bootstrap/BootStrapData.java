package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {

        System.out.println("BootStrapData running");

        Publisher publisher1 = new Publisher(
                "Example Publisher", "King of the North st", "Wyzima", "KY", "43201");
        publisherRepository.save(publisher1);

        Author eldredge = new Author("John", "Eldredge");
        Author tolkien = new Author("J.R.R.", "Tolkien");
        Author lewis = new Author("C.S.", "Lewis");
        Author bartosiak = new Author("Jacek", "Bartosiak");
        Author zychowicz = new Author("Piotr", "Zychowicz");
        List<Author> authors = Arrays.asList(eldredge, tolkien, lewis, bartosiak, zychowicz);
        authorRepository.saveAll(authors);

        Book heart = new Book("Wild heart", "121212");
        Book fellowship = new Book("The Lord of the Rings: The Fellowship of the Ring", "131313");
        Book tower = new Book("The Lord of the Rings: The Two Towers", "141414");
        Book king = new Book("The Lord of the Rings: The Return of the King", "151515");
        Book divorce = new Book("The Great Divorce", "161616");
        Book wojna = new Book("Nadchodzi III Wojna Światowa", "171717");
        List<Book> books = Arrays.asList(heart, fellowship, tower, king, divorce, wojna);
        bookRepository.saveAll(books);



        eldredge.getBooks().add(heart);
        tolkien.getBooks().add(fellowship);
        tolkien.getBooks().add(tower);
        tolkien.getBooks().add(king);
        lewis.getBooks().add(divorce);
        bartosiak.getBooks().add(wojna);
        zychowicz.getBooks().add(wojna);
        authorRepository.saveAll(authors);

        heart.getAuthors().add(eldredge);
        fellowship.getAuthors().add(tolkien);
        tower.getAuthors().add(tolkien);
        king.getAuthors().add(tolkien);
        divorce.getAuthors().add(lewis);
        wojna.getAuthors().add(bartosiak);
        wojna.getAuthors().add(zychowicz);
        tower.setPublisher(publisher1);
        heart.setPublisher(publisher1);
        bookRepository.saveAll(books);

        publisher1.getBooks().add(tower);
        publisher1.getBooks().add(heart);
        publisherRepository.save(publisher1);

        System.out.println("Publishers No. " + publisherRepository.count());
        System.out.println("Author No. " + authorRepository.count());
        authors.forEach(author -> System.out.println(author.getLastName() + ": " + author.getBooks().size() + " books"));
        System.out.println("Books No. " + bookRepository.count());
        books.forEach(book -> System.out.println(book.getTitle() + ": " + book.getAuthors().size() + " authors"));
        System.out.println("Publisher's books: " + publisher1.getBooks().size());

    }
}
