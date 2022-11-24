- [English](README.md)
- [Chinese](README-cn.md)

## Introduction

This tool(wink-forge) start with a simple idea: My Boss always says that "I will show your project to customers, but its
UI seem look like with error!"
Because when I was testing the projects, I always input "balabala" to the forms, there must be lots of nonsense text on
pages. In this project I want to create a tools to deal with this problem.

This library want to be used as simple as possible and does not depend on anything other things than JDK8. At present,
the code has seldom exception protections and cannot be expected to work correct in arbitrary calls.

## Install

```xml

<dependency>
    <groupId>cn.foofun</groupId>
    <artifactId>wink-forge-core</artifactId>
    <version>0.0.2</version>
</dependency>
```

## Concept

```java
public interface Source<T> {
    T next();
}
```

`Source` class is core interface. Its `next` method generates data one by one. So we can write a class in project like
this(see details in source code):

```java
public class MobileSource implements Source<String> {
    // ...
    @Override
    public String next() {
        // ...
        // last random numbers
        for (int i = 0; i < 8; i++) {
            temp = random.nextInt(10);
            sb.append(temp);
        }
        return sb.toString();
    }

    // use it 

    public static main() {
        MobileSource mobileSource = new MobileSource();
        // r1、r2 is mobile numbers
        String r1 = mobileSource.next();
        String r2 = mobileSource.next();
    }
}
```

`MobileSource` is a tool that generates Chinese mobile phone number.

## Sources

### Forge

`Forge` is a factory helper with many static methods start with "build*". It can create kinds of `Source`:

```java
class App {
    public static main() {
        // provide different numbers between 8-80
        Source<Integer> source = Forge.buildInt(8, 80);
        // provide lines in resource file
        Source<String> source = Forge.buildFromClassPath("/forge/岗位名称.txt");
    }
}
```

### IntSource

There is another important source - `IntSource`. `IntSource` can generate random number from `min` to `max`, and the
numbers are not same.

```java
public class ForgeTest {
    @Test
    void buildInt() {

        Source<Integer> source = Forge.buildInt(8, 80);

        for (int i = 0; i < 100; i++) {
            int next = source.next();
            System.out.println(next);
        }
    }
}
```

### ValueListSource

You can let `ValueListSource` contains any values, later call its `next` method will pop these value randomized.

```java
public class Forge {
    // create
    public static <T> Source<T> buildInValues(T... values) {
        ValueListSource<T> valueListSource = new ValueListSource<>(Arrays.asList(values));
        return valueListSource;
    }

    // use
    @Test
    void buildInValues() {

        Source<String> source = Forge.buildInValues("A", "B", "C", "D", "E");

        for (int i = 0; i < 100; i++) {
            String next = source.next();
            System.out.println(next);
        }
    }
}
```

### LinesStringSource & FileLinesStringSource

These two classes read multiline text, then `next` method pop line randomized. In fact, they are implemented
by `IntSource`.

```java

public class Forge {
    // create
    public static Source<String> buildFromClassPath(String fileName) throws IOException {

        InputStream inputStream = Forge.class.getResourceAsStream(fileName);

        Source<String> source = new FileLinesStringSource(inputStream, Charset.defaultCharset());

        inputStream.close();

        return source;
    }

    // use 
    @Test
    void buildFromClassPath() throws IOException {

        Source<String> source = Forge.buildFromClassPath("/forge/岗位名称.txt");

        for (int i = 0; i < 100; i++) {
            String next = source.next();
            System.out.println(next);
        }
    }
}
```

## Advanced

Some simple sources were introduced earlier in this article. You can read the test source codes to know more. From here
we will do some complex tips.

### Combine two sources

The following code combines two sources, and takes the first source with a 1/3 chance, the second source with 2/3 chance
each time:

```java
public class ForgeTest {
    @Test
    void combineSources() throws IOException {

        Source<String> source = Forge.combineSources(Forge.buildFromClassPath("/forge/man-names.txt"), Forge.buildFromClassPath("/forge/female-names.txt"), 2, 1);

        for (int i = 0; i < 100; i++) {
            String next = source.next();
            System.out.println(next);
        }
    }
}
```

### Composite Source

Combines raw data types sources into composite data:

```java
public class ForgeTest {
    // co
    @Test
    void beanSource() throws IOException {

        Source<String> nameSource = Forge.combineSources(Forge.buildFromClassPath("/forge/男名.txt"), Forge.buildFromClassPath("/forge/女名.txt"), 2, 1);

        BeanSource<User> source = Forge.buildMap("age", Forge.buildInt(20, 50))
                .add("name", nameSource).toBeanSource(User.class);

        for (int i = 0; i < 100; i++) {
            User user = source.next();
            System.out.println(user);
        }
    }

    @Test
    void beanSource2() throws IOException {

        Source<String> source = Forge.buildFromClassPath("/forge/双列测试.txt");

        MappingSource<User, String> mappingSource = new MappingSource<>(source, (str) -> {
            String[] strs = str.split("\\t");
            User user = new User();
            user.setName(strs[0]);
            user.setAge(Integer.parseInt(strs[1]));

            return user;
        });

        for (int i = 0; i < 100; i++) {
            User user = mappingSource.next();
            System.out.println(user);
        }
    }
}
```

### Mapping Source

A simple mapping wrapper:

```java
public class ForgeTest {
    @Test
    void beanSource2() throws IOException {

        Source<String> source = Forge.buildFromClassPath("/forge/双列测试.txt");

        MappingSource<User, String> mappingSource = new MappingSource<>(source, (str) -> {
            String[] strs = str.split("\\t");
            User user = new User();
            user.setName(strs[0]);
            user.setAge(Integer.parseInt(strs[1]));

            return user;
        });

        for (int i = 0; i < 100; i++) {
            User user = mappingSource.next();
            System.out.println(user);
        }
    }
}
```

## Examples

If you want to fill some `Position` entities, you can write below in kotlin language:

```kotlin
class Position {
    var catelog: String?
    var age: Int?
}

class PositionTest {
    // 
    fun test() {
        // various sources
        val catalogSource = Forge.buildInValues("全职", "兼职", "实习", "临时", "小时工")
        val ageSource = SimpleIntRangeSource(20, 50, 10, 2)
        // ...


        for (position in positions) {
            // use sources
            position.catalog = catalogSource.next()
            position.age = ageSource.next()
            // ...
        }

        // all positions are modified
    }
}
```
