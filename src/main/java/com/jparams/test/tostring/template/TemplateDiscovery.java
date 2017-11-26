package com.jparams.test.tostring.template;

import java.util.regex.Pattern;

public final class TemplateDiscovery
{
    private static final Pattern APACHE_COMMONS_LANG_3 = Pattern.compile("^(.*)@[0-9A-Za-z]{8}\\[[\\s\\S]*]$");

    private TemplateDiscovery()
    {
    }

    public static Template discover(final String toString) throws TemplateMatchException
    {
        if (APACHE_COMMONS_LANG_3.matcher(toString).matches())
        {
            return Templates.APACHE_COMMONS_LANG_3;
        }

        throw new TemplateMatchException("Failed to discover template of toString(). Explicitly define a template to continue.");
    }

    public static class TemplateMatchException extends RuntimeException
    {
        TemplateMatchException(final String message)
        {
            super(message);
        }
    }
}
