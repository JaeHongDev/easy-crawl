package org.example.core;

import java.util.OptionalInt;


public record PaginationInformation(
        int maxPage,
        OptionalInt lastPage
) {

    public PaginationInformation(int maxPage) {
        this(maxPage, OptionalInt.empty());
    }

    public boolean isContinue(int currentPage) {
        return lastPage.orElse(maxPage) != currentPage;
    }
}
