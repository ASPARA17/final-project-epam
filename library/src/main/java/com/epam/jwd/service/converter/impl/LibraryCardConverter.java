package com.epam.jwd.service.converter.impl;

import com.epam.jwd.dao.entity.user.LibraryCard;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.dto.userdto.LibraryCardDto;

public class LibraryCardConverter implements Converter<LibraryCard, LibraryCardDto, Integer> {
    @Override
    public LibraryCard convert(LibraryCardDto entity) {
        return new LibraryCard(entity.getId(),
                entity.getDateOfIssue(),
                entity.getExpirationDate());
    }

    @Override
    public LibraryCardDto convert(LibraryCard entity) {
        return new LibraryCardDto(entity.getId(),
                entity.getDateOfIssue(),
                entity.getExpirationDate());
    }
}
