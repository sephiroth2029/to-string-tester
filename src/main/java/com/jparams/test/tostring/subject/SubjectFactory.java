package com.jparams.test.tostring.subject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SubjectFactory
{
    private SubjectFactory()
    {
    }

    public static Subject createSubject(final Object instance)
    {
        final Map<String, List<Object>> properties = new HashMap<>();
        Class<?> clazz = instance.getClass();

        while (clazz != null)
        {
            for (final Field field : clazz.getDeclaredFields())
            {
                field.setAccessible(true);

                try
                {
                    final Object value = field.get(instance);
                    properties.putIfAbsent(field.getName(), new ArrayList<>());
                    properties.get(field.getName()).add(value);
                }
                catch (final IllegalAccessException e)
                {
                    throw new FieldLookupException("Error occurred looking up fields", e);
                }
            }

            clazz = clazz.getSuperclass();
        }

        return new Subject(instance.getClass(), properties, instance);
    }

    public static class FieldLookupException extends RuntimeException
    {
        FieldLookupException(final String message, final Throwable cause)
        {
            super(message, cause);
        }
    }
}
