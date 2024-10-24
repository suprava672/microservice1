package com.Blog.User.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BookDetails")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long bookId;
    private String name;
    private String author_Name;
    private Long page_number;
    private Long userId;
}
