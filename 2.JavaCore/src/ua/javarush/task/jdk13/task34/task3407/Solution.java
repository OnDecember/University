package ua.javarush.task.jdk13.task34.task3407;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

/* 
Збирач типів
*/

public class Solution {

    private Map<TimeUnit, BiFunction<Integer, Long, Map<Boolean, List<String>>>> field;

    public static void main(String[] args) throws Exception {
        getTypes(Solution.class.getDeclaredField("field").getGenericType()).forEach(System.out::println);
    }

    public static Set<Type> getTypes(Type type) {
        Set<Type> set = new HashSet<>();
        if (!(type instanceof ParameterizedType)) {
            set.add(type);
        } else {
            ParameterizedType pType = (ParameterizedType) type;
            set.add(pType.getRawType());
            for (Type actualTypeArgument : pType.getActualTypeArguments()) {
                set.addAll(getTypes(actualTypeArgument));
            }
        }
        return set;
    }
}
