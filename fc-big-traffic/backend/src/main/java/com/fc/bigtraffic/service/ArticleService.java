package com.fc.bigtraffic.service;

import com.fc.bigtraffic.entity.Article;
import com.fc.bigtraffic.entity.Board;
import com.fc.bigtraffic.entity.User;
import com.fc.bigtraffic.repository.ArticleRepository;
import com.fc.bigtraffic.repository.BoardRepository;
import com.fc.bigtraffic.service.dto.ArticleInfoResponse;
import com.fc.bigtraffic.service.dto.CreateArticle;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    public ArticleInfoResponse create(CreateArticle createdRequest) {
        Board board = boardRepository.findById(createdRequest.boardId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        Article article = new Article(createdRequest.title(), createdRequest.content(), createdRequest.authorId(), board);
        Article createdArticle = articleRepository.save(article);

        return new ArticleInfoResponse(createdArticle);
    }

}
