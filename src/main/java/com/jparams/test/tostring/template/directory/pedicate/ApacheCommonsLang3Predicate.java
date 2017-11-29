package com.jparams.test.tostring.template.directory.pedicate;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ApacheCommonsLang3Predicate implements Predicate<String>
{
    private static final Pattern PATTERN_APACHE_COMMONS_LANG_3 = Pattern.compile("^(.*)@(.*)\\[[\\s\\S]*]$");

    @Override
    public boolean test(final String toString)
    {
        return PATTERN_APACHE_COMMONS_LANG_3.matcher(toString).matches();
    }
}
