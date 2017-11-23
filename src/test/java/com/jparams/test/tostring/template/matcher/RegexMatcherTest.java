package com.jparams.test.tostring.template.matcher;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RegexMatcherTest
{
    private RegexMatcher subject;

    @Before
    public void setUp() throws Exception
    {
        subject = new RegexMatcher("[a-z]{3}");
    }

    @Test
    public void returnsMatch() throws Exception
    {
        final String match = subject.match("abcde", null);
        assertThat(match).isEqualTo("abc");
    }

    @Test
    public void returnsMatchTillEnd() throws Exception
    {
        final String match = new RegexMatcher(".*$").match("abcde123", null);
        assertThat(match).isEqualTo("abcde123");
    }

    @Test
    public void failsOnMatch() throws Exception
    {
        assertThatThrownBy(() -> subject.match("a123cdefg", null))
            .hasMessage("Expected text matching regex: ^([a-z]{3})");
    }
}