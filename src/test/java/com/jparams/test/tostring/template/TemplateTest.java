package com.jparams.test.tostring.template;

import com.jparams.test.tostring.subject.Subject;
import com.jparams.test.tostring.template.matcher.Matcher;
import com.jparams.test.tostring.template.matcher.MatcherException;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TemplateTest
{
    private Template sut;
    private Matcher mockMatcher1;
    private Matcher mockMatcher2;

    @Before
    public void setUp() throws Exception
    {
        mockMatcher1 = mock(Matcher.class);
        mockMatcher2 = mock(Matcher.class);

        sut = TemplateBuilder.newTemplate()
                             .match(mockMatcher1)
                             .match(mockMatcher2)
                             .build();
    }

    @Test
    public void callsEachMatcherWithRemainingSnippet() throws Exception
    {
        when(mockMatcher1.match(any(), any())).thenReturn("field1=1,");
        when(mockMatcher2.match(any(), any())).thenReturn("field1=2");

        final Subject subject = new Subject(null, null, "field1=1,field2=2");
        sut.verify(subject);

        verify(mockMatcher1).match("field1=1,field2=2", subject);
        verify(mockMatcher2).match("field2=2", subject);
    }

    @Test
    public void throwsErrorIfSnippetNotEmptyAfterAllMatches() throws Exception
    {
        when(mockMatcher1.match(any(), any())).thenReturn("field1=1,");
        when(mockMatcher2.match(any(), any())).thenReturn("field1=2,");

        assertThatThrownBy(() -> sut.verify(new Subject(null, null, "field1=1,field2=2,field=3")))
            .hasMessage("Verification failed:\n"
                            + "\n"
                            + "Test Subject : \"field1=1,field2=2,field=3\"\n"
                            + "Failure      : \"String value of test subject does not match expected template.\"");
    }

    @Test
    public void throwsErrorIfMatcherThrowsError() throws Exception
    {
        when(mockMatcher1.match(any(), any())).thenThrow(new MatcherException("bob"));

        assertThatThrownBy(() -> sut.verify(new Subject(null, null, "field1=1,field2=2,field=3")))
            .hasMessage("Verification failed:\n"
                            + "\n"
                            + "Test Subject : \"field1=1,field2=2,field=3\"\n"
                            + "Failure      : \"bob\"");
    }
}