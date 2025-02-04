package com.example.library.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StringUtilTest {
    private StringUtil stringUtil = new StringUtil();

    @Test
    void trimWithNull() {
        String trim = stringUtil.trim(null);
        assertNull(trim);

    }

    @Test
    void trimWithSpace() {
        String textText = " asdf  ";
        String trim = stringUtil.trim(textText);
        assertEquals("asdf", trim);
    }

    @Test
    void trimWithoutSpace() {
        String test = "asdf";
        String trim = stringUtil.trim(test);
        assertEquals("asdf",test);
    }
}