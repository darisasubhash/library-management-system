package com.library.model;

import java.time.LocalDate;

public class IssueRecord {
    private User user;
    private Book book;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public IssueRecord(User user, Book book, LocalDate issueDate) {
        this.user = user;
        this.book = book;
        this.issueDate = issueDate;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
