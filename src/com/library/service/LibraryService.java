package com.library.service;

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
            System.out.println("Invalid User or User is not Present");
            return;
        }
        if(book==null ){
            System.out.println("Invalid Book or Book is not Present");
            return;
        }
        if(!book.isAvailable()){
            System.out.println("Book is already issued");
            return;
        }
        book.setAvailable(false);
        IssueRecord record=new IssueRecord(user, book);
        issueRepo.addRecord(record);
        System.out.println("Book issued  to "+user.getUserName());

    }
    public void returnBook(int userId) {

        if (userRepo.getUser(userId) == null) {
            System.out.println("User is not Present");
            return;
        }
        IssueRecord record = issueRepo.getActiveIssueByUserId(userId);
        if (record == null) {
            System.out.println("No active issued book for this user");
            return;
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
