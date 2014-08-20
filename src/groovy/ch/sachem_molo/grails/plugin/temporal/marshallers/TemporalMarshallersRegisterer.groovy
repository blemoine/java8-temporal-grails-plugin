package ch.sachem_molo.grails.plugin.temporal.marshallers

import grails.converters.JSON
import grails.converters.XML
import org.apache.commons.logging.LogFactory

import java.time.*
import java.time.format.DateTimeFormatter

/**
 * Helper class to register default marshaller for JSON and XML grails converters
 */
class TemporalMarshallersRegisterer {

    private static final log = LogFactory.getLog(this)

    static void registerMarshallers() {
        log.debug 'Registering converters for JSON and XML'
        [JSON, XML].each { converter ->
            converter.registerObjectMarshaller(new TemporalMarshaller(Instant, DateTimeFormatter.ISO_INSTANT))
            converter.registerObjectMarshaller(new TemporalMarshaller(Year, DateTimeFormatter.ofPattern('yyyy')))
            converter.registerObjectMarshaller(new TemporalMarshaller(YearMonth, DateTimeFormatter.ofPattern('yyyy-MM')))
            converter.registerObjectMarshaller(new TemporalMarshaller(LocalDate, DateTimeFormatter.ISO_LOCAL_DATE))
            converter.registerObjectMarshaller(new TemporalMarshaller(LocalDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            converter.registerObjectMarshaller(new TemporalMarshaller(LocalTime, DateTimeFormatter.ISO_LOCAL_TIME))
            converter.registerObjectMarshaller(new TemporalMarshaller(ZonedDateTime, DateTimeFormatter.ISO_ZONED_DATE_TIME))
        }
    }
}
