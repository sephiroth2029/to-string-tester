package com.jparams.test.tostring;

import com.jparams.test.tostring.template.Templates;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

public class ApacheCommonsLang3Test
{
    @Test
    public void containsAllFields() throws Exception
    {
        ToStringTester.forClass(DummyClass.class)
                      .usingTemplate(Templates.APACHE_COMMONS_LANG_3)
                      .containsAllFields()
                      .verify();
    }

    @Test(expected = VerificationError.class)
    public void containsUnexpectedFields() throws Exception
    {
        ToStringTester.forClass(DummyClass.class)
                      .usingTemplate(Templates.APACHE_COMMONS_LANG_3)
                      .containsField("a")
                      .verify();
    }

    @Test
    public void containsSpecificFields() throws Exception
    {
        ToStringTester.forClass(DummyClass.class)
                      .usingTemplate(Templates.APACHE_COMMONS_LANG_3)
                      .containsField("array")
                      .containsField("a")
                      .containsField("b")
                      .verify();
    }

    public static class DummyClass extends DummyParent
    {
        private final String[] array = {"a", "b" };
        private final String a = "a";

        @Override
        public String toString()
        {
            return new ToStringBuilder(this)
                .append("array", array)
                .append("a", a)
                .appendSuper(super.toString())
                .toString();
        }
    }

    public static class DummyParent
    {
        private final String b = "b";

        @Override
        public String toString()
        {
            return new ToStringBuilder(this)
                .append("b", b)
                .toString();
        }
    }
}
