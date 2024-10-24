package com.example.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="reviewInformation")
public class ReviewOfBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private int rating;
    private String title;
    private Long userId;
    private Long bookId;
    private LocalDateTime reviewDate;
}
