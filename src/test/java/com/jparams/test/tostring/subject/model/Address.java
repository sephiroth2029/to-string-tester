package com.jparams.test.tostring.subject.model;

public class Address
{
    private final String line1;
    private final String line2;
    private final String city;
    private final String country;
    private final String postCode;

    public Address(final String line1, final String line2, final String city, final String country, final String postCode)
    {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
    }

    public String getLine1()
    {
        return line1;
    }

    public String getLine2()
    {
        return line2;
    }

    public String getCity()
    {
        return city;
    }

    public String getCountry()
    {
        return country;
    }

    public String getPostCode()
    {
        return postCode;
    }
}
