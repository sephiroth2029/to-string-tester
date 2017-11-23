package com.jparams.test.tostring.template;

import java.util.ArrayList;
import java.util.List;

import com.jparams.test.tostring.template.matcher.Matcher;

public class TemplateBuilder
{
    private final List<Matcher> matchers;

    public TemplateBuilder()
    {
        this.matchers = new ArrayList<>();
    }

    public TemplateBuilder match(final Matcher matcher)
    {
        this.matchers.add(matcher);
        return this;
    }

    public Template build()
    {
        return new Template(matchers);
    }

    public static TemplateBuilder newTemplate()
    {
        return new TemplateBuilder();
    }
}
