package cn.foofun.forge.business;

import cn.foofun.forge.Source;

public class TelephoneSource implements Source<String> {

    @Override
    public String next() {
        return "0086-010-66778899";
    }
}
