package com.wtychn;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class JsonTest {

    @Test
    public void jsonTest() throws IOException {
        Json jsonDate = new Json("src\\main\\java\\com\\wtychn\\src.json");
        jsonDate.updateDate();
        System.out.println(jsonDate.toForm());
    }
}