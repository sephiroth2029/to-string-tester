package com.jparams.test.tostring.template.matcher;

import com.jparams.test.tostring.subject.Subject;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IdentityHashMatcherTest
{
    private IdentityHashMatcher subject;

    @Before
    public void setUp() throws Exception
    {
        subject = new IdentityHashMatcher();
    }

    @Test
    public void returnsMatch() throws Exception
    {
        final DummyClass dummy = new DummyClass();
        final String hash = Integer.toHexString(System.identityHashCode(dummy));
        final String match = subject.match(hash + " abc", new Subject(null, null, dummy));
        assertThat(match).isEqualTo(hash);
    }

    @Test
    public void returnsExpected() throws Exception
    {
        final DummyClass dummy = new DummyClass();
        final String hash = Integer.toHexString(System.identityHashCode(dummy));

        assertThatThrownBy(() -> subject.match("abc", new Subject(null, null, dummy)))
            .hasMessage("Expected hash code: " + hash);
    }

    private static class DummyClass
    {
    }
}