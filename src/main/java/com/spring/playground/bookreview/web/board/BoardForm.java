package com.spring.playground.bookreview.web.board;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class BoardForm {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public BoardForm() {
    }

    public BoardForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
