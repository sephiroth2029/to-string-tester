package com.jparams.test.tostring.template;

public class TemplateDiscovery
{
    public Template discover(final String toString) throws TemplateMatchException
    {
        return null;
    }

    public static class TemplateMatchException extends RuntimeException
    {
        public TemplateMatchException(final String message)
        {
            super(message);
        }
    }
}
