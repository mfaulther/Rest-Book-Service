package restbookservice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookController {

    public int counter = 4;

    List<Map<String, String>> books = new ArrayList<>(){{
        add(new HashMap<>() {{ put("id", "1"); put("author", "Franz Kafka"); put("title", "The Process"); }});
        add(new HashMap<>() {{ put("id", "2"); put("author", "Jack Kerouac"); put("title", "On the Road"); }});
        add(new HashMap<>() {{ put("id", "3"); put("author", "William Gibson"); put("title", "Neuromancer"); }});
    }};

    @GetMapping("/books")
    public List<Map<String, String>> getAllBooks() {
        return books;
    }

    @GetMapping("/books/{id}")
    public Map<String, String> getOneBook(@PathVariable String id) {

        return getBook(id);

    }

    @PostMapping("/books")
    public Map<String, String> createBook(@RequestBody Map<String, String> book) {
        book.put("id", String.valueOf(counter++));
        books.add(book);
        return book;
    }


    @PutMapping("/books/{id}")
    public Map<String, String> updateBook(@PathVariable String id, @RequestBody Map<String, String> book) {
        Map<String, String> newBook = getBook(id);
        newBook.putAll(book);
        return newBook;
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable String id) {
        Map<String, String> book = getBook(id);
        books.remove(book);
    }

    private Map<String, String> getBook(String id) {
        return books.stream().filter(book-> book.get("id").equals(id)).findFirst().orElseThrow(BookNotFoundException::new);
    }


}
