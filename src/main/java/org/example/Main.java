package org.example;

import java.util.Objects;
import org.example.core.PageBuilder;
import org.example.core.PaginationInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Main {
    public static void main(String[] args) {

        var firefoxDriver = new FirefoxDriver();

        var TARGET_URL = "https://techblog.lycorp.co.jp/ko";

        var page = new PageBuilder<String>()
                .webDriver(firefoxDriver)
                .pageContentsLoader(webDriverWait -> webDriverWait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.className("pagination"))))
                .pageInitializer(number -> TARGET_URL + "?page=" + number)
                .paginationReader(
                        webDriver -> new PaginationInformation(webDriver.findElement(By.className("pagination"))
                                .findElements(By.className("page"))
                                .stream()
                                .map(WebElement::getText)
                                .mapToInt(Integer::parseInt)
                                .max()
                                .orElseThrow(IllegalArgumentException::new)))
                .contentsReader(webDriver -> "")
                .build();

        var result = page.execute();
        while (Objects.nonNull(result)) {
            result = page.execute();
        }

        System.out.println("Hello world!");
    }
}