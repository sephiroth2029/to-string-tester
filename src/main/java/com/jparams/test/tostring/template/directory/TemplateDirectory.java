package com.jparams.test.tostring.template.directory;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import com.jparams.test.tostring.template.Template;
import com.jparams.test.tostring.template.Templates;
import com.jparams.test.tostring.template.directory.pedicate.ApacheCommonsLang3Predicate;
import com.jparams.test.tostring.template.directory.pedicate.EclipsePredicate;
import com.jparams.test.tostring.template.directory.pedicate.GoogleGuavaPredicate;
import com.jparams.test.tostring.template.directory.pedicate.IntellijPredicate;

public final class TemplateDirectory
{
    static
    {
        TEMPLATES = Collections.synchronizedMap(new LinkedHashMap<>());
        register(new ApacheCommonsLang3Predicate(), Templates.APACHE_COMMONS_LANG_3);
        register(new EclipsePredicate(), Templates.ECLIPSE);
        register(new IntellijPredicate(), Templates.INTELLI_J);
        register(new GoogleGuavaPredicate(), Templates.GOOGLE_GUAVA);
    }

    private static final Map<Predicate<String>, Template> TEMPLATES;

    private TemplateDirectory()
    {
    }

    public static void register(final Predicate<String> predicate, final Template template)
    {
        TEMPLATES.put(predicate, template);
    }

    public static Template findTemplate(final String toString) throws TemplateMatchException
    {
        for (final Entry<Predicate<String>, Template> entry : TEMPLATES.entrySet())
        {
            if (entry.getKey().test(toString))
            {
                return entry.getValue();
            }
        }

        throw new TemplateMatchException("Failed to findTemplate template of toString(). Explicitly define a template.");
    }

    public static class TemplateMatchException extends RuntimeException
    {
        TemplateMatchException(final String message)
        {
            super(message);
        }
    }
}
