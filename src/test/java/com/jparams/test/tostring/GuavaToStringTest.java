package com.jparams.test.tostring;

import com.google.common.base.MoreObjects;
import com.jparams.test.tostring.template.Templates;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

public class GuavaToStringTest
{
    @Test
    public void containsAllFields() throws Exception
    {
        ToStringTester.forClass(DummyClass.class)
                      .usingTemplate(Templates.GOOGLE_GUAVA)
                      .containsAllFields()
                      .verify();
    }

    @Test(expected = VerificationError.class)
    public void containsUnexpectedFields() throws Exception
    {
        ToStringTester.forClass(DummyClass.class)
                      .usingTemplate(Templates.GOOGLE_GUAVA)
                      .containsField("a")
                      .verify();
    }

    @Test
    public void containsSpecificFields() throws Exception
    {
        ToStringTester.forClass(DummyClass.class)
                      .usingTemplate(Templates.GOOGLE_GUAVA)
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
            return MoreObjects.toStringHelper(this)
                              .add("array", array)
                              .add("b", super.b)
                              .add("a", a)
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
