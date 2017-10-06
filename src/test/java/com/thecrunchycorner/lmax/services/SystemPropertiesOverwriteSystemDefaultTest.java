package com.thecrunchycorner.lmax.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;
import org.junit.After;
import org.junit.Test;

public class SystemPropertiesOverwriteSystemDefaultTest {
    @After
    public void tearDown() throws Exception {
        SystemProperties.refreshProperties();
    }


    @Test
    public void test() {
        final String value = "This value overwrites the system default";
        final String key = "unit.test.value.systemdefault";
        SystemProperties.set(key, value);

        assertThat(SystemProperties.get(key), is(Optional.of(value)));
    }
}