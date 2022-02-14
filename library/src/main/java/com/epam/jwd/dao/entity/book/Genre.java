package com.epam.jwd.dao.entity.book;

import java.util.Arrays;

/**
 * Enum which describes available genres in library
 */
public enum Genre {
    DETECTIVE(1),
    FANTASTIC(2),
    ADVENTURE(3),
    NOVEL(4),
    SCIENTIFIC(5),
    CHILDREN(6),
    EDUCATIONAL(7);

    /**
     * Integer field which describes genre id
     */
    private final int genreId;

    Genre(int genreId) {
        this.genreId = genreId;
    }

    public int getGenreId() {
        return genreId;
    }

    public static Genre getGenreById(int genreId) {
        return Arrays.stream(Genre.values())
                .filter(genre -> genre.getGenreId() == genreId)
                .findFirst()
                .orElse(null);
    }
}
