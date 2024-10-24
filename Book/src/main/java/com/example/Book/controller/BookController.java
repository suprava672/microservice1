package com.example.Book.controller;

import com.example.Book.entity.Book;
import com.example.Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public Book creatBook(@RequestBody Book book) {
        return bookService.createBook(book);

    }

    @GetMapping
    public List<Book> getAllBook() {
        return bookService.getAllBook();
    }

    @GetMapping("/{id}")
    public Book getBookBuyId(@PathVariable Long id) {
        return bookService.getBookById(id);

    }

    @DeleteMapping("/{id}")
    public String deleteBookById(@PathVariable Long id) {
        return bookService.deleteBookById(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@RequestBody Book book, @PathVariable Long id) {
        Book book1 = bookService.updateBookById(book, id);
        return ResponseEntity.ok(book1);
    }

    @GetMapping("/user/{userId}")
    public List<Book> getBookById(@PathVariable Long userId) {
        return bookService.getBookOfUser(userId);

    }
}
