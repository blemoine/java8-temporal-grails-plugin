package ch.sachem_molo.grails.plugin.temporal.marshallers

import grails.converters.JSON
import grails.converters.XML

import java.time.*
import java.time.format.DateTimeFormatter

class TemporalMarshallersRegisterer {

    static void registerMarshallers() {
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
