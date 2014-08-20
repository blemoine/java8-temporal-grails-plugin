package ch.sachem_molo.grails.plugin.temporal.converters

import groovy.transform.CompileStatic

import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import java.time.temporal.TemporalAccessor
import java.time.temporal.TemporalQuery

/**
 * The main objective of this class is to provide a simple mean to convert
 * a String to the Temporal.
 *
 * @param < T > a Temporal representing the output type of the converter
 */
@CompileStatic
class TemporalConverter<T extends Temporal> {


    final Class<T> targetType

    /**
     *
     * @param targetType the type that the convert should ouput. This type cannot be null,
     * and should have a static method <code>from</code> taking a TemporalAccessor.
     *
     * @throws NullPointerException if targetType is null
     * @throws IllegalArgumentException if the type T has no method <code>from</code>
     */
    TemporalConverter(Class<T> targetType) {
        Objects.requireNonNull(targetType, 'The targetType should not be null')
        this.targetType = targetType
        requireFromMethod()
    }

    /**
     * Convert an object to the Temporal target type.
     *
     *
     * @param value the value to convert
     * @param formatter the formatter to use if the value is a String
     * @return the temporal value of the first method parameter
     */
    T convert(Object value, DateTimeFormatter formatter) {
        if (value == null) {
            null
        } else if (value instanceof T) {
            value as T
        } else {
            formatter.parse(value as String, temporalQuery) as T
        }
    }

    /**
     * Return the default temporal query associated with the TemporalConverter.
     * Here, we use the static <code>from</code> method.
     *
     * @return the temporal query used to transform the value to temporal
     */
    protected TemporalQuery<T> getTemporalQuery() {
        targetType.&from as TemporalQuery<T>
    }

    private void requireFromMethod() {
        try {
            targetType.getDeclaredMethod('from', TemporalAccessor)
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("the targetType $targetType should have a static from method taking a TemporalAccessor as parameter", e)
        }
    }
}
