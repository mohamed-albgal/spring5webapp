package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//this annot tells spring to detect this a component of my app
@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        Author eric = new Author("Eric", "Morales");
        Publisher publisher = new Publisher("Maq Media Productions", "427 S 37th St", "Richmond" ,"CA", "94804");
        Book bookTLDLA = new Book("To Live And Die In L.A", "234fdsjz3");
        eric.getBooks().add(bookTLDLA);
        bookTLDLA.getAuthors().add(eric);
        publisherRepository.save(publisher);
        authorRepository.save(eric);

        bookTLDLA.setPublisher(publisher);
        bookRepository.save(bookTLDLA);



        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2ee develepoment without eJB", "234324j234jk");
        bookRepository.save(noEJB);
        authorRepository.save(rod);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPubsher(publisher);
        bookRepository.save(noEJB);
        //let publisher know about a new book it publishes
        publisher.getBooks().add(noEJB);
        publisher.getBooks().add(bookTLDLA);

        publisherRepository.save(publisher);


        System.out.println("Started Bootstrap file");
        System.out.println("Number of books for this publishers: "+publisher.getBooks().size());
        System.out.println("Number of Books stored in the repository: " + bookRepository.count());
    }
}
