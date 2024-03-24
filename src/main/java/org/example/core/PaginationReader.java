package org.example.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface PaginationReader {
    PaginationInformation read(WebDriver webDriver);
}
