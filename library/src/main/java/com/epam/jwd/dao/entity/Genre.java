package com.epam.jwd.dao.entity;

import java.util.Arrays;

public enum Genre {
    DETECTIVE("detective"),
    FANTASTIC("fantastic"),
    ADVENTURE("adventure"),
    NOVEL("novel"),
    SCIENTIFIC("scientific"),
    CHILDREN("children"),
    EDUCATIONAL("educational");

    private final String nameGenre;

    Genre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public static Genre getGenreByName(String nameGenre) {
        return Arrays.stream(Genre.values())
                .filter(genre -> genre.getNameGenre().equals(nameGenre))
                .findFirst()
                .orElse(null);
    }
}
