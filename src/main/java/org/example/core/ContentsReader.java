package org.example.core;

import org.openqa.selenium.WebElement;

public interface ContentsReader<T> {
    T read(WebElement webElement);
}
