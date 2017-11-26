package com.jparams.test.tostring.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringSearch
{
    private final String str;
    private final List<Match> matches;

    public StringSearch(final String str)
    {
        this.str = str;
        this.matches = new ArrayList<>();
    }

    public int search(final Pattern pattern)
    {
        final List<Match> matchesFound = new ArrayList<>();
        final Matcher matcher = pattern.matcher(str);

        while (matcher.find())
        {
            matchesFound.add(new Match(matcher.start(), matcher.end()));
        }

        matches.addAll(matchesFound);
        return matchesFound.size();
    }

    public String getMatched()
    {
        return matches.stream()
                      .sorted(Comparator.comparing(Match::getEndIndex))
                      .map(match -> str.substring(match.getStartIndex(), match.getEndIndex()))
                      .collect(Collectors.joining());
    }

    public String getUnmatched()
    {
        final Integer matchEnd = matches.stream()
                                        .map(Match::getEndIndex)
                                        .max(Integer::compareTo)
                                        .orElse(0);

        final StringBuilder stringBuilder = new StringBuilder(str).delete(matchEnd, str.length());

        matches.stream()
               .map(Match::getIndexes)
               .flatMap(List::stream)
               .sorted(Comparator.reverseOrder())
               .distinct()
               .forEach(stringBuilder::deleteCharAt);

        return stringBuilder.toString();
    }

    private static class Match
    {
        private final int startIndex;
        private final int endIndex;

        Match(final int startIndex, final int endIndex)
        {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        int getStartIndex()
        {
            return startIndex;
        }

        int getEndIndex()
        {
            return endIndex;
        }

        List<Integer> getIndexes()
        {
            return IntStream.range(startIndex, endIndex)
                            .boxed()
                            .collect(Collectors.toList());
        }
    }
}
