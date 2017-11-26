package com.jparams.test.tostring.util;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSearchTest
{
    private StringSearch subject;

    @Before
    public void setUp()
    {
        subject = new StringSearch("field1=1,field2=2,field3=3]");
    }

    private Pattern createPattern(final String field, final String value)
    {
        final String pattern = String.format("%s=%s(%s)?", field, value, ",");
        return Pattern.compile(pattern);
    }


    @Test
    public void matchesAll()
    {
        subject.search(createPattern("field1", "1"));
        subject.search(createPattern("field2", "2"));
        subject.search(createPattern("field3", "3"));

        assertThat(subject.getMatched()).isEqualTo("field1=1,field2=2,field3=3");
        assertThat(subject.getUnmatched()).isBlank();
    }
}