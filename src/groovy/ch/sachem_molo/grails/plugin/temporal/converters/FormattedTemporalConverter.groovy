package ch.sachem_molo.grails.plugin.temporal.converters

import groovy.transform.CompileStatic
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.grails.databinding.converters.FormattedValueConverter

import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal

/**
 * A FormattedValueConverter for Temporal type which can be used in grails by declaring in the spring config file (resources.groovy):
 *
 * nameOfMyAwesomeFormattedValueConverter(FormattedTemporalConverter, TypeOfTheTemporalToConvert)
 *
 * eg.
 * localDateTimeFormattedDataBindingConverter(FormattedTemporalConverter, LocalDateTime)
 *
 *
 * And, in the class where we want to use this converter, we should use the annotation:
 * @BindingFormat ( value = ' myFormat ' )
 *
 *
 *
 *
 * @param < T > the target type of the converter
 */
@CompileStatic
class FormattedTemporalConverter<T extends Temporal> extends TemporalConverter<T> implements FormattedValueConverter {
    private static final Log log = LogFactory.getLog(this)
    /**
     *
     * @param targetType the type that the convert should ouput. This type cannot be null,
     * and should have a static method <code>from</code> taking a TemporalAccessor.
     *
     * @throws NullPointerException if targetType is null
     * @throws IllegalArgumentException if the type T has no method <code>from</code>
     */
    FormattedTemporalConverter(Class<T> targetType) {
        super(targetType)

        log.debug "Registering a Formatted Value Converter for $targetType"
    }

    /**
     * Convert the object value using the format
     *
     * @param value the value to convert
     * @param format the pattern format which should be used to convert this value
     * @return the temporal corresponding to the value
     */
    @Override
    T convert(Object value, String format) {
        convert(value, DateTimeFormatter.ofPattern(format))
    }

}
