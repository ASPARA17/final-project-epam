package com.epam.jwd.service.impl;

import com.epam.jwd.dao.api.BaseDao;
import com.epam.jwd.dao.entity.user.LibraryCard;
import com.epam.jwd.dao.exception.DaoException;
import com.epam.jwd.dao.impl.LibraryCardDaoImpl;
import com.epam.jwd.service.api.Service;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.converter.impl.LibraryCardConverter;
import com.epam.jwd.service.dto.userdto.LibraryCardDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.DateUtil;
import com.epam.jwd.service.validator.DateValidator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LibraryCardServiceImpl implements Service<LibraryCardDto, Integer> {
    private final BaseDao<LibraryCard,Integer> libraryCardDao;
    private final Converter<LibraryCard, LibraryCardDto, Integer> converter;
    private final DateValidator validator;
    private static volatile LibraryCardServiceImpl instance;

    private LibraryCardServiceImpl() {
        this.libraryCardDao = LibraryCardDaoImpl.getInstance();
        this.converter = new LibraryCardConverter();
        this.validator = new DateValidator();
    }

    public static LibraryCardServiceImpl getInstance() {
        LibraryCardServiceImpl localInstance = instance;
        if (instance == null) {
            synchronized (LibraryCardServiceImpl.class) {
                localInstance = instance;
                if (instance == null) {
                    instance = localInstance = new LibraryCardServiceImpl();
                }
            }
        }
        return  localInstance;
    }

    @Override
    public List<LibraryCardDto> findAll() throws ServiceException {
        return Collections.emptyList();
    }

    @Override
    public Optional<LibraryCardDto> findById(Integer id) throws ServiceException {
        Optional<LibraryCardDto> libraryCardDtoOptional = Optional.empty();
        try {
            Optional<LibraryCard> libraryCard = libraryCardDao.findById(id);
            if (libraryCard.isPresent()) {
                LibraryCardDto libraryCardDto = converter.convert(libraryCard.get());
                libraryCardDtoOptional = Optional.of(libraryCardDto);
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return libraryCardDtoOptional;
    }

    public LibraryCardDto create(LibraryCardDto libraryCardDto) throws ServiceException {
        String dateOfIssue = DateUtil.parseDateToStringFormat(libraryCardDto.getDateOfIssue());
        String expirationDate =
                DateUtil.parseDateToStringFormat(libraryCardDto.getExpirationDate());
        validator.checkDate(dateOfIssue, expirationDate);
        LibraryCard createdLibraryCard = converter.convert(libraryCardDto);
        try {
            libraryCardDto = converter.convert(libraryCardDao.add(createdLibraryCard));
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return libraryCardDto;
    }
}
