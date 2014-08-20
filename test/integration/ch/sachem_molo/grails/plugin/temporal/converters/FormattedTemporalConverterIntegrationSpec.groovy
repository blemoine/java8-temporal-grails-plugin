package ch.sachem_molo.grails.plugin.temporal.converters

import grails.test.spock.IntegrationSpec
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.BindingFormat
import org.grails.databinding.SimpleMapDataBindingSource
import spock.lang.Unroll

import java.time.*

class FormattedTemporalConverterIntegrationSpec extends IntegrationSpec {

    GrailsWebDataBinder grailsWebDataBinder


    @Unroll
    def "FormatedTemporalConverter should be able to databind all Temporal"() {
        given:
        TestTemporal testTemporal = new TestTemporal()

        when:
        grailsWebDataBinder.bind(testTemporal, [
                year         : "$month/$year",
                yearMonth    : "$month/$year",
                localTime    : "$hour $minutes $seconds",
                localDate    : "$day/$month/$year",
                localDateTime: "$day/$month/$year $hour $minutes $seconds",
        ] as SimpleMapDataBindingSource)

        then:

        testTemporal.year == Year.of(year)
        testTemporal.yearMonth == YearMonth.of(year, month as Integer)
        testTemporal.localTime == LocalTime.of(hour as Integer, minutes as Integer, seconds as Integer)
        testTemporal.localDate == LocalDate.of(year, month as Integer, day as Integer)
        testTemporal.localDateTime == LocalDateTime.of(year, month as Integer, day as Integer, hour as Integer, minutes as Integer, seconds as Integer)

        where:
        year | month | day  | hour | minutes | seconds
        1910 | '01'  | '01' | '01' | '01'    | '01'
        2013 | '02'  | '12' | '14' | '31'    | '31'
        2000 | '12'  | '27' | '23' | '59'    | '59'
    }

    private class TestTemporal {
        @BindingFormat(value = 'MM/yyyy')
        Year year
        @BindingFormat(value = 'MM/yyyy')
        YearMonth yearMonth
        @BindingFormat(value = 'HH mm ss')
        LocalTime localTime
        @BindingFormat(value = 'dd/MM/yyyy')
        LocalDate localDate
        @BindingFormat(value = 'dd/MM/yyyy HH mm ss')
        LocalDateTime localDateTime
    }
}
