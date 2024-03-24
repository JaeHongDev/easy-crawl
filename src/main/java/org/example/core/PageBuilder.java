package org.example.core;

import org.openqa.selenium.WebDriver;

class PageBuilder<T> {
    PaginationReader paginationReader;
    ContentsReader<T> contentsReader;
    WebDriver webDriver;
    PageContentsLoader pageContentsLoader;

    public PageBuilder<T> paginationReader(PaginationReader paginationReader) {
        this.paginationReader = paginationReader;
        return this;
    }

    public PageBuilder<T> contentsReader(ContentsReader<T> contentsReader) {
        this.contentsReader = contentsReader;
        return this;
    }

    public PageBuilder<T> pageContentsLoader(PageContentsLoader pageContentsLoader) {
        this.pageContentsLoader = pageContentsLoader;
        return this;
    }


    public PageBuilder<T> webElement(WebDriver webDriver) {
        this.webDriver = webDriver;
        return this;
    }


    public Page<T> build() {
        return new Page<>(
                paginationReader,
                contentsReader,
                webDriver,
                pageContentsLoader

        );
    }
}
