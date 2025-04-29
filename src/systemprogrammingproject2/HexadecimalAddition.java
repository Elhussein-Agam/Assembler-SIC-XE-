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
public class HexadecimalAddition {
    
    
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
 
    // Map for converting decimal values to hexadecimal
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
 
    // Function to add two hexadecimal numbers
    public static String addHex(String a, String b) {
        Map<Character, Integer> hexToDec = hexValueOfDec();
        Map<Integer, Character> decToHex = decValueOfHex();
 
        // Check if the length of the first string is greater than or equal to the second
        if (a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
 
        int l1 = a.length();
        int l2 = b.length();
 
        String result = "";
 
        int carry = 0;
        int i = l1 - 1;
        int j = l2 - 1;
 
        while (j >= 0) {
            int sum = hexToDec.get(a.charAt(i)) + hexToDec.get(b.charAt(j)) + carry;
            char additionBit = decToHex.get(sum % 16);
            result = additionBit + result;
            carry = sum / 16;
            i--;
            j--;
        }
 
        while (i >= 0) {
            int sum = hexToDec.get(a.charAt(i)) + carry;
            char additionBit = decToHex.get(sum % 16);
            result = additionBit + result;
            carry = sum / 16;
            i--;
        }
 
        if (carry > 0) {
            result = decToHex.get(carry) + result;
        }
 
        return result;
    }
//    public static void addObjectCode2(String file, String filePath){
//           
//            HashMap <String, String> myMap = ReadingFromFile.creatingInstructionSetMap("InstructionSet.csv");
//            myMap.remove("");
//            HashMap<String, String> symbolTable = ReadingFromFile.covertSymbolTableToMap("data2.csv");
//            symbolTable.remove("");
//            HashMap<Character, String> regMap = ReadingFromFile.creatingRegistersMap();
//            regMap.remove("");
//            ArrayList<String> myArray = ReadingFromFile.creatingformatTwoInstructionsList("Format2InstructionSet.csv");
//            ArrayList<String> myArray2 = ReadingFromFile.creatingObjList("myTets.csv");
//            int count = 0;
//
//            File theFile = new File(filePath);
//
//            try { 
//
//
//                    FileReader filereader = new FileReader(file); 
//
//
//                    CSVReader csvReader = new CSVReader(filereader); 
//                    String[] nextRecord;
//
//
//                    FileWriter outputfile = new FileWriter(theFile); 
//
//
//                    CSVWriter writer = new CSVWriter(outputfile); 
//
//
//                    String[] header = { "loc", "st1", "st2", "st3","object code" }; 
//                    writer.writeNext(header);
//                    String [] test = csvReader.readNext();
//                    test = csvReader.readNext();
//
//                    while ((nextRecord = csvReader.readNext()) != null) {
//
//                        String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], myArray2.get(count++)};
//                        writer.writeNext(data);
//                       
//                    }
//
//                    writer.close();
//
//             } 
//             catch (Exception e) { 
//             e.printStackTrace(); 
//             }
//        }
}
