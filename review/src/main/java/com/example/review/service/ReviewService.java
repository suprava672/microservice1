package com.example.review.service;

import com.example.review.entity.ReviewOfBook;
import com.example.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    public ReviewOfBook addReview(ReviewOfBook review){
       return reviewRepository.save(review);

    }
    public List<ReviewOfBook> getReviewByUserId(Long userId){
        return reviewRepository.findByUserId(userId);

    }
public List<ReviewOfBook> getReviewByBookId(Long bookId){
        return reviewRepository.findByBookId(bookId);
}
public ReviewOfBook updateReview(ReviewOfBook review,Long reviewId){
        return reviewRepository.findById(reviewId).map(review1 -> {
           review1.setRating(review.getRating());
           review1.setTitle(review.getTitle());
           return reviewRepository.save(review);

                }
                ).orElseThrow(()->new RuntimeException("id is not found in this id"));


}
}
