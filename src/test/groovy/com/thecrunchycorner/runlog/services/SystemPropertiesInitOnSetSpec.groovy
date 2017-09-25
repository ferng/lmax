package com.thecrunchycorner.lmax.services

import spock.lang.Specification

class SystemPropertiesInitOnSetSpec extends Specification {

    def 'test'() {
        given:
        def key = "unit.test.value.inittrigger"
        def value = "74"
        def loadedKey = "unit.test.value.fromfile"
        def loadedValue = "Test data from properties file"

        when:
        SystemProperties.set(key, value)

        then:
        SystemProperties.get(loadedKey) == loadedValue
    }

}
