package ch.sachem_molo.grails.plugin.temporal.converters

import groovy.transform.CompileStatic
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.grails.databinding.converters.ValueConverter

import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal

/**
 * A ValueConverter which can be used by grails by declaring in the spring config file (resources.groovy):
 *
 * nameOfMyAwesomeConverter(TemporalValueConverter, ClassOfTemporalToConvert, DateTimeFormatter.ofPattern(awesomePattern))
 *
 * eg.
 * yearDataBindingConverter(TemporalValueConverter, Year, DateTimeFormatter.ofPattern('yyyy'))
 *
 * @param < T > the type of the Temporal which this class is targeting
 */
@CompileStatic
class TemporalValueConverter<T extends Temporal> extends TemporalConverter<T> implements ValueConverter {

    private static final Log log = LogFactory.getLog(this)

    private final DateTimeFormatter formatter

    /**
     *
     *
     * @param targetType the type that the convert should ouput. This type cannot be null,
     * and should have a static method <code>from</code> taking a TemporalAccessor.
     * @param formatter the formatter used to transform a String to the Temporal T. Should not be null
     *
     * @throws NullPointerException if targetType is null of formatter is null
     * @throws IllegalArgumentException if the type T has no method <code>from</code>

     */
    TemporalValueConverter(Class<T> targetType, DateTimeFormatter formatter) {
        super(targetType)

        Objects.requireNonNull(formatter, 'The formatter should not be null')
        this.formatter = formatter

        log.debug "Registering a Value Converter for $targetType with formatter $formatter"
    }

    /**
     * Is the parameter convertible to the target type T
     *
     * @param value the object that we want to convert to target type T
     * @return true if the object is convertible, false otherwise
     */
    @Override
    boolean canConvert(Object value) {
        value instanceof String
    }

    /**
     * Convert the value to the target type T.
     *
     * canConvert should return true when called with this value.
     *
     * @param value the value to convert
     * @return the temporal corresponding to the value
     */
    @Override
    T convert(Object value) {
        convert(value, formatter)
    }
}
