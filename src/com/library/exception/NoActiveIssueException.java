package com.library.exception;

public class NoActiveIssueException extends RuntimeException {
    public NoActiveIssueException(String message) {
        super(message);
    }
}
