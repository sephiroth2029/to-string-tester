package com.jparams.test.tostring.template.directory.pedicate;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class IntellijPredicate implements Predicate<String>
{
    private static final Pattern PATTERN_INTELLIJ = Pattern.compile("^(.*)\\{(\\w)='[\\s\\S]*'}$");

    @Override
    public boolean test(final String toString)
    {
        return PATTERN_INTELLIJ.matcher(toString).matches();
    }
}
