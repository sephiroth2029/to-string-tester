package com.jparams.test.tostring.template.matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import com.jparams.test.tostring.subject.Subject;

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
        final StringBuilder stringBuilder = createEmptyStringBuilderOfLength(snippet.length());
        int endIndex = 0;

        for (final String propertyName : subject.getPropertyNames())
        {
            final List<Object> values = subject.getProperties().get(propertyName);

            for (final Object value : values)
            {
                final List<Match> matches = findMatches(snippet, propertyName, value);
                final long expectedMatches = values.stream().filter(o -> Objects.equals(o, value)).count();

                if (matches.isEmpty())
                {
                    throw new MatcherException("Expected field: " + propertyName);
                }
                else if (matches.size() != expectedMatches)
                {
                    throw new MatcherException(
                        "Expected " + expectedMatches + " verify for field: " + propertyName + ". Found " + matches.size());
                }
                else
                {
                    for (final Match match : matches)
                    {
                        stringBuilder.replace(match.getStartIndex(), match.getEndIndex(), match.getMatchingText());
                        endIndex = match.getEndIndex() > endIndex ? match.getEndIndex() : endIndex;
                    }
                }
            }
        }

        return stringBuilder.substring(0, endIndex);
    }

    private StringBuilder createEmptyStringBuilderOfLength(final int length)
    {
        final StringBuilder stringBuilder = new StringBuilder(length);
        IntStream.range(0, length).forEach((i) -> stringBuilder.append(" "));
        return stringBuilder;
    }

    private List<Match> findMatches(final String snippet, final String propertyName, final Object value)
    {
        final String expectedValue = fieldFormatter.apply(propertyName) + keyValueSeparator + valueFormatter.apply(value);
        final String regex = String.format("%s(%s)?", Pattern.quote(expectedValue), Pattern.quote(entrySeparator));
        final java.util.regex.Matcher matcher = Pattern.compile(regex).matcher(snippet);
        final List<Match> matches = new ArrayList<>();

        while (matcher.find())
        {
            final int startIndex = matcher.start();
            final int endIndex = matcher.end();
            final String matchingText = snippet.substring(startIndex, endIndex);
            matches.add(new Match(startIndex, endIndex, matchingText));
        }

        return matches;
    }

    private static class Match
    {
        private final int startIndex;
        private final int endIndex;
        private final String matchingText;

        Match(final int startIndex, final int endIndex, final String matchingText)
        {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.matchingText = matchingText;
        }

        public int getStartIndex()
        {
            return startIndex;
        }

        public int getEndIndex()
        {
            return endIndex;
        }

        public String getMatchingText()
        {
            return matchingText;
        }
    }
}
