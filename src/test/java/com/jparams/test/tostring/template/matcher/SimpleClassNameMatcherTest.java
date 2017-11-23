package com.jparams.test.tostring.template.matcher;

import java.math.BigDecimal;

import com.jparams.test.tostring.subject.Subject;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SimpleClassNameMatcherTest
{
    private SimpleClassNameMatcher sut;

    @Before
    public void setUp() throws Exception
    {
        sut = new SimpleClassNameMatcher();
    }

    @Test
    public void returnsMatch() throws Exception
    {
        final String match = sut.match("BigDecimal", new Subject(BigDecimal.class, null, null));
        assertThat(match).isEqualTo("BigDecimal");
    }

    @Test
    public void returnsExpected() throws Exception
    {
        assertThatThrownBy(() -> sut.match("String", new Subject(BigDecimal.class, null, null)))
            .hasMessage("Expected class name: BigDecimal");
    }
}