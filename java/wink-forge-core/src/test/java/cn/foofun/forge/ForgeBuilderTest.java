package cn.foofun.forge;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

public class ForgeBuilderTest {

    @Test
    void mapSource() throws IOException {

        Source<String> nameSource = Forge.combineSources(Forge.buildFromClassPath("/forge/男名.txt"), Forge.buildFromClassPath("/forge/女名.txt"), 2, 1);

        MapSource source = Forge.buildMap("age", Forge.buildInt(20, 50))
                .add("name", nameSource).toMapSource();

        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = source.next();
            for (String key : map.keySet()) {
                System.out.println(key + ":" + map.get(key));
            }
            System.out.println();
        }
    }

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