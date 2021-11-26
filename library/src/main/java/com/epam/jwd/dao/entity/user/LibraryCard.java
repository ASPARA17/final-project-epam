package com.epam.jwd.dao.entity.user;

import com.epam.jwd.dao.entity.Entity;

import java.sql.Date;

public class LibraryCard extends Entity<Integer> {
    private Date dateOfIssue;
    private Date expirationDate;

    public LibraryCard(Integer id, Date dateOfIssue, Date expirationDate) {
        super(id);
        this.dateOfIssue = dateOfIssue;
        this.expirationDate = expirationDate;
    }

    public LibraryCard(Date dateOfIssue, Date expirationDate) {
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
