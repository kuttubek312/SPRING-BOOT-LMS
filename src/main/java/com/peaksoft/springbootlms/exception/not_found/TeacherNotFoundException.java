package com.peaksoft.springbootlms.exception.not_found;

import java.text.MessageFormat;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(Long courseId) {
        super(MessageFormat.format("Could not find teacher with id: {id}", courseId));
    }
}
