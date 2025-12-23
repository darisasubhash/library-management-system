package com.library.model;

public enum BookType {
    FICTION,
    NON_FICTION,
    SCIENCE,
    HISTORY,
    TECHNOLOGY,
    MATHEMATICS,
    COMEDY,
    OTHER;

    public static BookType fromString(String input){
        for(BookType type:values()){
            if(type.name().equalsIgnoreCase(input)){
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Book Type");
    }

}
