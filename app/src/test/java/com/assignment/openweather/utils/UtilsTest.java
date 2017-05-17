package com.assignment.openweather.utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by mudit-agarwal on 17-05-2017.
 */
public class UtilsTest {

    @Test
    public void getDateVerbose() throws Exception {
        String dateStr = "2017-05-17";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateStr);
        assertEquals(dateStr, Utils.getDateVerbose(date));
    }

    @Test
    public void isNotEmptyArray() throws Exception {
        assertEquals(true, Utils.isNotEmptyArray(new String[]{"1"}));
        assertEquals(false, Utils.isNotEmptyArray(new String[]{}));
        assertEquals(false, Utils.isNotEmptyArray(null));
    }

    @Test
    public void isNotEmptyList() throws Exception {
        ArrayList<String> test = new ArrayList<>();
        test.add("test");
        assertEquals(true, Utils.isNotEmptyList(test));
        assertEquals(false, Utils.isNotEmptyList(new ArrayList()));
        assertEquals(false, Utils.isNotEmptyList(null));
    }

}