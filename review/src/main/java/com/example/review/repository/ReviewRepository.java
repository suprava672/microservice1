package com.example.review.repository;

import com.example.review.entity.ReviewOfBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<ReviewOfBook,Long> {
    List<ReviewOfBook> findByUserId(Long userId);

    List<ReviewOfBook> findByBookId(Long bookId);
}
