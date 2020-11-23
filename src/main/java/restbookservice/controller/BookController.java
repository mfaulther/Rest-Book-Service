package restbookservice.controller;

import org.springframework.web.bind.annotation.*;
import restbookservice.domain.Book;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    public int counter = 4;

    List<Book> books = new ArrayList<>(){{
            add (new Book(1, "Franz Kafka", "The Process"));
            add (new Book(2, "Jack Kerouac", "On the Road"));
            add (new Book(3, "William Gibson", "Neuromancer"));
        }};

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/books/{id}")
    public Book getOneBook(@PathVariable int id) {
        return getBookById(id);
    }

    private Book getBookById(@PathVariable int id) {
        return books
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElseThrow(BookNotFoundException::new);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        book.setId(counter++);
        books.add(book);
        return book;
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        Book newBook = getBookById(id);

        newBook.setId(id);
        if (book.getAuthor() != null) {
            newBook.setAuthor(book.getAuthor());
        }
        if (book.getTitle() != null) {
            newBook.setTitle(book.getTitle());
        }
        return newBook;
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable int id) {
        Book book = getBookById(id);
        books.remove(book);
    }

}
