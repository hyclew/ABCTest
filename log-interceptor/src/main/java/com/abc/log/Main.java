package com.abc.log;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;

public class Main {

    public static void main(String[] args) {

//        String json1 = "{id:1,name:\"Joe\",friends:[{id:2,name:\"Pat\",pets:[\"dog\"]},{id:3,name:\"Sue\",pets:[\"bird\",\"fish\"]}],pets:[]}";
//        String json2 = "{id:1,name:\"Joe\",friends:[{id:2,name:\"Pat\",pets:[\"dog\"]},{id:3,name:\"Sue\",pets:[\"cat\",\"fish\"]}],pets:[]}";

        String json1 = "{\"a\":\"\",\"b\":[{\"b1\":\"1\"},{\"b2\":\"2\"}]}";
        String json2 = "{\"a\":null,\"b\":[{\"b1\":\"1\"},{\"b2\":\"2\"}]}";

        try {
            JSONAssert.assertEquals(json1, json2, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("ok");
    }
}

