package org.example.core;


import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page<T> {

    private final PageInitializer pageInitializer;
    private final PaginationReader paginationReader;
    private final ContentsReader<T> contentsReader;
    private final PageContentsLoader pageContentsLoader;
    private final PageReadStopper pageReadStopper;
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    int currentPage = 1;

    protected Page(
            PageInitializer pageInitializer,
            PaginationReader paginationReader,
            ContentsReader<T> contentsReader,
            WebDriver webDriver,
            PageContentsLoader pageContentsLoader,
            PageReadStopper pageReadStopper
    ) {
        this.pageInitializer = pageInitializer;
        this.paginationReader = paginationReader;
        this.contentsReader = contentsReader;
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        this.pageContentsLoader = pageContentsLoader;
        this.pageReadStopper = pageReadStopper;
    }


    public T execute() {
        final var url = pageInitializer.getUrl(currentPage);
        webDriver.get(url);

        if (Objects.nonNull(pageReadStopper) && pageReadStopper.isSatisfy(webDriver)) {
            return clear();
        }

        pageContentsLoader.waitUntilLoad(webDriverWait);

        final var result = contentsReader.read(webDriver);
        final var paginationInformation = paginationReader.read(webDriver);

        if (paginationInformation.isContinue(currentPage++)) {
            return result;
        }
        return clear();
    }

    T clear() {
        CompletableFuture.runAsync(webDriver::close);
        return null;
    }


}
