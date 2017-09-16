package com.favoritebookmarks.exception;

public class BookmarkAlreadyExistException extends RuntimeException
{
    public static final String MESSAGE = "A bookmark with id %d already exist";

    public BookmarkAlreadyExistException(final String message)
    {
        super(message);
    }
}
