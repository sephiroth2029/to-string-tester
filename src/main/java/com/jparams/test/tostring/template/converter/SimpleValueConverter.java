package com.jparams.test.tostring.template.converter;

import java.util.Arrays;
import java.util.function.Function;

public class SimpleValueConverter implements Function<Object, String>
{
    @Override
    public String apply(final Object obj)
    {
        if (obj == null)
        {
            return "null";
        }

        if (obj.getClass().isArray())
        {
            return Arrays.toString((Object[]) obj);
        }

        return String.valueOf(obj);
    }
}
