package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(rollbackOn = true)
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s from Student s where s.email= ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("Select s from Student s where s.firstName=?1 and s.age>=?2")
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqual(String firstName, Integer age);

    @Query(value = "Select * from student where first_name = :firstName and age>=:age ",nativeQuery = true)
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
            @Param("firstName") String firstName, @Param("age") Integer age);

    @Transactional
    @Modifying
    @Query("Delete from Student u where u.id= ?1")
    void deleteStudentById(Long id);
}
