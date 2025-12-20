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

    public void issueBook(int bookId, int userId, LocalDate issueDate){
        Book book=bookRepo.getBook(bookId);
        User user=userRepo.getUser(userId);

        if(book==null ){
            System.out.println("Invalid Book or Book is not Present");
            return;
        }
        if(user==null ){
            System.out.println("Invalid User or User is not Present");
            return;
        }
        if(!book.isAvailable()){
            System.out.println("Book is already issued");
            return;
        }
        book.setAvailable(false);
        IssueRecord record=new IssueRecord(user, book, issueDate);
        issueRepo.addRecord(record);
        System.out.println("Book issued  to "+user.getUserName());

    }
    public void returnBook(int bookId,LocalDate returDate){
        IssueRecord record=issueRepo.findActiveRecordByBookId(bookId);
        if(record==null){
            System.out.println("No issue record found");
            return;
        }
        record.setReturnDate(returDate);
        record.getBook().setAvailable(true);
        int fine=FineCalculator.calculateFine(record.getIssueDate(),record.getReturnDate());

        System.out.println("Book returned successfully");
        System.out.println("Fine to be paid is:"+fine);


    }

}
