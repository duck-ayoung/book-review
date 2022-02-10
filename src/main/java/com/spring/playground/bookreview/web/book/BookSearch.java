package com.spring.playground.bookreview.web.book;

import lombok.Data;

@Data
public class BookSearch {

    enum SearchType {
        TITLE("제목"),
        AUTHOR("작가");

        private final String description;

        SearchType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    String value;
    SearchType searchType;
}
