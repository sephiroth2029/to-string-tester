package com.jparams.test.tostring.template.matcher;

import com.jparams.test.tostring.subject.Subject;

public class StringMatcher implements Matcher
{
    private final String matching;

    public StringMatcher(final String matching)
    {
        this.matching = matching;
    }

    @Override
    public String match(final String snippet, final Subject subject)
    {
        if (snippet.startsWith(matching))
        {
            return matching;
        }

        throw new MatcherException("Expected text matching: " + matching);
    }
}
