/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship;

/**
 *
 * @author GREGORIUS VITO
 */

import java.io.*;
import java.util.*;



public class Internship {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
        // TODO code application logic here

        int i = 0;
        
        Scanner s = new Scanner(new File("E:\\\\Gregorius Vito\\\\Kuliah\\\\Sem 6\\\\Magang\\\\Data set postag\\\\train.01.tsv"));
        Scanner d = new Scanner(new File("E:\\\\Gregorius Vito\\\\Kuliah\\\\Sem 6\\\\Magang\\\\Data set postag\\\\dev.01.tsv"));
        Scanner t = new Scanner(new File("E:\\\\Gregorius Vito\\\\Kuliah\\\\Sem 6\\\\Magang\\\\Data set postag\\\\test.01.tsv"));
        ArrayList<String> list = new ArrayList<String>();
        
        ArrayList<String> listofTag = new ArrayList<>(); // list of tag
        ArrayList<String> listofWordTag = new ArrayList<>(); // list of word,tag
        
        ArrayList<String> testWord = new ArrayList<>(); // list of test word
        ArrayList<String> testTag = new ArrayList<>(); // list of test tag
        
        ArrayList<String> predictedTag = new ArrayList<>(); // list of predicted tag
        
        Map<String, Integer> mapTag = new HashMap<String, Integer>();
        Map<String, Integer> mapWordTag = new HashMap<String, Integer>();
        
        float acry;

        while (s.hasNextLine()){
            list.add(s.nextLine());
//            System.out.println(list.get(i));
            String[] part = list.get(i).split("\t");

            if (part.length==2){
//                System.out.println(part[0]);
//                System.out.println(part[1]);
                listofTag.add(part[1]);
                listofWordTag.add(part[0].toLowerCase()+","+part[1]);
            }
            i= i+1;
        }

        while (d.hasNextLine()){
            list.add(d.nextLine());
//            System.out.println(list.get(i));
            String[] part = list.get(i).split("\t");

            if (part.length==2){
//                System.out.println(part[0]);
//                System.out.println(part[1]);
                listofTag.add(part[1]);
                listofWordTag.add(part[0].toLowerCase()+","+part[1]);
            }
            i= i+1;
        }
        while (t.hasNextLine()){
            list.add(t.nextLine());
//            System.out.println(list.get(i));
            String[] part = list.get(i).split("\t");

            if (part.length==2){
//                System.out.println(part[0]);
//                System.out.println(part[1]);
                testTag.add(part[1]);
                testWord.add(part[0].toLowerCase());
            }
            i= i+1;
        }
        mapTag = countFreq(listofTag);
        mapWordTag = countFreq(listofWordTag);
        predictedTag = baseline(testWord,mapTag,mapWordTag);
//        acry= acc(predictedTag,testTag);
        s.close();
        d.close();
        t.close();
        System.out.println(mapTag);
        System.out.println(mapWordTag);
        System.out.println(predictedTag);
        System.out.println(testTag);
//        System.out.println(acry);
//        System.out.println(testWord);


    }
    public static Map<String,Integer> countFreq(ArrayList<String> list) 
    { 
        Map<String, Integer> hm = new HashMap<String, Integer>(); 
        for (String i : list) { 
            Integer j = hm.get(i); 
            hm.put(i, (j == null) ? 1 : j + 1); 
        } 
        return hm;
    } 
    
    public static ArrayList<String> baseline (ArrayList<String> list,Map<String, Integer> mapTag,Map<String, Integer> mapWordTag){
        ArrayList<String> predictedKeys = new ArrayList<>();
        for (String i : list) {
            String key = null ;
            int maximum = 0;
            for (Map.Entry<String, Integer> val : mapTag.entrySet()) {
                String test = i +','+val.getKey();
                if (mapWordTag.containsKey(test)){
                    if (mapWordTag.get(test)>maximum){
                        maximum = mapWordTag.get(test);
                        key = val.getKey();
                    }
                }
            }
            if (maximum==0){
                key = "NN";
            }
            predictedKeys.add(key);
        }
        return predictedKeys;
    }
    
    public static float acc (ArrayList<String> predict,ArrayList<String> test){
        String[] apredict = new String[predict.size()];
        String[] atest = new String[test.size()];
        
        int t = 0,i=0;
        while (i<apredict.length){
            if (apredict[i] == atest[i]){
                t =+ 1;
            }
            i=+ 1;
        }
        return(t/i);
    }
    
    
}


