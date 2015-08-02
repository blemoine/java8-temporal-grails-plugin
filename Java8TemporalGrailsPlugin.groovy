import ch.sachem_molo.grails.plugin.temporal.converters.FormattedTemporalConverter
import ch.sachem_molo.grails.plugin.temporal.converters.TemporalValueConverter
import ch.sachem_molo.grails.plugin.temporal.marshallers.TemporalMarshaller
import ch.sachem_molo.grails.plugin.temporal.marshallers.TemporalMarshallersRegisterer
import grails.converters.JSON
import grails.converters.XML

import java.time.*
import java.time.format.DateTimeFormatter

class Java8TemporalGrailsPlugin {

    def version = "0.3"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.5 > *"

    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]


    def title = "Java 8 new Date and Temporal API support plugin" // Headline display name of the plugin
    def author = "Benoit Lemoine"
    def authorEmail = "lemoine.benoit@gmail.com"
    def description = '''\
Plugin for supporting the new Java 8 Date and Temporal API:
   *  Support of DataBinding
   *  Support in Gorm
   *  Support of marshalling in JSON and XML
'''

    // URL to the plugin's documentation
    //TODO branche Gh-pages sur github
    def documentation = "https://github.com/blemoine/java8-temporal-grails-plugin"

    def license = "APACHE"


   def issueManagement = [ system: "GitHub", url: "https://github.com/blemoine/java8-temporal-grails-plugin/issues" ]

    def scm = [ url: "https://github.com/blemoine/java8-temporal-grails-plugin" ]

    def doWithWebDescriptor = { xml ->

    }

    def doWithSpring = {

        yearDataBindingConverter(TemporalValueConverter, Year, DateTimeFormatter.ofPattern('yyyy'))
        yearMonthDataBindingConverter(TemporalValueConverter, YearMonth, DateTimeFormatter.ofPattern('yyyy-MM'))
        instantDataBindingConverter(TemporalValueConverter, Instant, DateTimeFormatter.ISO_INSTANT)
        localDateDataBindingConverter(TemporalValueConverter, LocalDate, DateTimeFormatter.ISO_LOCAL_DATE)
        localDateTimeDataBindingConverter(TemporalValueConverter, LocalDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        localTimeDataBindingConverter(TemporalValueConverter, LocalTime, DateTimeFormatter.ISO_LOCAL_TIME)
        zonedDateTimeDataBindingConverter(TemporalValueConverter, ZonedDateTime, DateTimeFormatter.ISO_ZONED_DATE_TIME)

        yearFormattedDataBindingConverter(FormattedTemporalConverter, Year)
        yearMonthFormattedDataBindingConverter(FormattedTemporalConverter, YearMonth)
        instantFormattedDataBindingConverter(FormattedTemporalConverter, Instant)
        localDateFormattedDataBindingConverter(FormattedTemporalConverter, LocalDate)
        localDateTimeFormattedDataBindingConverter(FormattedTemporalConverter, LocalDateTime)
        localTimeFormattedDataBindingConverter(FormattedTemporalConverter, LocalTime)
        zonedDateTimeFormattedDataBindingConverter(FormattedTemporalConverter, ZonedDateTime)
    }

    def doWithDynamicMethods = { ctx ->

    }

    def doWithApplicationContext = { ctx ->
        TemporalMarshallersRegisterer.registerMarshallers()
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }

    def onShutdown = { event ->
    }
}
