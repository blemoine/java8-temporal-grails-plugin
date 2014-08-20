package ch.sachem_molo.grails.plugin.temporal.marshallers

import org.codehaus.groovy.grails.web.converters.Converter
import spock.lang.Specification

import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TemporalMarshallerSpec extends Specification {

    def "Should support type passed in parameter"() {
        when:
        TemporalMarshaller marshaller = new TemporalMarshaller(Instant, DateTimeFormatter.ISO_INSTANT)

        then:
        marshaller.supports(Instant.now())
        !marshaller.supports(LocalDate.now())
    }


    def "Should convert to format passed in parameter"() {
        given:

        LocalDate localDate = LocalDate.of(2012, 3, 14)
        TemporalMarshaller marshaller = new TemporalMarshaller(LocalDate, DateTimeFormatter.ISO_LOCAL_DATE)
        def converter = Mock(Converter)

        when:
        marshaller.marshalObject(localDate, converter)

        then:
        1 * converter.convertAnother('2012-03-14')
    }
}
