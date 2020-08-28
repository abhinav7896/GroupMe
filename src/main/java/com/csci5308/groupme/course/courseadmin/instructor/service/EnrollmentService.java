package com.csci5308.groupme.course.courseadmin.instructor.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.Reader;
import java.util.List;

public interface EnrollmentService {

    public boolean upload(MultipartFile file, String instructorID, String courseCode);

    public List<String[]> readAll(Reader reader);
}
