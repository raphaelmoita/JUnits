package org.moita.formater;

import java.util.Objects;

public class Formater {

    static String TO_UPPERCASE(String text) {
        if (Objects.nonNull(text)) return text.toUpperCase();

        throw new IllegalArgumentException("cannot be null");
    }

    public String beautify(String line) {
        return TO_UPPERCASE(line);
    }
}
