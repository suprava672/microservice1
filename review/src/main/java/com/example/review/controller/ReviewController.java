package com.example.review.controller;

import com.example.review.entity.ReviewOfBook;
import com.example.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping
    public ReviewOfBook addReview(@RequestBody ReviewOfBook review){

        return reviewService.addReview(review);
    }

    @GetMapping("/user/{userId}")
    public List<ReviewOfBook> getReviewByUserId(@PathVariable Long userId){
      return   reviewService.getReviewByUserId(userId);
    }
    @GetMapping("/book/{bookId}")
    public List<ReviewOfBook> getTReviewByBookId(@PathVariable Long bookId){

        return reviewService.getReviewByBookId(bookId);
    }
    @PutMapping("{reviewId}")
    public ResponseEntity<ReviewOfBook> updateReview(@RequestBody ReviewOfBook review  , @PathVariable Long reviewId ){
        ReviewOfBook updatedReview = reviewService.updateReview(review, reviewId);
        return ResponseEntity.ok(updatedReview) ;
    }
}
