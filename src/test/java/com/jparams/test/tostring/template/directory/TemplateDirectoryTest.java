package com.jparams.test.tostring.template.directory;

import com.jparams.test.tostring.template.Template;
import com.jparams.test.tostring.template.Templates;
import com.jparams.test.tostring.template.directory.TemplateDirectory.TemplateMatchException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateDirectoryTest
{
    @Test
    public void discoverApacheTemplate()
    {
        final Template template = TemplateDirectory.findTemplate("com.some.Class@3b2cf7ab[actionCode=a, actionCode=b]");

        assertThat(template).isSameAs(Templates.APACHE_COMMONS_LANG_3);
    }

    @Test
    public void discoverGuavaTemplate()
    {
        assertThat(TemplateDirectory.findTemplate("DummyClass{b=b, a=a}")).isSameAs(Templates.GOOGLE_GUAVA);
        assertThat(TemplateDirectory.findTemplate("DummyClass{}")).isSameAs(Templates.GOOGLE_GUAVA);
    }

    @Test
    public void discoverIntellijTemplate()
    {
        final Template template = TemplateDirectory.findTemplate("DummyClass{b='b', a='a'}");

        assertThat(template).isSameAs(Templates.INTELLI_J);
    }


    @Test
    public void discoverEclipseTemplate()
    {
        final Template template = TemplateDirectory.findTemplate("Domain [name=abc, something=def]");

        assertThat(template).isSameAs(Templates.ECLIPSE);
    }

    @Test(expected = TemplateMatchException.class)
    public void throwsTemplateMatchExceptionOnUnknownException()
    {
        TemplateDirectory.findTemplate("abc");
    }
}