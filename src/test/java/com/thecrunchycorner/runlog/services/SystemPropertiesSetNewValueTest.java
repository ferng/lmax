package com.thecrunchycorner.runlog.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SystemPropertiesSetNewValueTest {
    String key = "unit.test.value.newvalue";
    String value = "New key value pair";

    @Before
    public void setUp() throws Exception {
        SystemPropertiesFactory.loadSystemProperties();
    }


    @After
    public void tearDown() throws Exception {
        SystemProperties.remove(key);
    }


    @Test
    public void testSetter() {
        SystemProperties.setProperty(key, value);

        assertThat(SystemProperties.get(key), is(value));
    }
}