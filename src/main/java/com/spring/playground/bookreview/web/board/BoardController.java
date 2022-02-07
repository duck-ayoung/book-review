package com.spring.playground.bookreview.web.board;

import com.spring.playground.bookreview.domain.Board;
import com.spring.playground.bookreview.service.BoardService;
import com.spring.playground.bookreview.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BookService bookService;
    private final BoardService boardService;

    @GetMapping("review/{bookId}")
    public String bookReviewBoard(@PathVariable long bookId, Model model) {
        List<Board> boards = boardService.findByBook(bookId);
        model.addAttribute("boards", boards);
        return "boards/bookReviewBoard";
    }
}
