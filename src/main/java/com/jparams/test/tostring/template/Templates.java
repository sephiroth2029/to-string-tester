package com.jparams.test.tostring.template;

import java.util.function.Function;

import com.jparams.test.tostring.template.matcher.ClassNameMatcher;
import com.jparams.test.tostring.template.matcher.FieldValueMatcher;
import com.jparams.test.tostring.template.matcher.IdentityHashMatcher;
import com.jparams.test.tostring.template.matcher.StringMatcher;

public class Templates
{
    public static Template APACHE_COMMONS_LANG_3 = TemplateBuilder.newTemplate()
                                                                  .match(new ClassNameMatcher())
                                                                  .match(new StringMatcher("@"))
                                                                  .match(new IdentityHashMatcher())
                                                                  .match(new StringMatcher("["))
                                                                  .match(new FieldValueMatcher(",", "=", Function.identity(), String::valueOf))
                                                                  .match(new StringMatcher("]"))
                                                                  .build();
}
