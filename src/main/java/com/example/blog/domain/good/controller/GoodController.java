package com.example.blog.domain.good.controller;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentReq;
import com.example.blog.domain.comment.dto.CommentRes;
import com.example.blog.domain.good.domain.Good;
import com.example.blog.domain.good.dto.GoodReq;
import com.example.blog.domain.good.dto.GoodRes;
import com.example.blog.domain.good.service.GoodService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/goods")
public class GoodController {

    private final GoodService goodService;

    @PostMapping
    public ResponseEntity<GoodRes> createGood(@RequestBody GoodReq goodReq) {
        Good good = goodService.createGood(goodReq.userId(), goodReq.commentId(), goodReq.toEntity());
        return ResponseEntity.ok(new GoodRes(good.getId(), good.getUser().getId(), good.getComment().getId(), good.isLikeCheck()));
    }

    @PutMapping("/{goodId}")
    public ResponseEntity<GoodRes> updateGood(@PathVariable Long goodId, @RequestBody GoodReq goodReq) {
        Good updatedGood = goodService.updateGood(goodId, goodReq.toEntity());
        return ResponseEntity.ok(new GoodRes(updatedGood.getId(), updatedGood.getUser().getId(), updatedGood.getComment().getId(), updatedGood.isLikeCheck()));
    }

    @GetMapping("/count/{commentId}")
    public ResponseEntity<Integer> countLikesByCommentId(@PathVariable Long commentId) {
        int likeCount = goodService.countLikesByCommentId(commentId);
        return ResponseEntity.ok(likeCount);
    }

}
