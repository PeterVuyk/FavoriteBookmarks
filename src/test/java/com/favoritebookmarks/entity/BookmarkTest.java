package com.favoritebookmarks.entity;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookmarkTest
{
    @Test
    public void testName()
    {
        String name = "Example";
        Bookmark bookmark = new Bookmark();
        bookmark.setName(name);

        assertEquals(name, bookmark.getName());
    }

    @Test
    public void testUrl()
    {
        String url = "url.website.nl";
        Bookmark bookmark = new Bookmark();
        bookmark.setUrl(url);

        assertEquals(url, bookmark.getUrl());
    }
}
