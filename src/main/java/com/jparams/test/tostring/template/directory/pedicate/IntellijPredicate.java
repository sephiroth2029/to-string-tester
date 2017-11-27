package com.jparams.test.tostring.template.directory.pedicate;

import java.util.function.Predicate;

public class IntellijPredicate implements Predicate<String>
{
    @Override
    public boolean test(final String s)
    {
        return false;
    }
}
