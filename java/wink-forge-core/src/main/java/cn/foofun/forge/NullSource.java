package cn.foofun.forge;

public class NullSource implements Source<String> {

    @Override
    public String next() {
        return "无效数据";
    }
}
