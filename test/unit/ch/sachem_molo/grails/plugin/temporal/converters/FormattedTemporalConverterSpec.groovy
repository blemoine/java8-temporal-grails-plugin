package ch.sachem_molo.grails.plugin.temporal.converters

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Year
import java.time.format.DateTimeParseException
import java.time.temporal.Temporal

class FormattedTemporalConverterSpec extends Specification {

    def "FormattedTemporalConverter should not accept null as targetType"() {
        when:
        new FormattedTemporalConverter(null)

        then:
        thrown NullPointerException
    }

    def "FormattedTemporalConverter should not accept a class that has no 'from' static method"() {
        when:
        new FormattedTemporalConverter(Temporal)

        then:
        thrown IllegalArgumentException
    }

    def "FormattedTemporalConverter should target the type passed in constructor"() {
        given:
        def converter = new FormattedTemporalConverter(targetType)

        when:
        def clazz = converter.targetType

        then:
        clazz == targetType

        where:
        targetType << [Year, LocalDate, LocalDateTime]
    }



    def "FormattedTemporalConverter should convert correctly temporal"() {
        given:
        def converter = new FormattedTemporalConverter(expectedResult.class)

        when:
        def result = converter.convert(input, format)

        then:
        result == expectedResult

        where:
        input        | expectedResult            | format
        '12 23 45'   | LocalTime.of(12, 23, 45)  | 'HH mm ss'
        '12/01/1910' | LocalDate.of(1910, 1, 12) | 'dd/MM/yyyy'
    }

    def "FormattedTemporalConverter should be null safe"() {
        given:
        def converter = new FormattedTemporalConverter(LocalDate)

        when:
        def result = converter.convert(null, 'dd-MM-yyyy')

        then:
        result == null
    }

    def "FormattedTemporalConverter should throw exception if param is malformed"() {
        given:
        def converter = new FormattedTemporalConverter(LocalDate)

        when:
        converter.convert(malFormed, 'dd-MM-yyyy')

        then:
        thrown DateTimeParseException

        where:
        malFormed << ['', 'test', '2012-12-11']
    }


}
