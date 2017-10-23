package com.jparams.tostring;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class InstanceCreator
{
    /**
     * Create an instance of this object using any values
     *
     * @param clazz class of object to create
     * @return object
     */
    public static Object createInstance(final Class<?> clazz)
    {
        final Constructor<?> constructor = findConstructor(clazz);
        final Object[] parameters = ParametersFactory.createParameters(constructor.getParameterTypes());

        try
        {
            return constructor.newInstance(parameters);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new InstanceCreationException("Unable to construct the object to test", e);
        }
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

        throw new InstanceCreationException("Unable to construct the object to test. No constructors found");
    }

    public static class InstanceCreationException extends RuntimeException
    {
        InstanceCreationException(final String message)
        {
            super(message);
        }

        InstanceCreationException(final String message, final Exception cause)
        {
            super(message, cause);
        }
    }
}
