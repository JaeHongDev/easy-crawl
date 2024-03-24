package org.example.core;

import org.openqa.selenium.WebDriver;

public interface ContentsReader<T> {
    T read(WebDriver webDriver);
}
