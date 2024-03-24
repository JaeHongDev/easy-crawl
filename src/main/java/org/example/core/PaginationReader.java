package org.example.core;

import org.openqa.selenium.WebElement;

public interface PaginationReader {
    PaginationInformation read(WebElement webElement);
}
