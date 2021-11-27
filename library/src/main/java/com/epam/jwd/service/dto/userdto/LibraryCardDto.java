package com.epam.jwd.service.dto.userdto;

import com.epam.jwd.service.dto.EntityDto;

import java.sql.Date;

public class LibraryCardDto extends EntityDto<Integer> {
    private Date dateOfIssue;
    private Date expirationDate;

    public LibraryCardDto(Integer id, Date dateOfIssue, Date expirationDate) {
        super(id);
        this.dateOfIssue = dateOfIssue;
        this.expirationDate = expirationDate;
    }

    public LibraryCardDto(Date dateOfIssue, Date expirationDate) {
        this.dateOfIssue = dateOfIssue;
        this.expirationDate = expirationDate;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
