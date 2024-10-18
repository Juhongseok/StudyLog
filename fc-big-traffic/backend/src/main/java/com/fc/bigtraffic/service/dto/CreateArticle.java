package com.fc.bigtraffic.service.dto;

public record CreateArticle(
        Long boardId,
        Long authorId,
        String title,
        String content
) {

}
