package com.jparams.test.tostring.template.matcher;

import com.jparams.test.tostring.subject.Subject;

public class IdentityHashMatcher implements Matcher
{
    @Override
    public String match(final String snippet, final Subject subject) throws MatcherException
    {
        final String identityHash = Integer.toHexString(System.identityHashCode(subject.getInstance()));

        if (snippet.startsWith(identityHash))
        {
            return identityHash;
        }

        throw new MatcherException("Expected hash code: " + identityHash);
    }
}
