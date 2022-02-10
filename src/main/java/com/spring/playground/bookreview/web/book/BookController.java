package com.spring.playground.bookreview.web.book;

import com.spring.playground.bookreview.domain.Book;
import com.spring.playground.bookreview.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @ModelAttribute("searchTypes")
    public BookSearch.SearchType[] itemTypes() {
        return BookSearch.SearchType.values();
    }

    @GetMapping
    public String bookList(@ModelAttribute BookSearch bookSearch, Model model) {
        log.info("bookList");
        List<Book> books = bookService.findByAll();
        List<BookSearch.SearchType> searchTypes = new ArrayList<>();
        searchTypes.add(BookSearch.SearchType.TITLE);
        searchTypes.add(BookSearch.SearchType.AUTHOR);
        model.addAttribute("books", books);
        model.addAttribute("searchTypes", searchTypes);
        return "/books/bookList";
    }

    @PostMapping
    public String searchBookList(@ModelAttribute BookSearch bookSearch, Model model) {
        log.info("bookSearch {} ", bookSearch.toString());

        String value = bookSearch.getValue();
        List<Book> books = null;
        if(bookSearch.searchType == BookSearch.SearchType.TITLE) {
            books = bookService.findByTitle(value);
        } else if(bookSearch.searchType == BookSearch.SearchType.AUTHOR) {
            books = bookService.findByAuthor(value);
        }

        model.addAttribute("books", books);
        return "/books/bookList";
    }

}
