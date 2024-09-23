package com.jhs.dynamictable.global.jpa;

import com.jhs.dynamictable.global.TableNameGenerator;
import com.jhs.dynamictable.global.support.dto.TableNameMetadata;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonthlyTableNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    private final TableNameGenerator tableNameGenerator;

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        TableNameMetadata metadata = tableNameGenerator.generateTableName();
        String selectedName = metadata.selectName(name.getCanonicalName());

        return Identifier.toIdentifier(selectedName);
    }

}
