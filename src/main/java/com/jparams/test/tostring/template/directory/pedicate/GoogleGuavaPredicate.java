package com.jparams.test.tostring.template.directory.pedicate;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class GoogleGuavaPredicate implements Predicate<String>
{
    private static final Pattern PATTERN_GOOGLE_GUAVA = Pattern.compile("^(.*)\\{[\\s\\S]*}$");

    @Override
    public boolean test(final String toString)
    {
        return PATTERN_GOOGLE_GUAVA.matcher(toString).matches();
    }
}
