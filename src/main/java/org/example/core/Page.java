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
        final var result = contentsReader.read(webElement);
        final var paginationInformation = paginationReader.read(webElement);

        if (paginationInformation.isContinue(currentPage++)) {
            return result;
        }
        return null;
    }


}
