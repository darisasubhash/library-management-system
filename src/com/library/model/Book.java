package com.library.model;

public class Book {
    private int id;
    private String title;
    private BookType type;
    private boolean available;

    public Book(int id, String title, BookType type) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.available=true;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public BookType getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book id =" +id +" ,Book name ="+ title ;
    }
}
