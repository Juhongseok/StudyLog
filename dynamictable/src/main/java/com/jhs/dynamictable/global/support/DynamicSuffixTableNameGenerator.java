package com.jhs.dynamictable.global.support;

import com.jhs.dynamictable.global.TableNameGenerator;
import com.jhs.dynamictable.global.support.dto.TableNameMetadata;
import com.jhs.dynamictable.global.TableSelector;
import com.jhs.dynamictable.global.TableSuffixGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DynamicSuffixTableNameGenerator implements TableNameGenerator {

    private final TableSelector tableSelector;
    private final TableSuffixGenerator tableSuffixGenerator;

    @Override
    public TableNameMetadata generateTableName() {
        String tableName = tableSelector.selectTableOriginName();
        String suffix = tableSuffixGenerator.generateSuffix();

        return new TableNameMetadata(tableName, tableName + suffix);
    }

}
