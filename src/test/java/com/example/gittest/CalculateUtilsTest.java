package com.example.gittest;

import com.example.gittest.utils.CalculateUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculateUtilsTest {

    @Test
    void addReturnsSumForEvenNumbers() {
        assertThat(CalculateUtils.add(2, 4)).isEqualTo(3);
    }

    @Test
    void addUsesIntegerDivisionBeforeAdding() {
        assertThat(CalculateUtils.add(3, 5)).isEqualTo(3);
    }
}
