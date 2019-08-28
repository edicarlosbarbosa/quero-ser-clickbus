package br.com.edicarlosbarbosa.clickbusapi.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.PageRequest;

import static br.com.edicarlosbarbosa.clickbusapi.util.PaginationUtil.SIZE_LIMIT;
import static br.com.edicarlosbarbosa.clickbusapi.util.PaginationUtil.build;

@RunWith(PowerMockRunner.class)
public class PaginationUtilTest {

    @Test
    public void build_WithPageAndSizeZero() {
        int page = 0;
        int size = 0;

        PageRequest actual = build(page, size);

        Assert.assertEquals(page, actual.getPageNumber());
        Assert.assertEquals(SIZE_LIMIT, actual.getPageSize());
    }

    @Test
    public void build_WithPageAndSizeNull() {
        PageRequest actual = build(null, null);

        Assert.assertEquals(0, actual.getPageNumber());
        Assert.assertEquals(SIZE_LIMIT, actual.getPageSize());
    }

    @Test
    public void build_ExceedSizeLimit() {
        PageRequest actual = build(null, 900000);

        Assert.assertEquals(0, actual.getPageNumber());
        Assert.assertEquals(SIZE_LIMIT, actual.getPageSize());
    }

    @Test
    public void build_CustomPageAndSize() {
        PageRequest actual = build(5, 10);

        Assert.assertEquals(5, actual.getPageNumber());
        Assert.assertEquals(10, actual.getPageSize());
    }

}
