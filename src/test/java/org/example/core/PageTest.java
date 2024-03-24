package org.example.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class PageTest {

    @Test
    void 최대_페이지까지_도달했을_때_null_을_반환합니다() {

        var page = new PageBuilder<String>()
                .paginationReader((webElement) -> new PaginationInformation(5))
                .contentsReader((webElement) -> "")
                .build();

        assertAll(
                () -> assertThat(page.execute()).isEqualTo(""),
                () -> assertThat(page.execute()).isEqualTo(""),
                () -> assertThat(page.execute()).isEqualTo(""),
                () -> assertThat(page.execute()).isEqualTo(""),
                () -> assertThat(page.execute()).isEqualTo(null)
        );
    }


    @Test
    void 예외_페이지를_찾는_경우_null을_반환합니다() {

    }

}