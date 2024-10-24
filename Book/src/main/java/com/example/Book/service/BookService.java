package com.example.Book.service;

import com.example.Book.entity.Book;
import com.example.Book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);

    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not getting by id"));

    }

    public String deleteBookById(Long id) {
        bookRepository.deleteById(id);
        return "book deleted successfully";
    }

    public Book updateBookById(Book book, Long id) {
        Book book1 = bookRepository.findById(id).orElseThrow(() -> new RuntimeException(" not updated"));
        book1.setName(book.getName());
        return bookRepository.save(book1);

    }

    public List<Book> getBookOfUser(Long userId) {
        return bookRepository.findBookById(userId);

    }

}
