package ru.cs.vsu.yachnyy_m_a;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task9 {

    public static List<Integer> createNewList(List<Integer> list1, List<Integer> list2) {
        int[] array = new int[list1.size()];
        int c0 = -1;
        for (Integer a : list1) {
            c0++;
            array[c0] = a;
        }
        boolean[] bool_array = new boolean[array.length];
        int c = 0;
        for (int i = 0; i < array.length; i++) {
            if (indexOf(list1, array[i]) >= i || indexOf(list1, array[i]) == -1) {
                if (hasDivider(array[i], list2)) {
                    c++;
                    bool_array[i] = true;
                }
            }
        }
        Integer[] result = new Integer[c];
        int i1 = 0;
        for (int i = 0; i < bool_array.length; i++) {
            if (bool_array[i]) {
                result[i1] = array[i];
                i1++;
            }
        }
        return Arrays.asList(result);
    }

    private static boolean hasDivider(int a, List<Integer> list) {
        for (Integer i : list) {
            if (i != 0 && a != 0) {
                if (a % i == 0 && Math.abs(a) != Math.abs(i) && Math.abs(i) != 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int indexOf(List<Integer> list, int value) {
        int i = -1;
        for (Integer a : list) {
            i++;
            if (a == value) {
                return i;
            }
        }
        return -1;
    }

    public static List<Integer> intArrayToIntegerList(int[] array) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return Arrays.asList(result);
    }

    public static int[] integerListToIntArray(List<Integer> list){
        int[] result = new int[list.size()];
        int i = 0;
        for(Integer integer: list){
            result[i] = integer;
            i++;
        }
        return result;
    }
}
