# To String Tester

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.jparams/to-string-tester/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.jparams/to-string-tester)
 [![Build Status](https://travis-ci.org/jparams/to-string-tester.svg?branch=master)](https://travis-ci.org/jparams/to-string-tester)

## Getting Started

### Get Object Builder

Maven:
```
<dependency>
    <groupId>com.jparams</groupId>
    <artifactId>to-string-tester</artifactId>
    <version>1.x.x</version>
</dependency>
```

Gradle:
```
compile 'com.jparams:to-string-tester:1.x.x'
```

### What is To String Tester?
To String Tester is an easy an convenient way to test your toString() implementation. Never again worry about forgetting to update the toString() 
method, just pop in a quick test and let To String Tester remind you!


### Writing a Test
Writing a test is easy! Here goes:

```java
@Test
public void myTest()
{
    ToStringTester.forClass(Student.class)
                  .containsAllFields()
                  .verify();
}
```

Wait! What just happened? To String Tester is very clever, when given a class to test, it uses the amazing [Object Builder](https://github.com/jparams/object-builder) library to create an instance of the class instantiated with random values.

To String Tester even knows the template of the toString() output, or at least it tries to discover it using pattern matching. Out of the box the 
library understands the 
templates:
- Eclipse Template
- IntelliJ Template
- Apache Commons Lang 3 Template
- Google Guava Template

### Configure
If you want more options, To String tester is quite configurable. Let run through some configurations.

#### Specific Fields
In our very simple example, we wanted to verify that the toString() contained all fields. If we want to be more specific, we can define a list of 
fields to verify. Here is an example:

```java
@Test
public void myTest()
{
    ToStringTester.forClass(Student.class)
                  .containsField("firstName")
                  .containsField("lastName")
                  .verify();
}
```

#### Templates
To String Tester supports the following templates out of the box.

| Template                       | Example                                                       |
|--------------------------------|---------------------------------------------------------------|
| Eclipse Template               | Student \[firstName=J, lastName=Params]                         |
| IntelliJ Template              | Student{firstName='J', lastName='Params'}                      |
| Apache Commons Lang 3 Template | com.jparams.test.Student@3701eaf6\[firstName=J,lastName=Params] |
| Google Guava Template          | Student{firstName=J, lastName=Params}                          |

What about me? I am too cool for these templates. Do you support \<insert obscure name here> Template? No, but you can create your own! Even 
better, you can create a template and submit a pull request to this library.

Here is how:

```
final Template myNewTemplate = TemplateBuilder.newTemplate()
                                              .match(new SimpleClassNameMatcher())
                                              .match(new StringMatcher(" "))
                                              .match(new StringMatcher("["))
                                              .match(new FieldValueMatcher(", ", "=", identity(), String::valueOf))
                                              .match(new StringMatcher("]"))
                                              .build();

ToStringTester.forClass(Student.class)
              .usingTemplate(myNewTemplate)
              .containsAllFields()
              .verify();
                                              
```

## Compatibility
This library is compatible with Java 8 and above.

## License
[MIT License](http://www.opensource.org/licenses/mit-license.php)

Copyright 2017 JParams

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
