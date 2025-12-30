package com.library.repository;

import com.library.exception.DuplicateException;
import com.library.model.Book;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookRepository {
    private static Map<Integer, Book> books=new HashMap<>();

    public void addBook(Book book){
        if (books.containsKey(book.getId())) {
            throw new DuplicateException("Book with BookID "+book.getId()+" already exists");
        }
        books.put(book.getId(),book);
    }

    public Book getBook(int id){
        return books.get(id);
    }
    public Collection<Book> getAllBooks(){
        return books.values();
    }
}
