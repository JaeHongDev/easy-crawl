package org.example.core;


import org.openqa.selenium.WebElement;

public class Page<T> {

    private final PaginationReader paginationReader;
    private final ContentsReader<T> contentsReader;
    private final WebElement webElement;

    protected Page(PaginationReader paginationReader, ContentsReader<T> contentsReader, WebElement webElement) {
        this.paginationReader = paginationReader;
        this.contentsReader = contentsReader;
        this.webElement = webElement;
    }

    int currentPage = 1;


    public T execute() {
        var result = contentsReader.read();
        var paginationInformation = paginationReader.read();

        if (paginationInformation.isContinue(currentPage++)) {
            return result;
        }
        return null;
    }


    static class PageBuilder<T> {
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


        public Page<T> build(){
            return new Page<>(
                    paginationReader,
                    contentsReader,
                    webElement
            );
        }
    }

}
