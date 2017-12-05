package com.jparams.test.tostring.template.converter;


import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ApacheLang3ValueConverter implements Function<Object, String>
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
            return Arrays.stream((Object[]) obj)
                         .map(this)
                         .collect(Collectors.joining(",", "{", "}"));
        }

        return String.valueOf(obj);
    }
}
