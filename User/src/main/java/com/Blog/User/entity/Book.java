package com.Blog.User.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private  long bookId;
    private String name;
    private String author_Name;
    private Long page_number;
    private Long userId;
}
