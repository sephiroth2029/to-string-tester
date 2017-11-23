package com.jparams.test.tostring.template.matcher;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WildcardMatcherTest
{
    private WildcardMatcher subject;

    @Before
    public void setUp() throws Exception
    {
        subject = new WildcardMatcher(3);
    }

    @Test
    public void matchesFirst3Characters() throws Exception
    {
        final String match = subject.match("abcde", null);
        assertThat(match).isEqualTo("abc");
    }

    @Test
    public void matchesRemainingCharacters() throws Exception
    {
        final String match = subject.match("ab", null);
        assertThat(match).isEqualTo("ab");
    }
}