/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemprogrammingproject2;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hagam
 */
public class HexadecimalSubtraction {

    public static Map<Character, Integer> hexValueOfDec() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('0', 0);
        map.put('1', 1);
        map.put('2', 2);
        map.put('3', 3);
        map.put('4', 4);
        map.put('5', 5);
        map.put('6', 6);
        map.put('7', 7);
        map.put('8', 8);
        map.put('9', 9);
        map.put('A', 10);
        map.put('B', 11);
        map.put('C', 12);
        map.put('D', 13);
        map.put('E', 14);
        map.put('F', 15);
        return map;
    }

    public static Map<Integer, Character> decValueOfHex() {
        Map<Integer, Character> map = new HashMap<>();
        map.put(0, '0');
        map.put(1, '1');
        map.put(2, '2');
        map.put(3, '3');
        map.put(4, '4');
        map.put(5, '5');
        map.put(6, '6');
        map.put(7, '7');
        map.put(8, '8');
        map.put(9, '9');
        map.put(10, 'A');
        map.put(11, 'B');
        map.put(12, 'C');
        map.put(13, 'D');
        map.put(14, 'E');
        map.put(15, 'F');
        return map;
    }

    public static String subtractHex(String a, String b) {
//        if (a == null || b == null) {
//        // Handle the case where either a or b is null
//        return "";
//        }
        Map<Character, Integer> hexToDec = hexValueOfDec();
        Map<Integer, Character> decToHex = decValueOfHex();
 
        if (a.equals(b)) {
            return "0";
        }
 
        if (a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
 
        int l1 = a.length();
        int l2 = b.length();
 
        StringBuilder result = new StringBuilder();
 
        int borrow = 0;
        int i = l1 - 1;
        int j = l2 - 1;
 
        while (j >= 0) {
            int diff = hexToDec.get(a.charAt(i)) - hexToDec.get(b.charAt(j)) - borrow;
            if (diff < 0) {
                diff += 16;
                borrow = 1;
            } else {
                borrow = 0;
            }
            char subtractionBit = decToHex.get(diff);
            result.insert(0, subtractionBit);
            i--;
            j--;
        }
 
        while (i >= 0) {
            int diff = hexToDec.get(a.charAt(i)) - borrow;
            if (diff < 0) {
                diff += 16;
                borrow = 1;
            } else {
                borrow = 0;
            }
            char subtractionBit = decToHex.get(diff);
            result.insert(0, subtractionBit);
            i--;
        }
 
        // Remove leading zeros
        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }
 
        return result.toString();
    }

    
}