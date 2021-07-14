package com.abc;

import com.abc.test.Add;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddTest {
    Add add;
    @Before
    public void setUp() {
        add = new Add();
    }
    @Test
    public void testAdd() throws InterruptedException {
        int a = 1;
        int b = 2;
        int result = add.add(a, b);
        Assert.assertEquals(a + b, result);
    }
    @After
    public void tearDown() throws Exception {
    }
}
