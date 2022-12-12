package com.peaksoft.springbootlms.exception.not_found;

import java.text.MessageFormat;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException() {
    }

    public CourseNotFoundException(Long id) {
        super(MessageFormat.format("Could not find course with id : {id} ", id));

    }
}
