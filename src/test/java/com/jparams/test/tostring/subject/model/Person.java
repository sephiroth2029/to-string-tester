package com.jparams.test.tostring.subject.model;

public abstract class Person
{
    private final String name;
    private final Address address;

    public Person(final String name, final Address address)
    {
        this.name = name;
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public Address getAddress()
    {
        return address;
    }
}
