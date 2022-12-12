package com.peaksoft.springbootlms.repository;

import com.peaksoft.springbootlms.model.Teacher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
//
//    @Query("select t from Teacher t")
//    List<Teacher> getByPagination(Pageable pageRequest);

    @Query("select t from Teacher t where upper(t.firstName) like concat('%',:text,'%')" +
            "or upper(t.lastName) like concat('%',:text,'%')" +
            "or upper(t.email) like concat('%',:text,'%') ")
    List<Teacher> searchAndPagination(@Param("text") String text, Pageable pageable);
}
