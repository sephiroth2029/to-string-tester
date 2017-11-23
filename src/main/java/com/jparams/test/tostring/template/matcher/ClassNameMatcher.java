package com.jparams.test.tostring.template.matcher;

import com.jparams.test.tostring.subject.Subject;

public class ClassNameMatcher implements Matcher
{
    @Override
    public String match(final String snippet, final Subject subject) throws MatcherException
    {
        final String className = subject.getType().getName();

        if (snippet.startsWith(className))
        {
            return className;
        }

        throw new MatcherException("Expected class name: " + className);
    }
}
