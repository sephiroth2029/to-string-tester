package com.jparams.test.tostring.template;

import com.jparams.test.tostring.template.converter.ApacheLang3ValueConverter;
import com.jparams.test.tostring.template.converter.IntelliJValueConverter;
import com.jparams.test.tostring.template.converter.SimpleValueConverter;
import com.jparams.test.tostring.template.matcher.ClassNameMatcher;
import com.jparams.test.tostring.template.matcher.FieldValueMatcher;
import com.jparams.test.tostring.template.matcher.IdentityHashMatcher;
import com.jparams.test.tostring.template.matcher.SimpleClassNameMatcher;
import com.jparams.test.tostring.template.matcher.StringMatcher;

import static java.util.function.Function.identity;

public class Templates
{
    public static Template APACHE_COMMONS_LANG_3 =
        TemplateBuilder.newTemplate()
                       .match(new ClassNameMatcher())
                       .match(new StringMatcher("@"))
                       .match(new IdentityHashMatcher())
                       .match(new StringMatcher("["))
                       .match(new FieldValueMatcher(",", "=", identity(), new ApacheLang3ValueConverter()))
                       .match(new StringMatcher("]"))
                       .build();

    public static Template GOOGLE_GUAVA =
        TemplateBuilder.newTemplate()
                       .match(new SimpleClassNameMatcher())
                       .match(new StringMatcher("{"))
                       .match(new FieldValueMatcher(", ", "=", identity(), new SimpleValueConverter()))
                       .match(new StringMatcher("}"))
                       .build();

    public static final Template INTELLI_J =
        TemplateBuilder.newTemplate()
                       .match(new SimpleClassNameMatcher())
                       .match(new StringMatcher("{"))
                       .match(new FieldValueMatcher(", ", "=", identity(), new IntelliJValueConverter()))
                       .match(new StringMatcher("}"))
                       .build();

    public static final Template ECLIPSE =
        TemplateBuilder.newTemplate()
                       .match(new SimpleClassNameMatcher())
                       .match(new StringMatcher(" "))
                       .match(new StringMatcher("["))
                       .match(new FieldValueMatcher(", ", "=", identity(), new SimpleValueConverter()))
                       .match(new StringMatcher("]"))
                       .build();

}
