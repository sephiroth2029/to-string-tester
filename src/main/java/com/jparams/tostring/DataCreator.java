package com.jparams.tostring;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class ParametersFactory
{
    private static final Random RANDOM = new Random();

    public static Object[] createParameters(final Class<?>[] parameterTypes)
    {
        final Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++)
        {
            parameters[i] = createRandomData(parameterTypes[i]);
        }

        return parameters;
    }

    private static Object createRandomData(final Class<?> clazz)
    {
        if (clazz.isAssignableFrom(String.class) || clazz.isAssignableFrom(Object.class))
        {
            return UUID.randomUUID().toString();
        }

        if (clazz.isAssignableFrom(long.class) || clazz.isAssignableFrom(Long.class))
        {
            return RANDOM.nextLong();
        }

        if (clazz.isAssignableFrom(boolean.class) || clazz.isAssignableFrom(Boolean.class))
        {
            return RANDOM.nextBoolean();
        }

        if (clazz.isAssignableFrom(int.class) || clazz.isAssignableFrom(Integer.class))
        {
            return RANDOM.nextInt();
        }

        if (clazz.isAssignableFrom(double.class) || clazz.isAssignableFrom(Double.class))
        {
            return RANDOM.nextDouble();
        }

        if (clazz.isAssignableFrom(float.class) || clazz.isAssignableFrom(Float.class))
        {
            return RANDOM.nextFloat();
        }

        if (clazz.isAssignableFrom(BigDecimal.class))
        {
            return BigDecimal.valueOf(RANDOM.nextDouble());
        }

        if (clazz.isAssignableFrom(LocalDateTime.class))
        {
            return LocalDateTime.now();
        }

        return null;
    }
}
