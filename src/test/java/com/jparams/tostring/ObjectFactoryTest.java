package com.jparams.tostring;

import org.junit.Test;

public class RandomObjectFactoryTest
{
    @Test
    public void name()
    {
        RandomObjectFactory.createObject(MultipleConstructorsClass.class);
    }

    public static class MultipleConstructorsClass
    {

        protected MultipleConstructorsClass(final String str, final Integer val)
        {
            // protected constructor
        }

        private MultipleConstructorsClass(final String str, final Integer val, final boolean truth)
        {
            // default constructor
        }

        public MultipleConstructorsClass()
        {
            // default constructor
        }
    }
}