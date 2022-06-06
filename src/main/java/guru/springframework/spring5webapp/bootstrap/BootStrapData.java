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
                "Example Publishing", "King of the North st", "Wyzima", "KY", "43201");

        Author eldredge = new Author("John", "Eldredge");
        Author tolkien = new Author("J.R.R.", "Tolkien");
        Author lewis = new Author("C.S.", "Lewis");
        Author bartosiak = new Author("Jacek", "Bartosiak");
        Author zychowicz = new Author("Piotr", "Zychowicz");

        Book heart = new Book("Wild heart", "121212");
        Book fellowship = new Book("The Lord of the Rings: The Fellowship of the Ring", "131313");
        Book tower = new Book("The Lord of the Rings: The Two Towers", "141414");
        Book king = new Book("The Lord of the Rings: The Return of the King", "151515");
        Book divorce = new Book("The Great Divorce", "161616");
        Book wojna = new Book("Nadchodzi III Wojna Światowa", "171717");


        eldredge.getBooks().add(heart);
        tolkien.getBooks().add(fellowship);
        tolkien.getBooks().add(tower);
        tolkien.getBooks().add(king);
        lewis.getBooks().add(divorce);
        bartosiak.getBooks().add(wojna);
        zychowicz.getBooks().add(wojna);

        heart.getAuthors().add(eldredge);
        fellowship.getAuthors().add(tolkien);
        tower.getAuthors().add(tolkien);
        king.getAuthors().add(tolkien);
        divorce.getAuthors().add(lewis);
        wojna.getAuthors().add(bartosiak);
        wojna.getAuthors().add(zychowicz);

        publisherRepository.save(publisher1);
        authorRepository.saveAll(Arrays.asList(eldredge,tolkien,lewis,bartosiak,zychowicz));
        bookRepository.saveAll(Arrays.asList(heart,fellowship,tower,king,divorce,wojna));

        System.out.println(publisherRepository.count());
        System.out.println(authorRepository.count());
        System.out.println(bookRepository.count());

    }
}
