package com.jparams.test.tostring.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jparams.test.tostring.template.matcher.Matcher;

public class Template
{
    private final List<Matcher> matchers;

    public Template(final List<Matcher> matchers)
    {
        this.matchers = matchers == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList<>(matchers));
    }


}
