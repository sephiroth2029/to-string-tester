package com.jparams.test.tostring.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jparams.test.tostring.VerificationError;
import com.jparams.test.tostring.subject.Subject;
import com.jparams.test.tostring.template.matcher.Matcher;
import com.jparams.test.tostring.template.matcher.MatcherException;

public class Template
{
    private static final String ERROR_MESSAGE = "Verification failed:\n"
        + "\n"
        + "Test Subject : \"%s\"\n"
        + "Failure      : \"%s\"";

    private final List<Matcher> matchers;

    public Template(final List<Matcher> matchers)
    {
        this.matchers = matchers == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList<>(matchers));
    }

    public void verify(final Subject subject)
    {
        final String toString = subject.getInstance().toString();
        final StringBuilder snippetBuilder = new StringBuilder(toString);

        for (final Matcher matcher : matchers)
        {
            try
            {
                final String match = matcher.match(snippetBuilder.toString(), subject);
                snippetBuilder.delete(0, match.length());
            }
            catch (final MatcherException e)
            {
                throw new VerificationError(String.format(ERROR_MESSAGE, toString, e.getMessage()));
            }
        }

        if (!snippetBuilder.toString().isEmpty())
        {
            throw new VerificationError(String.format(ERROR_MESSAGE, toString, "String value of test subject does not match expected template."));
        }
    }
}
