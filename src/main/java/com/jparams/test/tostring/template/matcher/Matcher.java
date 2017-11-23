package com.jparams.test.tostring.template.matcher;

import com.jparams.test.tostring.subject.Subject;

public interface Matcher
{
    String match(String snippet, Subject subject) throws MatcherException;
}
