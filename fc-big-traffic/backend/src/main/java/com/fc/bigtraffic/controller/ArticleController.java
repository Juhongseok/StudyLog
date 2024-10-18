package com.fc.bigtraffic.controller;

import com.fc.bigtraffic.controller.dto.CreateArticleRequest;
import com.fc.bigtraffic.service.ArticleService;
import com.fc.bigtraffic.service.dto.ArticleInfoResponse;
import com.fc.bigtraffic.service.dto.CreateArticle;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ArticleInfoResponse writeArticle(@RequestBody CreateArticleRequest request,
            @Parameter(description = "상위 게시판 아이디", required = true, example="1L")
            @PathVariable("boardId") Long boardId,
            @AuthenticationPrincipal Long userId) {
        return articleService.create(new CreateArticle(boardId, userId, request.title(), request.content()));
    }

}
