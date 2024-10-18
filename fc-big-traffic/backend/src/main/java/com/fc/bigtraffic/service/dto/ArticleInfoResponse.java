package com.fc.bigtraffic.service.dto;

import com.fc.bigtraffic.entity.Article;

public record ArticleInfoResponse(
        String title,
        String content,
        Long authorId,
        Long boardId
) {

    public ArticleInfoResponse(Article article) {
        this(article.getTitle(), article.getContent(), article.getAuthorId(), article.getBoard().getId());
    }

}
