package com.jparams.test.tostring.template.matcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.jparams.test.tostring.subject.Subject;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FieldValueMatcherTest
{
    private FieldValueMatcher sut;

    @Before
    public void setUp() throws Exception
    {
        sut = new FieldValueMatcher(",", "=", Function.identity(), String::valueOf);
    }

    @Test
    public void matchesIgnoringOrder()
    {
        final Map<String, List<Object>> properties = new HashMap<>();
        properties.put("field1", Collections.singletonList("def"));
        properties.put("field2", Collections.singletonList("10"));

        final String match = sut.match("field2=10,field1=def]abc", new Subject(null, properties, null));

        assertThat(match).isEqualTo("field2=10,field1=def");
    }

    @Test
    public void failsOnFieldNotFound()
    {
        final Map<String, List<Object>> properties = new HashMap<>();
        properties.put("field3", Collections.singletonList("def"));

        assertThatThrownBy(() -> sut.match("field2=10,field1=def]abc", new Subject(null, properties, null)))
            .hasMessage("Expected field: field3");
    }

    @Test
    public void handlesDuplicates()
    {
        final Map<String, List<Object>> properties = new HashMap<>();
        properties.put("field1", Arrays.asList("def", "def"));

        final String match = sut.match("field1=def,field1=def]abc", new Subject(null, properties, null));

        assertThat(match).isEqualTo("field1=def,field1=def");
    }

    @Test
    public void failsOnUnexpectedDuplicate()
    {
        final Map<String, List<Object>> properties = new HashMap<>();
        properties.put("field1", Collections.singletonList("def"));

        assertThatThrownBy(() -> sut.match("field1=def,field1=def]abc", new Subject(null, properties, null)))
            .hasMessage("Expected 1 matches for field: field1. Found: 2");
    }

    @Test
    public void failsOnUnexpectedValue()
    {
        final Map<String, List<Object>> properties = new HashMap<>();
        properties.put("field1", Collections.singletonList("def"));

        assertThatThrownBy(() -> sut.match("field2=abc,field1=def]abc", new Subject(null, properties, null)))
            .hasMessage("Unexpected values: field2=abc,");
    }
}