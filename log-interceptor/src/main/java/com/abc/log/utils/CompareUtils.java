package com.abc.log.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CompareUtils {
    
    public static boolean compare2Json(String json1, String json2) {
        if (json1.length() != json2.length()) {
            return false;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> map1 = objectMapper.readValue(json1, Map.class);
            Map<String, String> map2 = objectMapper.readValue(json2, Map.class);


            return map1.equals(map2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

//    public static boolean compare2Map(Map<String, String> map1, Map<String, String> map2) {
//        if (map1.size() != map2.size()) {
//            return false;
//        }
//        for (Map.Entry<String, String> entry1 : map1.entrySet()) {
//            String m1value = entry1.getValue() == null ? "" : entry1.getValue();
//            String m2value = map2.get(entry1.getKey()) == null ? "" : map2.get(entry1.getKey());
//            if (!m1value.equals(m2value)) {
//                return false;
//            }
//        }
//        return true;
//    }

//    public static boolean compare2List(List<Result> airList, List<Result> cccList) throws IOException {
//        if (airList == null && cccList == null) {
//            return true;
//        }
//        if (airList == null || cccList == null) {
//            return false;
//        }
//        if (airList.size() != cccList.size()) {
//            return false;
//        }
//        for (int i = 0; i < airList.size(); i++) {
//            Integer airCode = airList.get(i).getCode();
//            Map<String, String> airMap = ParseUtil.parseToMap(airList.get(i).getData());
//            Integer cccCode = cccList.get(i).getCode();
//            Map<String, String> cccMap = (Map<String, String>) cccList.get(i).getData();
//            if (!airCode.equals(cccCode)) {
//                return false;
//            }
//            if (!compare2Map(airMap, cccMap)) {
//                return false;
//            }
//
//        }
//
//        return true;
//    }

}
