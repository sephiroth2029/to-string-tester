package com.jparams.test.tostring.template.directory.pedicate;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class EclipsePredicate implements Predicate<String>
{
    private static final Pattern PATTERN_ECLIPSE = Pattern.compile("^(.*) \\[[\\s\\S]*]$");

    @Override
    public boolean test(final String toString)
    {
        return PATTERN_ECLIPSE.matcher(toString).matches();
    }
}
