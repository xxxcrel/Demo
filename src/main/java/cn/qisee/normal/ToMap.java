package cn.qisee.normal;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ToMap {

    public static void main(String[] args) {
        listToMap((x) -> x.stream().collect(Collectors.toMap(id -> id, ToMap::caculateI)));
    }


    public static void listToMap(Function<List<String>, Map<String , String>> function){

    }

    public static String caculateI(String i){
        return i;
    }
}
