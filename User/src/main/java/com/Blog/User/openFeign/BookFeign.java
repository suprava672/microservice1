package com.Blog.User.openFeign;

import com.Blog.User.entity.Book;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "BOOK-SERVICE")
public interface BookFeign {

        @GetMapping("/Book/user/{userId}")
     List<Book> getbooks(@PathVariable Long userId);

}
