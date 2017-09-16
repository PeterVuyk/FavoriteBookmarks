package com.favoritebookmarks.controller;

import com.favoritebookmarks.entity.Bookmark;
import com.favoritebookmarks.service.BookmarkService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;

@Controller
@RequestMapping(value="/")
public class BookmarkListController
{
    private final BookmarkService bookmarkservice;

    @Inject
    public BookmarkListController(final BookmarkService bookmarkService)
    {
        this.bookmarkservice = bookmarkService;
    }

    @GetMapping
    public ModelAndView viewBookmarkList()
    {
        ModelMap model = new ModelMap();
        model.addAttribute("bookmarks", this.bookmarkservice.getBookmarks());
        model.addAttribute("bookmark", new Bookmark());

        return new ModelAndView("bookmark_list", model);
    }

    @PostMapping
    public RedirectView AddBookmark(@ModelAttribute Bookmark bookmark)
    {
        if (!this.bookmarkservice.isFormValid(bookmark)) {
            //@todo: Show error flash message
            return new RedirectView("/");
        }
        try {
            this.bookmarkservice.addBookmark(bookmark);
            //@todo: Show success flash message
        } catch (Exception e) {
            //@todo: Show error flash message
        }
        return new RedirectView("/");
    }
}
