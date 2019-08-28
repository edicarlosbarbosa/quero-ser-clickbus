package br.com.edicarlosbarbosa.clickbusapi.util;

import org.springframework.data.domain.PageRequest;

public class PaginationUtil {

    public static final int SIZE_LIMIT = 20;

    public static PageRequest build(Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }

        if (size == null || (size == 0 || size > SIZE_LIMIT)) {
            size = SIZE_LIMIT;
        }
        return PageRequest.of(page, size);
    }

}
