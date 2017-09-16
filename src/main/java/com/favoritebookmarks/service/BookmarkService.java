package com.favoritebookmarks.service;

import com.favoritebookmarks.entity.Bookmark;
import com.favoritebookmarks.exception.BookmarkAlreadyExistException;
import com.favoritebookmarks.repository.BookmarkRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import java.util.List;

@Service
@Validated
public class BookmarkService
{
    private final BookmarkRepository bookmarkRepository;

    @Inject
    public BookmarkService(final BookmarkRepository bookmarkRepository)
    {
        this.bookmarkRepository = bookmarkRepository;
    }
    
    public List<Bookmark> getBookmarks()
    {
        return bookmarkRepository.findAll();
    }

    public void addBookmark(Bookmark bookmark)
    {
        Bookmark existing = bookmarkRepository.findOne(bookmark.getId());
        if (existing != null) {
            //@todo: Add logging
            throw new BookmarkAlreadyExistException(
                    String.format(BookmarkAlreadyExistException.MESSAGE, bookmark.getId())
            );
        }

        bookmarkRepository.save(bookmark);
    }

    public boolean isFormValid(Bookmark bookmark)
    {
        if (bookmark.getName() == null || bookmark.getName().isEmpty()) {
            return false;
        }
        if (bookmark.getUrl() == null || bookmark.getUrl().isEmpty()) {
            return false;
        }
        return true;
    }
}
