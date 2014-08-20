package ch.sachem_molo.grails.plugin.temporal.converters

import grails.test.spock.IntegrationSpec
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import spock.lang.Unroll

import java.time.*

class TemporalValueConverterIntegrationSpec extends IntegrationSpec {

    GrailsWebDataBinder grailsWebDataBinder

    @Unroll
    def "temporal converter should be able to databind all Temporal"() {
        given:
        TestTemporal testTemporal = new TestTemporal()

        when:
        grailsWebDataBinder.bind(testTemporal, [
                instant      : "$year-$month-${day}T$hour:$minutes:${seconds}Z",
                year         : "$year",
                yearMonth    : "$year-$month",
                localTime    : "$hour:$minutes:$seconds",
                localDate    : "$year-$month-$day",
                localDateTime: "$year-$month-${day}T$hour:$minutes:$seconds",
                zonedDateTime: "$year-$month-${day}T$hour:$minutes:${seconds}$zone"
        ] as SimpleMapDataBindingSource)

        then:
        testTemporal.instant == ZonedDateTime.of(year, month as Integer, day as Integer, hour as Integer, minutes as Integer, seconds as Integer, 0, ZoneOffset.UTC).toInstant()
        testTemporal.year == Year.of(year)
        testTemporal.yearMonth == YearMonth.of(year, month as Integer)
        testTemporal.localTime == LocalTime.of(hour as Integer, minutes as Integer, seconds as Integer)
        testTemporal.localDate == LocalDate.of(year, month as Integer, day as Integer)
        testTemporal.localDateTime == LocalDateTime.of(year, month as Integer, day as Integer, hour as Integer, minutes as Integer, seconds as Integer)
        testTemporal.zonedDateTime == ZonedDateTime.of(year, month as Integer, day as Integer, hour as Integer, minutes as Integer, seconds as Integer, 0, zone)

        where:
        year | month | day  | hour | minutes | seconds | zone
        1910 | '01'  | '01' | '01' | '01'    | '01'    | ZoneOffset.UTC
        2013 | '02'  | '12' | '14' | '31'    | '31'    | ZoneOffset.ofHours(2)
        2000 | '12'  | '27' | '23' | '59'    | '59'    | ZoneOffset.ofHours(3)
    }


    private class TestTemporal {
        Instant instant
        Year year
        YearMonth yearMonth
        LocalTime localTime
        LocalDate localDate
        LocalDateTime localDateTime
        ZonedDateTime zonedDateTime
    }
}
