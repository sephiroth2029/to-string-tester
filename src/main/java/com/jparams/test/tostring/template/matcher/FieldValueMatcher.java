package com.jparams.test.tostring.template.matcher;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.jparams.test.tostring.subject.Subject;
import com.jparams.test.tostring.util.StringSearch;

public class FieldValueMatcher implements Matcher
{
    private final String entrySeparator;
    private final String keyValueSeparator;
    private final Function<String, String> fieldFormatter;
    private final Function<Object, String> valueFormatter;

    public FieldValueMatcher(final String entrySeparator,
                             final String keyValueSeparator,
                             final Function<String, String> fieldFormatter,
                             final Function<Object, String> valueFormatter)
    {
        this.entrySeparator = entrySeparator;
        this.keyValueSeparator = keyValueSeparator;
        this.fieldFormatter = fieldFormatter;
        this.valueFormatter = valueFormatter;
    }

    @Override
    public String match(final String snippet, final Subject subject)
    {
        final StringSearch stringSearch = new StringSearch(snippet);

        for (final String propertyName : subject.getPropertyNames())
        {
            for (final Expected expected : getExpected(subject, propertyName))
            {
                final int found = stringSearch.search(expected.getPattern());

                if (found == 0)
                {
                    throw new MatcherException("Expected field: " + propertyName);
                }
                else if (found != expected.getCount())
                {
                    throw new MatcherException("Expected " + expected.getCount() + " matches for field: " + propertyName + ". Found: " + found);
                }
            }
        }

        final String unmatched = stringSearch.getUnmatched();

        if (!unmatched.isEmpty())
        {
            throw new MatcherException("Unexpected values: " + unmatched);
        }

        return stringSearch.getMatched();
    }

    private List<Expected> getExpected(final Subject subject, final String property)
    {
        final String formattedProperty = fieldFormatter.apply(property);

        return subject.getProperties()
                      .getOrDefault(property, Collections.emptyList())
                      .stream()
                      .map(valueFormatter)
                      .collect(Collectors.groupingBy(Function.identity()))
                      .entrySet()
                      .stream()
                      .map(entry -> new Expected(entry.getValue().size(), createExpectedPattern(formattedProperty, entry.getKey())))
                      .collect(Collectors.toList());
    }

    private Pattern createExpectedPattern(final String property, final String value)
    {
        final String expectedValue = fieldFormatter.apply(property) + keyValueSeparator + value;
        final String regex = String.format("%s(%s)?( )?", Pattern.quote(expectedValue), Pattern.quote(entrySeparator));
        return Pattern.compile(regex);
    }

    private static class Expected
    {
        private final int count;
        private final Pattern pattern;

        public Expected(final int count, final Pattern pattern)
        {
            this.count = count;
            this.pattern = pattern;
        }

        public int getCount()
        {
            return count;
        }

        public Pattern getPattern()
        {
            return pattern;
        }
    }
}
