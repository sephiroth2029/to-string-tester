package com.jparams.test.tostring.template.matcher;

import java.util.regex.Pattern;

import com.jparams.test.tostring.subject.Subject;

public class RegexMatcher implements Matcher
{
    private final String regex;
    private final Pattern pattern;

    public RegexMatcher(final String regex)
    {
        this.regex = "^(" + regex + ")";
        this.pattern = Pattern.compile(this.regex);
    }

    @Override
    public String match(final String snippet, final Subject subject) throws MatcherException
    {
        final java.util.regex.Matcher matcher = pattern.matcher(snippet);

        if (matcher.find())
        {
            return matcher.group(0);
        }

        throw new MatcherException("Expected text matching regex: " + regex);
    }
}
