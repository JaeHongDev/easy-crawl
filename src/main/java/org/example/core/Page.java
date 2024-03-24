package org.example.core;


import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page<T> {

    private final PaginationReader paginationReader;
    private final ContentsReader<T> contentsReader;
    private final PageContentsLoader pageContentsLoader;
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;
    int currentPage = 1;

    protected Page(
            PaginationReader paginationReader,
            ContentsReader<T> contentsReader,
            WebDriver webDriver,
            PageContentsLoader pageContentsLoader
    ) {
        this.paginationReader = paginationReader;
        this.contentsReader = contentsReader;
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        this.pageContentsLoader = pageContentsLoader;
    }

    public T execute() {
        pageContentsLoader.waitUntilLoad(webDriverWait);

        final var result = contentsReader.read(webDriver);
        final var paginationInformation = paginationReader.read(webDriver);

        if (paginationInformation.isContinue(currentPage++)) {
            return result;
        }
        return null;
    }


}
