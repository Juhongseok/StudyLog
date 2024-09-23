package com.jhs.dynamictable.global.support;

import com.jhs.dynamictable.global.TableSelector;
import com.jhs.dynamictable.global.properties.TableProperties;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnnotationBasedTableSelector implements TableSelector {

    private final TableProperties properties;

    @Override
    public String selectTableOriginName() {
        try {
            Class<?> clazz = Class.forName(properties.classPath());
            Table table = clazz.getAnnotation(Table.class);

            return table.name().isEmpty() ? clazz.getSimpleName() : table.name();
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot Find Class", e);
        }
    }

}
