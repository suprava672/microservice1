package com.Blog.User.service;

import com.Blog.User.openFeign.BookFeign;
import com.Blog.User.entity.Book;
import com.Blog.User.entity.User;
import com.Blog.User.exception.ResourceNotFoundException;
import com.Blog.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookFeign bookFeign;

    public User createUser(User user) {
        return userRepository.save(user);

    }

    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();

        // Use stream to fetch books for each user via the Feign client
        List<User> usersWithBooks = users.stream().map(user -> {
            // Fetch books for the current user using Feign client
            List<Book> books = bookFeign.getbooks(user.getUserId());

            // Set the fetched books to the user entity (assuming User has a setBooks method)
            user.setBooks(books);

            return user; // Return the user with associated books
        }).collect(Collectors.toList());

        return usersWithBooks;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not get" + id));

    }

    public String deleteUserById(Long id) {
        User valueNotGet = userRepository.findById(id).orElseThrow(() -> new RuntimeException("value not get"));

        userRepository.delete(valueNotGet);
        return " user deleted successfully";
    }

    public User updateUser(User user, Long id) {
        User valueGet = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("value get"));
        valueGet.setName(user.getName());
        User savsave1 = userRepository.save(valueGet);

        return savsave1;

    }

    public List<User> getAllUsersWithBooks() {
        // Fetch all users
        List<User> users = userRepository.findAll();

        // Use stream to map users with their associated books fetched from Book Service
        List<User> usersWithBooks = users.stream().map(user -> {
            // Fetch books for the current user
            List<Book> books = bookFeign.getbooks(user.getUserId());

            // Assuming the User entity has a setBooks method to set the books list
            user.setBooks(books); // Set the fetched books

            return user; // Return the user with associated books
        }).collect(Collectors.toList());

        return usersWithBooks; // Return the list of users with their books
    }
}


