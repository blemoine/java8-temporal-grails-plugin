Java8 Temporal Grails Plugin
============================
The Java 8 temporal Plugin integrates the new DateTime API (Instant, LocalDate, LocalTime, etc.) of Java 8 with grails.

This plugin is compatible with grails 2.4.x, the first version of grails supporting the jdk8

* Provides the ability to bind inputs to Java 8 Temporal.
* Supports JSON and XML rendering of Java 8 types.

How to install
--------------

Add to your BuildConfig.groovy in the _plugins_ section

```
    compile ":java8-temporal:0.2"
```


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


Persistence
-----------

The compatibility of Gorm with Java8 Temporals is assured by the [Jadira User Type project](http://jadira.sourceforge.net/usertype.extended/index.html).

h3. Adding the type in your domain classes

You can specify in your domain classes how to persist the temporal classes in the mapping block.

eg.


{code}
import java.time.*
import org.jadira.usertype.dateandtime.threeten.*

class Person {
    String name
    LocalDate birthdate
    static mapping = {
    	birthdate type: PersistentLocalDate
    }
}
{code}

h3. Adding default type mapping

To avoid writing manually the mapping for every single domain classes, you could declare default mapping for temporal classes
in your @Config.groovy@ file.

eg.


{code}
grails.gorm.default.mapping = {
    "user-type" type: org.jadira.usertype.dateandtime.threeten.PersistentLocalDate, class: java.time.LocalDate
    "user-type" type: org.jadira.usertype.dateandtime.threeten.PersistentLocalTime, class: java.time.LocalTime
    "user-type" type: org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime, class: java.time.LocalDateTime
    "user-type" type: org.jadira.usertype.dateandtime.threeten.PersistentInstantAsTimestamp, class: java.time.Instant
    "user-type" type: org.jadira.usertype.dateandtime.threeten.PersistentYearAsInteger, class: java.time.Year
    "user-type" type: org.jadira.usertype.dateandtime.threeten.PersistentYearMonthAsString, class: java.time.YearMonth

    // ...
}
{code}


h2. Acknowledgement

Thanks to [Grails Joda-Time plugin Team](https://github.com/gpc/grails-joda-time/), which is the main inspiration source
for this plugin.

