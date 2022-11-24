- [English](README.md)
- [Chinese](README-cn.md)

## 简介

这个工具（wink-forge）从一个简单的想法开始。我的老板总是说“我想向客户展示你的项目进展，但是它的UI看起来好像有错误！"
因为当测试项目时，我总是在表单中随意输入，所以页面上面肯定有很多业务无关的数据内容，这些内容让用户费解。通过这个项目，我想创建一个工具来解决这个问题。

为了最简化使用，这个项目不依赖任何第三库。 但是目前代码中还缺乏一些最基本的异常保护，所以需要让输入的参数尽量合理正确。

## 安装

```xml
<dependency>
    <groupId>cn.foofun</groupId>
    <artifactId>wink-forge-core</artifactId>
    <version>0.0.2</version>
</dependency>
```

## 核心概念

```java
public interface Source<T> {
    T next();
}
```

`Source` 是一个核心概念接口. 调用它的 `next` 方法可以一项接一项的生成数据。 比如我们可以在项目写一个这样的类（详情见源代码）：

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
        // 返回两个随机手机号
        String r1 = mobileSource.next();
        String r2 = mobileSource.next();
    }
}
```

`MobileSource` 是一个用来生成中国三大运营商手机号的工具。

## 各种Source

### Forge

`Forge` 是一个类生产辅助类，它有很多build开头的方法，用于生成各种现有Source:

```java
class App {
    public static main() {
        // 生成8-80内随机不同Int
        Source<Integer> source = Forge.buildInt(8, 80);
        // 读取文本文件每行作为随机内容返回
        Source<String> source = Forge.buildFromClassPath("/forge/岗位名称.txt");
    }
}
```

### IntSource

`IntSource`是一个比较重要的source。它可以按步进值生成从最大值到最小值的随机数，并且每次生成的随机数不同。 （next调用次数超过了总个数，将会重复开始。）

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

`ValueListSource`用于包含多个同类型的值，然后用`next`方法随机取出。

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

这两个`Source`类用于读取多行文本（从变量或文件流中），随机取出任意一行。其实上，他们内部就是用`IntSource`实现的。

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

## 高级

本文前面介绍了一些简单的Source。您可以阅读单元测试源代码以了解更多信息。从这里开始我们将演示一些复杂的小技巧。

### 组合两个Source

下面的代码结合了两个`Source`，第一个Source每次有1/3的机会取到，第二个Source则每次有2/3的机会取到:

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

### 组合Source

将原始数据类型源合并为复合数据:

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

一个简单的数据重映射包装：

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

## 综合例子

如果你想要填充`Position`实体类，你可以使用如下kotlin代码:

```kotlin
class Position {
    var catelog: String?
    var age: Int?
}

class PositionTest {
    // 
    fun test() {
        // 各种Source
        val catalogSource = Forge.buildInValues("全职", "兼职", "实习", "临时", "小时工")
        val ageSource = SimpleIntRangeSource(20, 50, 10, 2)
        // ...
        
        for (position in positions) {
            // 使用Source
            position.catalog = catalogSource.next()
            position.age = ageSource.next()
            // ...
        }

        // 所有Position都已被修改
    }
}

```
