package com.jparams.test.tostring.template.matcher;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringMatcherTest
{
    private StringMatcher subject;

    @Before
    public void setUp() throws Exception
    {
        subject = new StringMatcher("abc");
    }

    @Test
    public void returnsMatch() throws Exception
    {
        final String match = subject.match("abcd", null);
        assertThat(match).isEqualTo("abc");
    }

    @Test
    public void returnsExpected() throws Exception
    {
        assertThatThrownBy(() -> subject.match("aabcd", null)).hasMessage("Expected text matching: abc");
    }
}