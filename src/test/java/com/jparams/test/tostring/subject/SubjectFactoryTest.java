package com.jparams.test.tostring.subject;

import java.util.Arrays;
import java.util.Collections;

import com.jparams.object.builder.ObjectBuilder;
import com.jparams.test.tostring.subject.model.Student;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubjectFactoryTest
{
    @Test
    public void testCreateSubject()
    {
        final Student student = ObjectBuilder.withDefaultConfiguration()
                                             .buildInstanceOf(Student.class)
                                             .get();

        final Subject sut = SubjectFactory.createSubject(student);

        assertThat(sut.getType()).isSameAs(Student.class);

        assertThat(sut.getInstance()).isSameAs(student);

        assertThat(sut.getProperties()).hasSize(3)
                                       .containsEntry("courses", Collections.singletonList(student.getCourses()))
                                       .containsEntry("name", Arrays.asList(student.getName1(), student.getName()))
                                       .containsEntry("address", Collections.singletonList(student.getAddress()));
    }
}