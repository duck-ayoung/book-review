package com.spring.playground.bookreview.web.book;

import com.spring.playground.bookreview.domain.Book;
import com.spring.playground.bookreview.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String bookList(@ModelAttribute BookSearch bookSearch, Model model) {
        log.info("bookList");
        List<Book> books = bookService.findByAll();
        model.addAttribute("books", books);
        return "/books/bookList";
    }

    @PostMapping
    public String searchBookList(@ModelAttribute BookSearch bookSearch, Model model) {
        log.info("bookList");
        String title = bookSearch.getTitle();
        List<Book> books = bookService.findByTitle(title);
        model.addAttribute("books", books);
        return "/books/bookList";
    }

}
