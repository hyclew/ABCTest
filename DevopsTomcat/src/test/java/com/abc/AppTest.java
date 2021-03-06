package com.abc;

import com.abc.test.App;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
    private App app;
    @Before
    public void setUp() {
        app = new App();
    }
    @Test
    public void testAdd() {
        int a = 1;
        int b = 2;
        int result = app.add(a, b);
        Assert.assertEquals(a + b, result);
    }
    @Test()
    public void testSubtract() {
        int a = 1;
        int b = 2;
        int result = app.subtract(a, b);
        Assert.assertEquals(a - b, result);
    }
    @After
    public void tearDown() {
    }
}
