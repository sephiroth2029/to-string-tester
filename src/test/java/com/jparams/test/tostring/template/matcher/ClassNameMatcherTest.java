package com.jparams.test.tostring.template.matcher;

import java.math.BigDecimal;

import com.jparams.test.tostring.subject.Subject;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class ClassNameMatcherTest
{
    private ClassNameMatcher sut;

    @Before
    public void setUp() throws Exception
    {
        sut = new ClassNameMatcher();
    }

    @Test
    public void returnsMatch() throws Exception
    {
        final String match = sut.match("java.math.BigDecimal", new Subject(BigDecimal.class, null, null));
        assertThat(match).isEqualTo("java.math.BigDecimal");
    }

    @Test
    public void failsOnMatch() throws Exception
    {
        assertThatThrownBy(() -> sut.match("String", new Subject(BigDecimal.class, null, null)))
            .hasMessage("Expected class name: java.math.BigDecimal");
    }
}