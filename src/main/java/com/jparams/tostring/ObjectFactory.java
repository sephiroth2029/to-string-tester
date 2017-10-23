package com.jparams.tostring;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

final class RandomObjectFactory
{
    private RandomObjectFactory()
    {
    }

    /**
     * Uses a brute force approach to create an instance of the given object with random values
     *
     * @param clazz
     * @return
     */
    static Object createRandomObject(final Class<?> clazz)
    {
        final Constructor<?> constructor = findConstructor(clazz);

        return null;
    }

    private static Constructor<?> findConstructor(final Class<?> clazz)
    {
        final Optional<Constructor<?>> constructor = Arrays.stream(clazz.getDeclaredConstructors())
                                                           .max(Comparator.comparingInt(Constructor::getParameterCount));

        if (constructor.isPresent())
        {
            constructor.get().setAccessible(true);
            return constructor.get();
        }

        throw new ObjectCreationException("Unable to construct the object to test. No constructors found");
    }

    public static class ObjectCreationException extends RuntimeException
    {
        ObjectCreationException(final String message)
        {
            super(message);
        }
    }
}
