package com.jhs.dynamictable.global;

import com.jhs.dynamictable.global.support.dto.TableNameMetadata;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class TableInit implements ApplicationRunner {

    private final JdbcTemplate jdbcTemplate;
    private final TableNameGenerator tableNameGenerator;

    @Override
    public void run(ApplicationArguments args) {
        TableNameMetadata metadata = tableNameGenerator.generateTableName();
        String tableName = metadata.generatedName();

        List<HistoryDto> data = List.of(
                new HistoryDto(1L, "content1"),
                new HistoryDto(2L, "content2"),
                new HistoryDto(3L, "content3"),
                new HistoryDto(4L, "content4"),
                new HistoryDto(5L, "content5"),
                new HistoryDto(6L, "content6"),
                new HistoryDto(7L, "content7")
        );

        jdbcTemplate.batchUpdate("""
                insert into %s (id, content)
                values (?, ?)
                """.formatted(tableName), data, 10, (ps, argument) -> {
            ps.setLong(1, argument.id);
            ps.setString(2, argument.content);
        });

    }

    public record HistoryDto(Long id, String content) {

    }

}
