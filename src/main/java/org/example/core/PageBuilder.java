package org.example.core;

import org.openqa.selenium.WebDriver;

public class PageBuilder<T> {
    PageInitializer pageInitializer;
    PaginationReader paginationReader;
    ContentsReader<T> contentsReader;
    WebDriver webDriver;
    PageContentsLoader pageContentsLoader;
    PageReadStopper pageReadStopper;


    public PageBuilder<T> pageInitializer(PageInitializer pageInitializer) {
        this.pageInitializer = pageInitializer;
        return this;
    }

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


    public PageBuilder<T> webDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
        return this;
    }


    public Page<T> build() {
        return new Page<>(
                pageInitializer,
                paginationReader,
                contentsReader,
                webDriver,
                pageContentsLoader,
                pageReadStopper
        );
    }
}
