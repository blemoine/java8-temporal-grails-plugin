package ch.sachem_molo.grails.plugin.temporal.marshallers

import grails.converters.XML
import grails.test.spock.IntegrationSpec
import spock.lang.Unroll

import java.time.*

class TemporalMarshallerIntegrationSpec extends IntegrationSpec {


    void setupSpec() {
        TemporalMarshallersRegisterer.registerMarshallers()
    }

    @Unroll
    def "should be able to correctly convert Temporals to XML"() {
        when:
        def result = new XML(temporal)

        then:
        result.toString() == '<?xml version="1.0" encoding="UTF-8"?>' + expected

        where:
        temporal                                                                | expected
        ZonedDateTime.of(2013, 2, 3, 14, 32, 12, 0, ZoneOffset.UTC).toInstant() | '<instant>2013-02-03T14:32:12Z</instant>'
        Year.of(1902)                                                           | '<year>1902</year>'
        YearMonth.of(1830, 11)                                                  | '<yearMonth>1830-11</yearMonth>'
        LocalDate.of(1999, 1, 1)                                                | '<localDate>1999-01-01</localDate>'
        LocalDateTime.of(2001, 2, 23, 12, 34, 2)                                | '<localDateTime>2001-02-23T12:34:02</localDateTime>'
        LocalTime.of(14, 54, 59)                                                | '<localTime>14:54:59</localTime>'
        ZonedDateTime.of(2013, 2, 3, 14, 32, 12, 0, ZoneOffset.UTC)             | '<zonedDateTime>2013-02-03T14:32:12Z</zonedDateTime>'

    }


}
