package com.favoritebookmarks.controller;

import com.favoritebookmarks.entity.Bookmark;
import com.favoritebookmarks.service.BookmarkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(BookmarkListController.class)
public class BookmarkListControllerTest
{
    @MockBean
    private BookmarkService bookmarkService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testViewBookmarkList() throws Exception
    {
        List<Bookmark> bookmarks = new ArrayList<>();
        Bookmark bookmark = new Bookmark();
        bookmark.setName("Example");
        bookmark.setUrl("www.example.com");
        bookmarks.add(bookmark);

        given(bookmarkService.getBookmarks()).willReturn(bookmarks);

        this.mockMvc
                .perform(get("/"))
                .andExpect(status()
                .isOk())
                .andExpect(content()
                .string(containsString("www.example.com"))
                );
    }

    @Test
    public void testAddBookmark() throws Exception
    {
        this.mockMvc
                .perform(post("/")
                        .sessionAttr("bookmark", new Bookmark())
                )
                .andExpect(status().is3xxRedirection());
    }
}
