package com.jparams.test.tostring.template.matcher;

import java.math.BigDecimal;

import com.jparams.test.tostring.subject.Subject;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SimpleClassNameMatcherTest
{
    private SimpleClassNameMatcher subject;

    @Before
    public void setUp() throws Exception
    {
        subject = new SimpleClassNameMatcher();
    }

    @Test
    public void returnsMatch() throws Exception
    {
        final String match = subject.match("BigDecimal", new Subject(BigDecimal.class, null, null));
        assertThat(match).isEqualTo("BigDecimal");
    }

    @Test
    public void returnsExpected() throws Exception
    {
        assertThatThrownBy(() -> subject.match("String", new Subject(BigDecimal.class, null, null)))
            .hasMessage("Expected class name: BigDecimal");
    }
}