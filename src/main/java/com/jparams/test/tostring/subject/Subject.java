package com.jparams.test.tostring.subject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Subject
{
    private final Class<?> type;
    private final Map<String, List<Object>> properties;
    private final Object instance;

    public Subject(final Class<?> type, final Map<String, List<Object>> properties, final Object instance)
    {
        this.type = type;
        this.properties = properties == null ? Collections.emptyMap() : Collections.unmodifiableMap(new HashMap<>(properties));
        this.instance = instance;
    }

    public Class<?> getType()
    {
        return type;
    }

    public Map<String, List<Object>> getProperties()
    {
        return Collections.unmodifiableMap(properties);
    }

    public Set<String> getPropertyNames()
    {
        return properties.keySet();
    }

    public Object getInstance()
    {
        return instance;
    }
}
