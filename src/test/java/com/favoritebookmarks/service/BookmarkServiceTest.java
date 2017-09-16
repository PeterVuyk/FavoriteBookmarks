package com.favoritebookmarks.service;

import com.favoritebookmarks.entity.Bookmark;
import com.favoritebookmarks.exception.BookmarkAlreadyExistException;
import com.favoritebookmarks.repository.BookmarkRepository;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = BookmarkService.class)
public class BookmarkServiceTest
{
    @Test
    public void testAddBookmarkSuccess()
    {
        Bookmark bookmark = mock(Bookmark.class);
        BookmarkRepository bookmarkRepository = mock(BookmarkRepository.class);
        when(bookmarkRepository.findOne(bookmark.getId())).thenReturn(null);

        BookmarkService bookmarkService = new BookmarkService(bookmarkRepository);
        bookmarkService.addBookmark(bookmark);

        verify(bookmarkRepository, times(1)).save(bookmark);
    }

    @Test(expected = BookmarkAlreadyExistException.class)
    public void testAddBookmarkAlreadyExists()
    {
        Bookmark bookmark = mock(Bookmark.class);
        BookmarkRepository bookmarkRepository = mock(BookmarkRepository.class);
        when(bookmarkRepository.findOne(bookmark.getId())).thenReturn(new Bookmark());

        BookmarkService bookmarkService = new BookmarkService(bookmarkRepository);
        bookmarkService.addBookmark(bookmark);

        verify(bookmarkRepository, never()).save(bookmark);
    }

    @Test
    public void testGetBookmarks()
    {
        List returnValue = new ArrayList();
        returnValue.add(new Bookmark());
        BookmarkRepository bookmarkRepository = mock(BookmarkRepository.class);
        when(bookmarkRepository.findAll()).thenReturn(returnValue);

        BookmarkService bookmarkService = new BookmarkService(bookmarkRepository);

        assertEquals(returnValue, bookmarkService.getBookmarks());
    }

    @Test
    public void testIsFormValidIsTrue()
    {
        BookmarkRepository bookmarkRepository = mock(BookmarkRepository.class);
        BookmarkService bookmarkService = new BookmarkService(bookmarkRepository);
        Bookmark bookmark = new Bookmark();
        bookmark.setName("websiteName");
        bookmark.setUrl("url.nl");

        assertEquals(true, bookmarkService.isFormValid(bookmark));
    }

    @Test
    public void testIsFormValidIsFalse()
    {
        BookmarkRepository bookmarkRepository = mock(BookmarkRepository.class);
        BookmarkService bookmarkService = new BookmarkService(bookmarkRepository);

        assertEquals(false, bookmarkService.isFormValid(new Bookmark()));
    }
}
