/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemprogrammingproject2;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author hagam
 */
public class ReadingFromFile {
    
    
       public static void readAndWriteDataLineByLine(String file, String filePath) 
        { 
            HashMap <String, String> myMap = ReadingFromFile.creatingInstructionSetMap("InstructionSet.csv");
            myMap.remove("");
            ArrayList<String> myList = ReadingFromFile.creatingformatTwoInstructionsList("Format2InstructionSet.csv");
            File theFile = new File(filePath);

            try { 

                // Create an object of filereader 
                // class with CSV file as a parameter. 
                FileReader filereader = new FileReader(file); 


                CSVReader csvReader = new CSVReader(filereader); 
                String[] nextRecord;

                // create FileWriter object with file as parameter 
                FileWriter outputfile = new FileWriter(theFile); 


                CSVWriter writer = new CSVWriter(outputfile); 


                String[] header = { "loc", "st1", "st2", "st3","object code" }; 
                writer.writeNext(header);
                String[] data1 = { "0000", "COPY", "START", "0000" };
                writer.writeNext(data1);
                String [] test = csvReader.readNext();
                test = csvReader.readNext();
                test = csvReader.readNext();
                String [] data3 = {data1[0], test[1], test[2], test[3]};
                writer.writeNext(data3);


                while ((nextRecord = csvReader.readNext()) != null) {

                   // add an extra condition for format 2...................
                    if (myList.contains(data3[2])){

                        String[] data2 = { HexadecimalAddition.addHex(data3[0], "2"), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                        writer.writeNext(data2);
                        data3 = data2;
                    }
                    else if(myMap.containsKey(data3[2])){

                        String[] data2 = { HexadecimalAddition.addHex(data3[0], "3"), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                        writer.writeNext(data2);
                        data3 = data2;
                    }
                    else if(data3[2].charAt(0) == '+' && myMap.containsKey(data3[2].substring(1))){
                        //instead of using charat(0) i can use the method startswith("+").........

                        String[] data2 = { HexadecimalAddition.addHex(data3[0], "4"), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                        writer.writeNext(data2);
                        data3 = data2;  
                    }
                    else if(data3[2].contains("BASE")){

                        String[] data2 = { data3[0], nextRecord[1], nextRecord[2], nextRecord[3] }; 
                        writer.writeNext(data2);
                        data3 = data2;
                    }
                    else if(data3[2].contains("WORD")){

                        String[] data2 = { HexadecimalAddition.addHex(data3[0], "3"), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                        writer.writeNext(data2);
                        data3 = data2;
                    }
                    else if(data3[2].contains("BYTE") && data3[3].charAt(0) == 'C'){

                        String[] data2 = { HexadecimalAddition.addHex(data3[0], String.valueOf(data3[3].length()-3)), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                        writer.writeNext(data2);
                        data3 = data2;
                    }
                    else if(data3[2].contains("BYTE") && data3[3].charAt(0) == 'X'){

                        String[] data2 = { HexadecimalAddition.addHex(data3[0], String.valueOf((data3[3].length()-3)/2)), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                        writer.writeNext(data2);
                        data3 = data2;
                    }
                    else if(data3[2].contains("RESW")){

                        String[] data2 = { HexadecimalAddition.addHex(data3[0],Integer.toHexString(3*Integer.parseInt(data3[3]))), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                        writer.writeNext(data2);
                        data3 = data2;
                    }
                    else if(data3[2].contains("RESB")){

                        String[] data2 = { HexadecimalAddition.addHex(data3[0],Integer.toHexString(1*Integer.parseInt(data3[3]))), nextRecord[1], nextRecord[2], nextRecord[3] }; 
                        writer.writeNext(data2);
                        data3 = data2;
                    }

                }

                writer.close();

            } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
    
    
        }
       
       public static HashMap <String, String> creatingInstructionSetMap(String file)
       {
           HashMap <String, String> instructionSet = new HashMap<>();
           try { 
  
         
                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord; 


                    while ((nextRecord = csvReader.readNext()) != null) { 

                            instructionSet.put(nextRecord[0], nextRecord[1]); 
                    } 
            } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
           return instructionSet;
       }
       
       
       
       public static ArrayList <String> creatingformatTwoInstructionsList(String file){
           
           ArrayList <String> format2instructions = new ArrayList<>();
           
            try { 


                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord; 


                    while ((nextRecord = csvReader.readNext()) != null) { 

                            format2instructions.add(nextRecord[0]);
                    } 
                } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
            return format2instructions;
       }
       
       public static HashMap <Character, String> creatingRegistersMap(){
           HashMap <Character, String> theMap = new HashMap<>();
           String[]te = {"0", "1", "2", "4", "5", "6"};
           Character[]te2 = {'A', 'X', 'B', 'S', 'T', 'F'};
           for(int i = 0; i < te.length; i++){
               theMap.put(te2[i], te[i]);
           }
           return theMap;
       }
       
       
       public static ArrayList <String> creatingObjList(String file){
           
           ArrayList <String> ObjList = new ArrayList<>();
           
            try { 


                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord; 


                    while ((nextRecord = csvReader.readNext()) != null) { 

                            ObjList.add(nextRecord[0]);
                    } 
                } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
            return ObjList;
       }
       
       
       public static void creatingSymbolTable(String file, String filePath)
       {
           File theFile = new File(filePath);
           
           try { 
  
        
                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord;


                    FileWriter outputfile = new FileWriter(theFile); 


                    CSVWriter writer = new CSVWriter(outputfile); 


                    String[] header = { "symbol", "loc"}; 
                    writer.writeNext(header);


                    while ((nextRecord = csvReader.readNext()) != null) {

                       if(nextRecord[1] !="" ){
                           String [] data = {nextRecord[1], nextRecord[0]};
                           writer.writeNext(data);
                       }

                    }

                    writer.close();

            } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
       }
       
       
       public static void addObjectCode(String file, String filePath){
           
            HashMap <String, String> myMap = ReadingFromFile.creatingInstructionSetMap("InstructionSet.csv");
            myMap.remove("");
            HashMap<String, String> symbolTable = ReadingFromFile.covertSymbolTableToMap("data2.csv");
            symbolTable.remove("");
            HashMap<Character, String> regMap = ReadingFromFile.creatingRegistersMap();
            regMap.remove("");
            ArrayList<String> myArray = ReadingFromFile.creatingformatTwoInstructionsList("Format2InstructionSet.csv");

            File theFile = new File(filePath);

            try { 


                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord;


                    FileWriter outputfile = new FileWriter(theFile); 


                    CSVWriter writer = new CSVWriter(outputfile); 


                    String[] header = { "loc", "st1", "st2", "st3","object code" }; 
                    writer.writeNext(header);
                    String [] test = csvReader.readNext();


                    while ((nextRecord = csvReader.readNext()) != null) {

           //                if(myMap.containsKey(nextRecord[2]) && (nextRecord[3].charAt((nextRecord[3].length()-1)) == 'X' && nextRecord[3].charAt((nextRecord[3].length()-2)) == ',')){
           //                    String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], myMap.get(nextRecord[2]) + HexadecimalAddition.addHex(symbolTable.get(nextRecord[3].substring(0, nextRecord[3].length()-2)), "8000")};
           //                    writer.writeNext(data);
           //                }

                       if(myMap.containsKey(nextRecord[2]) || myMap.containsKey(nextRecord[2].substring(1))){
                             //if(nextRecord[3] == ""){

                                 //String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], myMap.get(nextRecord[2]) + "0000"};
                                 //writer.writeNext(data);
                             //}
//                             else if((nextRecord[3].charAt((nextRecord[3].length()-1)) == 'X' && nextRecord[3].charAt((nextRecord[3].length()-2)) == ',')){
//
//                                 String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], myMap.get(nextRecord[2]) + HexadecimalAddition.addHex(symbolTable.get(nextRecord[3].substring(0, nextRecord[3].length()-2)), "8000")};
//                                 writer.writeNext(data);
//                             }
                             if(myArray.contains(nextRecord[2])){
                                 if(nextRecord[3].length()== 1){
                                    String res = myMap.get(nextRecord[2]);
                                    for(int i = 0; i < nextRecord[3].length(); i++){
                                        if(regMap.containsKey(nextRecord[3].charAt(i))){
                                            res += regMap.get(nextRecord[3].charAt(i));
                                        }
                                    }
                                 String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], res+"0"};
                                 writer.writeNext(data); 
                                 }
                                 else{
                                     String res = myMap.get(nextRecord[2]);
                                    for(int i = 0; i < nextRecord[3].length(); i++){
                                        if(regMap.containsKey(nextRecord[3].charAt(i))){
                                            res += regMap.get(nextRecord[3].charAt(i));
                                        }
                                    }
                                    String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], res};
                                    writer.writeNext(data);
                                 }
                             }
                             else{
                                 String opCode = "";
                                 if(myMap.containsKey(nextRecord[2])){
                                     opCode = ReadingFromFile.convertOpCodeFromHexaToBinary(myMap.get(nextRecord[2]));
                                 }
                                 else{
                                     opCode = ReadingFromFile.convertOpCodeFromHexaToBinary(myMap.get(nextRecord[2].substring(1)));
                                 }
                                 String ni = ReadingFromFile.determineNI(nextRecord[3]);
                                 String x = ReadingFromFile.determineX(nextRecord[3]);
                                 String bp = ReadingFromFile.determineBP(nextRecord[3], nextRecord[2], nextRecord[0]);
                                 //String e = ReadingFromFile.determineE(nextRecord[2]);
                                 String temp = opCode+ ni + x + bp;
                                 String result = ReadingFromFile.convertBinaryToHexa(temp);

                                 String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], result};
                                 writer.writeNext(data);
                             }

                       }
                       else if(nextRecord[2].contains("WORD")){
                           String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], Integer.toHexString(Integer.parseInt(nextRecord[3]))};
                           writer.writeNext(data);
                       }
                       else if(nextRecord[2].contains("BASE")){
                           String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], ""};
                           writer.writeNext(data);
                       }
                       else if(nextRecord[2].contains("RESW")|| nextRecord[2].contains("RESB")){
                           String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], ""};
                           writer.writeNext(data);
                       }
                       else if(nextRecord[2].contains("BYTE") && nextRecord[3].charAt(0) == 'X'){
                           String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], nextRecord[3].substring(2, nextRecord[3].length()-1)};
                           writer.writeNext(data);
                       }
                       else if(nextRecord[2].contains("BYTE") && nextRecord[3].charAt(0) == 'C'){
                           String [] data = {nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3],ReadingFromFile.convertStringToHex(nextRecord[3].substring(2, (nextRecord[3].length()-1)))};
                           writer.writeNext(data);
                       } 

                    }

                    writer.close();

             } 
             catch (Exception e) { 
             e.printStackTrace(); 
             }
        }
       
        public static String convertStringToHex(String str) {

            StringBuffer hex = new StringBuffer();

            // loop chars one by one
            for (char temp : str.toCharArray()) {

                // convert char to int, for char `a` decimal 97
                int decimal = (int) temp;

                // convert int to hex, for decimal 97 hex 61
                hex.append(Integer.toHexString(decimal));
            }

            return hex.toString();

     }
        
        public static HashMap <String, String> covertSymbolTableToMap(String file)
       {
            HashMap <String, String> symbolTable = new HashMap<>();
            try { 


                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord; 


                    while ((nextRecord = csvReader.readNext()) != null) { 

                            symbolTable.put(nextRecord[0], nextRecord[1]); 
                    } 
                } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
            return symbolTable;
        }
        
        public static String convertOpCodeFromHexaToBinary(String s){
            if(s != null){
                //int y = Integer.parseInt(s,16);
                //String eq = Integer.toBinaryString(y);
                //return eq.substring(0, eq.length()-1);
                String eq = GFG.hexToBinary(s);
                return eq.substring(0, eq.length()-2);
            }
            return "";
        }
        public static String determineNI(String s){
            if(s != null && !s.isEmpty()){
                if(s.charAt(0) == '#'){
                    return "01";
                }
                else if(s.charAt(0) == '@'){
                    return "10";
                }
                else{
                    return "11";
                }
            }
             return "11";  
        }
        
        public static String determineBP(String s,String f, String l){
            if(s != null && f != null&& !s.isEmpty()&& !f.isEmpty()){
                //ArrayList<String> myArray = ReadingFromFile.creatingObjList("myTets.csv");
                HashMap<String, String> symbolTable = ReadingFromFile.covertSymbolTableToMap("data2.csv");
                symbolTable.remove("");
                //symbolTable.remove("symbol");
                //symbolTable.remove("st1");
                MyLinkedList lls = ReadingFromFile.creatLocationsLinkedList("data1.csv");
                lls.removeAtBeg();
                lls.removeAtBeg();
                lls.removeAtBeg();

                if(!symbolTable.containsKey(s) && !symbolTable.containsKey(s.substring(1)) && !s.endsWith(",X")){
                    if(!symbolTable.containsKey(s.substring(1)) && f.charAt(0) == '+'){
                        String res = convertDecimalToBinary(s.substring(1));
                        String Zeros = "0";
                        for(int i = res.length(); i< 19; i++){
                            Zeros += "0";
                        }
                        return "00" + ReadingFromFile.determineE(f) +Zeros + res;
                    }
                    
                    else{
                        String res = convertDecimalToBinary(s.substring(1));
                        String Zeros = "0";
                        for(int i = res.length(); i< 11; i++){
                            Zeros += "0";
                        }
                        return "00" + ReadingFromFile.determineE(f) + Zeros + res;
                    }
                }
                else if(symbolTable.containsKey(s) && f.charAt(0) == '+'){
                        String res = GFG.hexToBinary(symbolTable.get(s));
                        String Zeros = "0";
                        for(int i = res.length(); i< 19; i++){
                            Zeros += "0";
                        }
                        return "00" + ReadingFromFile.determineE(f) +Zeros + res;
                }
                else{
                    if( symbolTable.containsKey(s)){
                        String eq = HexadecimalSubtraction.subtractHex(symbolTable.get(s), lls.getNextNodeData(l));
                        int x = Integer.parseInt(eq, 16);
                        if(Integer.parseInt(symbolTable.get(s),16) < Integer.parseInt(lls.getNextNodeData(l),16) && Integer.parseInt(symbolTable.get(s),16) - Integer.parseInt(lls.getNextNodeData(l),16)> -2048){
                            String res = GFG.hexToBinary(eq.substring(1));
                            if(res.length()<12){
                                String Zeros = "0";
                                for(int i = res.length(); i< 11; i++){
                                    Zeros += "0";
                                }
                            return "01" + ReadingFromFile.determineE(f) + Zeros + res;
                            }
                            return "01" + ReadingFromFile.determineE(f) + res;
                        }
                        
                        if(x >= -2048 && x <=2047){
                            String res = GFG.hexToBinary(eq);
                            if(res.length()<12){
                                String Zeros = "0";
                                for(int i = res.length(); i< 11; i++){
                                    Zeros += "0";
                                }
                            return "01" + ReadingFromFile.determineE(f) + Zeros + res;
                            }
                            return "01" + ReadingFromFile.determineE(f) + res;
                        }
                        
                        eq = HexadecimalSubtraction.subtractHex(symbolTable.get(s), symbolTable.get("LENGTH"));
                        x = Integer.parseInt(eq, 16);
                        if(x >= 0 && x <=4095){
                            String res = GFG.hexToBinary(eq);
                            if(res.length()<12){
                                String Zeros = "0";
                                for(int i = res.length(); i< 11; i++){
                                    Zeros += "0";
                                }
                            return "10" + ReadingFromFile.determineE(f) + Zeros + res;
                            }
                            return "10" + ReadingFromFile.determineE(f) + res;
                        }
                        
                    }
                    else if(symbolTable.containsKey(s.substring(1))){
                        
                        String eq = HexadecimalSubtraction.subtractHex(symbolTable.get(s.substring(1)), lls.getNextNodeData(l));
                        int x = Integer.parseInt(eq, 16);
                        if(Integer.parseInt(symbolTable.get(s.substring(1)),16) < Integer.parseInt(lls.getNextNodeData(l),16) && Integer.parseInt(symbolTable.get(s.substring(1)),16) - Integer.parseInt(lls.getNextNodeData(l),16) > -2048){
                            String res = GFG.hexToBinary(eq.substring(1));
                            if(res.length()<12){
                                String Zeros = "0";
                                for(int i = res.length(); i< 11; i++){
                                    Zeros += "0";
                                }
                            return "01" + ReadingFromFile.determineE(f) + Zeros + res;
                            }
                            return "01" + ReadingFromFile.determineE(f) + res;
                        }
                        
                        if(x >= -2048 && x <=2047){
                            String res = GFG.hexToBinary(eq);
                            if(res.length()<12){
                                String Zeros = "0";
                                for(int i = res.length(); i< 11; i++){
                                    Zeros += "0";
                                }
                            return "01" + ReadingFromFile.determineE(f) + Zeros + res;
                            }
                            return "01" + ReadingFromFile.determineE(f) + res;
                        }
                        eq = HexadecimalSubtraction.subtractHex(symbolTable.get(s.substring(1)), symbolTable.get("LENGTH"));
                        x = Integer.parseInt(eq, 16);
                        if(x >= 0 && x <=4095){
                            String res = GFG.hexToBinary(eq);
                            if(res.length()<12){
                                String Zeros = "0";
                                for(int i = res.length(); i< 11; i++){
                                    Zeros += "0";
                                }
                            return "10" + ReadingFromFile.determineE(f) + Zeros + res;
                            }
                            return "10" + ReadingFromFile.determineE(f) + res;
                        } 
                    }
                    else if(s.endsWith(",X")&& symbolTable.containsKey(s.substring(0, s.length()-2))){
                        
                        String eq = HexadecimalSubtraction.subtractHex(symbolTable.get(s.substring(0, s.length()-2)), lls.getNextNodeData(l));
                        int x = Integer.parseInt(eq, 16);
                        if(Integer.parseInt(symbolTable.get(s.substring(0, s.length()-2)),16) < Integer.parseInt(lls.getNextNodeData(l),16) && Integer.parseInt(symbolTable.get(s.substring(0, s.length()-2)),16) - Integer.parseInt(lls.getNextNodeData(l),16) > -2048){
                            String res = GFG.hexToBinary(eq.substring(1));
                            if(res.length()<12){
                                String Zeros = "0";
                                for(int i = res.length(); i< 11; i++){
                                    Zeros += "0";
                                }
                            return "01" + ReadingFromFile.determineE(f) + Zeros + res;
                            }
                            return "01" + ReadingFromFile.determineE(f) + res;
                        }

                        if(x >= -2048 && x <=2047){
                            String res = GFG.hexToBinary(eq);
                            if(res.length()<12){
                                String Zeros = "0";
                                for(int i = res.length(); i< 11; i++){
                                    Zeros += "0";
                                }
                            return "01" + ReadingFromFile.determineE(f) + Zeros + res;
                            }
                            return "01" + ReadingFromFile.determineE(f) + res;
                        }
                        eq = HexadecimalSubtraction.subtractHex(symbolTable.get(s.substring(0, s.length()-2)), symbolTable.get("LENGTH"));
                        x = Integer.parseInt(eq, 16);
                        if(x >= 0 && x <=4095){
                            String res = GFG.hexToBinary(eq);
                            if(res.length()<12){
                                String Zeros = "0";
                                for(int i = res.length(); i< 11; i++){
                                    Zeros += "0";
                                }
                            return "10" + ReadingFromFile.determineE(f) + Zeros + res;
                            }
                            return "10" + ReadingFromFile.determineE(f) + res;
                        }
                    }   
                }
            }
            return "000000000000000";    
        }
        
        
        
        public static String determineX(String s){
            if(s != null && !s.isEmpty()){
               if(s.endsWith(",X")){
                    return "1";
                }
                else{
                    return"0";
                } 
            }
           return "0"; 
        }
        public static String determineE(String s){
            if(s != null && !s.isEmpty()){
               if(s.charAt(0) == '+'){
                    return "1";
                }
                else{
                    return "0";
                } 
            }
           return "0"; 
        }
        public static String convertBinaryToHexa(String s){
            
            if(s != null){
                long x = Long.parseLong(s, 2);
                return Long.toHexString(x);
            }
            return "";
        }
        public static String convertDecimalToBinary(String s){
            if (StringUtils.isNumeric(s)) {
                int x = Integer.parseInt(s);
                return Integer.toBinaryString(x);
            } else {
                // Handle non-numeric case, e.g., return a default value or log a message
                return "";
            }
        }
        
        
        public static MyLinkedList creatLocationsLinkedList(String file){
            MyLinkedList sll = new MyLinkedList();
            try { 


                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord; 


                    while ((nextRecord = csvReader.readNext()) != null) { 

                            sll.insert(nextRecord[0]); 
                    } 
                } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
            return sll;
        }
        
        
        public static ArrayList modificationRecordList(String file){
            ArrayList <String> myList = new ArrayList<>();
            HashMap<String, String> symbolTable = ReadingFromFile.covertSymbolTableToMap("data2.csv");
            symbolTable.remove("");
            
            try { 


                    FileReader filereader = new FileReader(file); 


                    CSVReader csvReader = new CSVReader(filereader); 
                    String[] nextRecord; 


                    while ((nextRecord = csvReader.readNext()) != null) { 

                            if(nextRecord[2].charAt(0) == '+' && symbolTable.containsKey(nextRecord[3])){
                                myList.add(nextRecord[0]);
                            }
                    } 
                } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
            return myList;
        }
        
        public static void creatingHteRecord(String file, String helperFile, String filePath){
            HashMap <String, String> myMap = covertSymbolTableToMap("data2.csv");
            ArrayList <String> myList = ReadingFromFile.modificationRecordList("data1.csv");
            File theFile = new File(filePath);

            try { 

                    // Create an object of filereader 
                    // class with CSV file as a parameter. 
                    FileReader filereader = new FileReader(file); 
                    FileReader filereader2 = new FileReader(helperFile);

                    CSVReader csvReader = new CSVReader(filereader);
                    String[] nextRecord = csvReader.readNext();
                    nextRecord = csvReader.readNext();
                    
                    CSVReader csvReader2 = new CSVReader(filereader2);
                    String[] nextRecord2;
                    String programName = "";
                    
                    
                    while ((nextRecord2 = csvReader2.readNext()) != null){
                        if(nextRecord2[2].contains("START")){
                            programName = nextRecord2[1];
                            break;
                        }
                    }
                    
                    
                    // create FileWriter object with file as parameter 
                    FileWriter outputfile = new FileWriter(theFile); 


                    CSVWriter writer = new CSVWriter(outputfile); 
                    
                    String[] header = {"H", programName, myMap.get("FIRST"), HexadecimalSubtraction.subtractHex(HexadecimalAddition.addHex(myMap.get("OUTPUT"), "1"), myMap.get("FIRST"))};
                    writer.writeNext(header);
                    
                    while (nextRecord != null) {
                        System.out.println(nextRecord[0]);
                        int count = 0;
                        String[]objArr = new String[10];
                        String start = nextRecord[0];
                        while(nextRecord != null && !nextRecord[4].equals("No Object Code") && count < 10 ){
                            objArr[count] = nextRecord[4];
                            nextRecord = csvReader.readNext();
                            count++;
                        }
                        String end;
                        if(nextRecord != null){
                           end = nextRecord[0]; 
                        }
                        else{
                            end = HexadecimalAddition.addHex(myMap.get("OUTPUT"), "1");
                        }
                        
                        String[]newObjArr = new String[objArr.length+3];
                        newObjArr[0] = "T";
                        newObjArr[1] = start;
                        newObjArr[2] = HexadecimalSubtraction.subtractHex(end, start);
                        for(int i = 3; i< newObjArr.length; i++){
                            newObjArr[i] = objArr[i-3];
                        }
                        writer.writeNext(newObjArr);
                        
                        while(nextRecord != null && nextRecord[4].equals("No Object Code") ){
                            nextRecord = csvReader.readNext();
                        }
                    }
                    for(String s: myList){
                        String [] tail = {"M","00" + HexadecimalAddition.addHex(s, "0001"), "05"};
                        writer.writeNext(tail);
                    }
                    String [] tail = {"E", myMap.get("FIRST")};
                    writer.writeNext(tail);

                    writer.close();

            } 
            catch (Exception e) { 
                e.printStackTrace(); 
            }
        }
}