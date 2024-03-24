package org.example.core;

import org.openqa.selenium.WebElement;

class PageBuilder<T> {
    PaginationReader paginationReader;
    ContentsReader<T> contentsReader;
    WebElement webElement;

    public PageBuilder<T> paginationReader(PaginationReader paginationReader) {
        this.paginationReader = paginationReader;
        return this;
    }

    public PageBuilder<T> contentsReader(ContentsReader<T> contentsReader) {
        this.contentsReader = contentsReader;
        return this;
    }


    public PageBuilder<T> webElement(WebElement webElement) {
        this.webElement = webElement;
        return this;
    }


    public Page<T> build() {
        return new Page<>(
                paginationReader,
                contentsReader,
                webElement
        );
    }
}
