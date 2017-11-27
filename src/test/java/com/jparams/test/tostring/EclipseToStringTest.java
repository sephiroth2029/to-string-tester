package com.jparams.test.tostring;

import com.jparams.test.tostring.template.Templates;

import org.junit.Test;

public class EclipseToStringTest
{
    @Test
    public void containsAllFields() throws Exception
    {
        ToStringTester.forClass(DummyClass.class)
                      .usingTemplate(Templates.ECLIPSE)
                      .containsAllFields()
                      .verify();
    }

    @Test(expected = VerificationError.class)
    public void containsUnexpectedFields() throws Exception
    {
        ToStringTester.forClass(DummyClass.class)
                      .usingTemplate(Templates.ECLIPSE)
                      .containsField("a")
                      .verify();
    }

    @Test
    public void containsSpecificFields() throws Exception
    {
        ToStringTester.forClass(DummyClass.class)
                      .usingTemplate(Templates.ECLIPSE)
                      .containsField("a")
                      .containsField("b")
                      .verify();
    }

    public static class DummyClass
    {
        private final String a = "a";
        private final String b = "b";

        @Override
        public String toString()
        {
            return "DummyClass [a=" + a + ", b=" + b + "]";
        }
    }
}
