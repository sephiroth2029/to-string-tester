package com.jparams.test.tostring.subject.model;

import java.util.List;

public class Student extends Person
{
    private final String name;
    private final List<Course> courses;

    public Student(final String name, final Address address, final String name1, final List<Course> courses)
    {
        super(name, address);
        this.name = name1;
        this.courses = courses;
    }

    public String getName1()
    {
        return name;
    }

    public List<Course> getCourses()
    {
        return courses;
    }
}
