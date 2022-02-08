package com.spring.playground.bookreview.web.board;

import com.spring.playground.bookreview.domain.Board;
import com.spring.playground.bookreview.domain.Book;
import com.spring.playground.bookreview.domain.Member;
import com.spring.playground.bookreview.repository.MemberRepository;
import com.spring.playground.bookreview.service.BoardService;
import com.spring.playground.bookreview.service.BookService;
import com.spring.playground.bookreview.service.MemberService;
import com.spring.playground.bookreview.web.common.SessionConst;
import com.spring.playground.bookreview.web.member.MemberSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final MemberService memberService;
    private final BookService bookService;
    private final BoardService boardService;

    @GetMapping("review/{bookId}")
    public String bookReviewBoard(@PathVariable long bookId, Model model) {
        List<Board> boards = boardService.findByBook(bookId);
        model.addAttribute("boards", boards);
        return "boards/bookReviewBoard";
    }

    @GetMapping("add/{bookId}")
    public String addBoard(@PathVariable long bookId, Model model) {
        model.addAttribute("boardForm", new BoardForm());
        return "boards/addBoardForm";
    }

    @PostMapping("add/{bookId}")
    public String saveBoard(@PathVariable long bookId, @ModelAttribute BoardForm boardForm, BindingResult bindingResult,
                            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberSession memberSession) {
        if(bindingResult.hasErrors()) {
            log.info("apply fail errors={}", bindingResult);
            return "/boards/addBoardForm";
        }

        Optional<Member> member = memberService.findByLoginId(memberSession.getLoginId());
        boardService.save(boardForm.getTitle(), boardForm.getContent(), member.get().getId(), bookId);

        return "redirect:/boards/review/{bookId}";
    }
}
