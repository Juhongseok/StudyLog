package com.jhs.dynamictable.global;

import com.jhs.dynamictable.history.model.History;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class TableInit implements ApplicationRunner {

    private final EntityManager em;

    @Override
    public void run(ApplicationArguments args) {
        for (int i = 0; i < 10; i++) {
            History history = new History("content" + i);
            em.persist(history);
        }
    }

}
