package com.peaksoft.springbootlms.exception.not_found;

import java.text.MessageFormat;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long id) {
        super(MessageFormat.format("Cloud not find student with id:{id}", id));

    }
}
