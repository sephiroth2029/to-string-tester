package com.jparams.test.tostring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jparams.object.builder.Build;
import com.jparams.object.builder.ObjectBuilder;
import com.jparams.test.tostring.subject.Subject;
import com.jparams.test.tostring.subject.SubjectFactory;
import com.jparams.test.tostring.template.Template;

public final class ToStringTester
{
    private final Subject subject;
    private final Map<String, List<Object>> properties;
    private Template template;

    private ToStringTester(final Object instance)
    {
        this.subject = SubjectFactory.createSubject(instance);
        this.properties = new HashMap<>();
    }

    /**
     * Specify a template for the toString(). A number of pre-made templates are available out of the box, see {@link
     * com.jparams.test.tostring.template.Templates}. You can also create your own custom template using {@link
     * com.jparams.test.tostring.template.TemplateBuilder}.
     *
     * If a template is not explicitly provided, an attempt will be made to discover the template.
     *
     * @param template
     * @return this
     */
    public ToStringTester usingTemplate(final Template template)
    {
        this.template = template;
        return this;
    }

    /**
     * Contains the given field. This method can be called multiple times.
     *
     * @param fieldName
     * @return this
     */
    public ToStringTester containsFields(final String fieldName)
    {
        if (!subject.getProperties().containsKey(fieldName))
        {
            throw new IllegalArgumentException("No field with name [" + fieldName + "] found on test subject");
        }

        properties.put(fieldName, subject.getProperties().get(fieldName));
        return this;
    }

    /**
     * Contains all subject defined on the class.
     *
     * @return
     */
    public ToStringTester containsAllFields()
    {
        properties.clear();
        properties.putAll(subject.getProperties());
        return this;
    }

    public void verify() throws VerificationError
    {
        final Subject subjectToVerify = new Subject(subject.getType(), properties, subject.getInstance());
    }

    public static ToStringTester forClass(final Class<?> clazz)
    {
        final Build<?> build = ObjectBuilder.withDefaultConfiguration().buildInstanceOf(clazz);
        return forInstance(build.get());
    }

    public static ToStringTester forInstance(final Object instance)
    {
        return new ToStringTester(instance);
    }
}
