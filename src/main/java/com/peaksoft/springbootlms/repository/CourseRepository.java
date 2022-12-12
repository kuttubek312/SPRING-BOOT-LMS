package com.peaksoft.springbootlms.repository;

import com.peaksoft.springbootlms.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {


    @Query("select c from Course c where upper(c.courseName) like concat('%',:text,'%')" +
            "or upper(c.duration) like concat('%',:text,'%') ")
    List<Course> searchAndPagination(@Param("text") String text, Pageable pageable);
}
