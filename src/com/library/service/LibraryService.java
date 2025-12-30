package com.library.service;

import com.library.exception.BookNotAvailableException;
import com.library.exception.BookNotFoundException;
import com.library.exception.NoActiveIssueException;
import com.library.exception.UserNotFoundException;
import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.IssueRepository;
import com.library.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LibraryService {

    private BookRepository bookRepo;
    private IssueRepository issueRepo;
    private UserRepository userRepo;

    public LibraryService(BookRepository bookRepo, IssueRepository issueRepo, UserRepository userRepo) {
        this.bookRepo = bookRepo;
        this.issueRepo = issueRepo;
        this.userRepo = userRepo;
    }

    public void issueBook(int userId, int bookId){
        Book book=bookRepo.getBook(bookId);
        User user=userRepo.getUser(userId);
        if(user==null ){
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        if(book==null ){
            throw new BookNotFoundException("Book with ID " + bookId + " not found");
        }
        if(!book.isAvailable()){
            throw new BookNotAvailableException("Book is already issued");
        }
        book.setAvailable(false);
        IssueRecord record=new IssueRecord(user, book);
        issueRepo.addRecord(record);
        System.out.println("Book issued  to "+user.getUserName());

    }
    public void returnBook(int userId) {

        if (userRepo.getUser(userId) == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        IssueRecord record = issueRepo.getActiveIssueByUserId(userId);
        if (record == null) {
            throw new NoActiveIssueException("No active issued book found for user ID " + userId);
        }
        record.setReturnDate(LocalDate.now());
        record.getBook().setAvailable(true);
        System.out.println("Book returned successfully");
        System.out.println("User name is : " + record.getUser().getUserName());
        System.out.println("Book: " + record.getBook().getTitle());
        int fine=FineCalculator.calculateFine(record.getIssueDate(),record.getReturnDate());
        System.out.println("Fine to be paid is : "+fine);
    }

}
