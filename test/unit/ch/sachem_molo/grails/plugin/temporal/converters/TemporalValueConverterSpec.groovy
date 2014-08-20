package ch.sachem_molo.grails.plugin.temporal.converters

import spock.lang.Specification

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.Temporal

class TemporalValueConverterSpec extends Specification {

    def "Temporal converter should not accept null as targetType"() {
        when:
        new TemporalValueConverter<>(null, DateTimeFormatter.ISO_DATE)

        then:
        thrown NullPointerException
    }

    def "Temporal converter should not accept a class that has no 'from' static method"() {
        when:
        new TemporalValueConverter(Temporal, DateTimeFormatter.ISO_DATE)

        then:
        thrown IllegalArgumentException
    }

    def "Temporal converter should not accept null as formatter"() {
        when:
        new TemporalValueConverter<>(Instant, null)

        then:
        thrown NullPointerException
    }

    def "Temporal Converter should be able to convert String"() {
        given:
        def converter = new TemporalValueConverter<>(LocalTime, DateTimeFormatter.ISO_LOCAL_TIME)

        when:
        boolean result = converter.canConvert(param)

        then:
        result

        where:
        param << ['', 'test', '2013-12-12']
    }

    def "TemporalConverter should target the type passed in constructor"() {
        given:
        def converter = new TemporalValueConverter(targetType, DateTimeFormatter.ISO_DATE_TIME)

        when:
        def clazz = converter.targetType

        then:
        clazz == targetType

        where:
        targetType << [Instant, Year, LocalDate, LocalDateTime]
    }

    def "Temporal Converter should convert correctly temporal"() {
        given:
        def converter = new TemporalValueConverter(expectedResult.class, formatter)

        when:
        def result = converter.convert(expectedResult.toString())

        then:
        result == expectedResult

        where:
        formatter                             || expectedResult
        DateTimeFormatter.ISO_INSTANT         || Instant.now()
        DateTimeFormatter.ISO_LOCAL_TIME      || LocalTime.now()
        DateTimeFormatter.ISO_LOCAL_DATE_TIME || LocalDateTime.now()
    }

    def "Temporal Converter should be null safe"() {
        given:
        def converter = new TemporalValueConverter(Instant, DateTimeFormatter.ISO_INSTANT)

        when:
        def result = converter.convert(null)

        then:
        result == null
    }

    def "Temporal Converter should throw exception if param is malformed"() {
        given:
        def converter = new TemporalValueConverter(LocalDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        when:
        converter.convert(malFormed)

        then:
        thrown DateTimeParseException

        where:
        malFormed << ['', 'test', '2012-12-11']
    }
}
