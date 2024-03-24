package org.example.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface PageContentsLoader{

    void waitUntilLoad(WebDriverWait webDriverWait);
}
