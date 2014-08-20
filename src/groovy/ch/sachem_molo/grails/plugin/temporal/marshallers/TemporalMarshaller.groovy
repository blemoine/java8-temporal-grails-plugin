package ch.sachem_molo.grails.plugin.temporal.marshallers

import groovy.transform.CompileStatic
import org.codehaus.groovy.grails.web.converters.Converter
import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException
import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller

import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal

@CompileStatic
class TemporalMarshaller<T extends Temporal, C extends Converter> implements ObjectMarshaller<C> {

    DateTimeFormatter formatter
    Class<T> clazz

    TemporalMarshaller(Class<T> clazz, DateTimeFormatter formatter) {
        this.formatter = formatter
        this.clazz = clazz
    }

    @Override
    boolean supports(Object object) {
        clazz.isInstance(object)
    }

    @Override
    void marshalObject(Object object, C converter) throws ConverterException {
        T time = object as T
        converter.convertAnother(formatter.format(time))
    }


}
