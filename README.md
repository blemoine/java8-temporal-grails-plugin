Java8 Temporal Grails Plugin
============================
The Java 8 temporal Plugin integrates the new DateTime API (Instant, LocalDate, LocalTime, etc.) of Java 8 with grails.

This plugin is compatible with grails 2.4.x, the first version of grails supporting the jdk8

* Provides the ability to bind inputs to Java 8 Temporal.
* Supports JSON and XML rendering of Java 8 types.

DataBinding
-----------

The plugin adds binding support for the following types:

  * Year
  * YearMonth
  * LocalTime
  * LocalDate
  * LocalDateTime
  * Instant
  * ZonedDateTime

JSON and XML
------------

The plugin registers JSON and XML converters for:

   * Year
   * YearMonth
   * LocalTime
   * LocalDate
   * LocalDateTime
   * Instant
   * ZonedDateTime

