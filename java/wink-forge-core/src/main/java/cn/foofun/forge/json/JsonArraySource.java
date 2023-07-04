package cn.foofun.forge.json;

import cn.foofun.forge.IntSource;
import cn.foofun.forge.Source;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

public class JsonArraySource implements Source<JSONObject> {

    IntSource source;

    JSONArray jsonArray;

    public JsonArraySource(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
        this.source = new IntSource(0, jsonArray.size() - 1, 1);
    }

    @Override
    public JSONObject next() {
        return this.jsonArray.getJSONObject(this.source.next());
    }
}
