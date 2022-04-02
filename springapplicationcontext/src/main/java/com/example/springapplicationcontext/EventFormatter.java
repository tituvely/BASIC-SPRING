package com.example.springapplicationcontext;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class EventFormatter implements Formatter<Event> {

//    @Autowired
//    MessageSource messageSource;

    @Override
    public Event parse(String text, Locale locale) throws ParseException {
        return new Event(Integer.parseInt(text));
    }

    @Override
    public String print(Event object, Locale locale) {
//        messageSource.getMessage()

        return object.getId().toString();
    }
}
