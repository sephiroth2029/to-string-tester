package com.jparams.test.tostring.template.matcher;

import com.jparams.test.tostring.subject.Subject;

public class WildcardMatcher implements Matcher
{
    private final int length;

    public WildcardMatcher(final int length)
    {
        this.length = length;
    }

    @Override
    public String match(final String snippet, final Subject subject)
    {
        final int endIndex = snippet.length() < length ? snippet.length() : length;
        return snippet.substring(0, endIndex);
    }
}
