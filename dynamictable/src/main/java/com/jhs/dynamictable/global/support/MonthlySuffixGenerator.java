package com.jhs.dynamictable.global.support;

import com.jhs.dynamictable.global.TableSuffixGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class MonthlySuffixGenerator implements TableSuffixGenerator {

    @Override
    public String generateSuffix() {
        Month currentMonth = LocalDate.now().getMonth();

        return String.valueOf(currentMonth.getValue());
    }

}
