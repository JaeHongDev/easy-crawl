package org.example;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import org.example.core.ContentsReader;
import org.example.core.Page;
import org.example.core.PageBuilder;
import org.example.core.PageContentsLoader;
import org.example.core.PageInitializer;
import org.example.core.PaginationInformation;
import org.example.core.PaginationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    public static void main(String[] args) {

        var executor = Executors.newFixedThreadPool(4);

        var completableFutures = Stream.generate(() -> CompletableFuture.runAsync(Main::run, executor))
                .limit(10)
                .toList();

        completableFutures
                .forEach(CompletableFuture::join);


    }

    static void run() {

        var page = page();

        var result = page.execute();
        var sb = new StringBuilder();
        while (Objects.nonNull(result)) {
            result = page.execute();
            sb.append(result).append("\n");
        }
        System.out.println(sb);


    }

    private static PaginationReader paginationReader() {
        return webDriver -> new PaginationInformation(webDriver.findElement(By.className("pagination"))
                .findElements(By.className("page"))
                .stream()
                .map(WebElement::getText)
                .mapToInt(Integer::parseInt)
                .max()
                .orElseThrow(IllegalArgumentException::new));
    }

    public static Page page() {
        return new PageBuilder<String>()
                .webDriver(new FirefoxDriver())
                .pageContentsLoader(pageContentsLoader())
                .paginationReader(paginationReader())
                .pageInitializer(pageInitializer())
                .contentsReader(contentsReader())
                .build();
    }

    static PageInitializer pageInitializer() {
        return pageNumber -> "https://techblog.lycorp.co.jp/ko?page=" + pageNumber;
    }

    static ContentsReader contentsReader() {
        return webDriver -> "";
    }

    static PageContentsLoader pageContentsLoader() {
        return webDriverWait -> webDriverWait.until(visibilityOfElementLocated(By.className("pagination")));

    }
}