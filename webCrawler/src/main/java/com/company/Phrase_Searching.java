package com.company;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Phrase_Searching {
    private  DB db = new DB();
    //private  Indexer indexer = new Indexer();
    private   Map<String, Integer> count = new HashMap<>();
    private   Map<String, Integer> URL = new HashMap<>();
    private  Map<String, Integer> temp = new HashMap<>();
    private    Map<String, ArrayList<ArrayList<Integer> >> indx = new HashMap<>();
    private  HashMap<String, List<String>> Results= new HashMap<>();
    private   List<String> link=new ArrayList<String>();
    private    String[] query;
    public static  void main(String[] args) {
        String[]word = new String[4];
        word[0] = "Deal";
        word[1] = "With";
        word[2] = "With";
        word[3] = "With";

//        phase(word);
//        System.out.println("Phrase");
//        if(Results==null)
//            return;
//        for (Map.Entry<String, List<String>> entry : Results.entrySet()){
//            //
//            System.out.println(entry.getKey()+" "+entry.getValue());
//        }
    }
    public  boolean  phrase(String[]words) throws IOException {
        Indexer.ReadStopWords();
        query= Indexer.removeStopWords(words);
        //query=words;
        int target =query.length;
        boolean flag=false;
        for(int i=0;i<query.length;i++)
        {
            flag=false;
            //System.out.println(query[i]);
            String lowerCaseString=query[i].toLowerCase();
            List<Document>docs=(List<Document>)db.getAttr("words","word",lowerCaseString,"urls");
            System.out.println(docs);
            if(docs==null)
            {
                return false;
            }
            for (Document object :  docs) {
                // System.out.println(docs);
                // System.out.println(object);
                String url = (String) object.get("url");
                ArrayList<Integer> post =(ArrayList<Integer>) object.get("positions");
                count.putIfAbsent(url, 0);
                count.put(url,count.get(url) +1 );
                indx.putIfAbsent(url, new ArrayList<ArrayList<Integer>>());
                indx.get(url).add(post);
                if( target==count.get(url)){
                    URL.putIfAbsent(url, 0);
                }
            }
        }
        for (Map.Entry<String, ArrayList<ArrayList<Integer> >> entry : indx.entrySet()){
            System.out.println("**indx "+entry.getKey()+" "+entry.getValue());
        }
        check();
        for (Map.Entry<String, List<String>> entry : Results.entrySet()) {
            System.out.println("***eslam "+entry.getKey() + " " + entry.getValue());
        }
        return true;
    }
    public   void check (){
        for (Map.Entry<String, Integer> entry : URL.entrySet()) {
            ArrayList<Integer> first =indx.get(entry.getKey()).get(0);
            int n=indx.get(entry.getKey()).size();
            int count=0;
            boolean flag=true;
            // System.out.println(entry.getKey());
            for(int i=0;i<first.size();i++)
            {
                flag=true;
                for(int j=1;j<n;j++){
                    if(!indx.get(entry.getKey()).get(j).contains(indx.get(entry.getKey()).get(0).get(i)+j)){
                        flag= false;
                        // System.out.println(entry.getKey()+" "+indx.get(entry.getKey()).get(0).get(i));
                        //System.out.println(indx.get(entry.getKey()).get(j));
                        break;
                    }
                }
                if(flag)
                    count++;
            }
            if (count > 0) {
                temp.put(entry.getKey(), count);
                //System.out.println(entry.getKey());
            }
        }
        for (Map.Entry<String, Integer> entry : temp.entrySet()){
            //
            //  System.out.println(entry.getKey()+" "+entry.getValue());
        }
        sortByValue(false);
        for (Map.Entry<String, Integer> entry : temp.entrySet()){
            link.add(entry.getKey());
            List<String> TitleDesc = new ArrayList<String>();
            String title = (String) db.getAttr("URLs", "url", entry.getKey(), "title");
            List<Document> pra =(List<Document>) db.getAttr("URLs", "url", entry.getKey(), "paragraphs");
            TitleDesc.add(title);
            String  paragraphs=String.join(" ",query).toLowerCase();
            String result=paragraphs;
            for(int i=0;i<pra.size();i++){
                String original =(String) db.getAttr("Paragraphs", "id",pra.get(i) , "content");
                String content=original.toLowerCase();
                String[] contentArr=content.split(" ");
                contentArr=Indexer.removeStopWords(contentArr);
                content=String.join(" ",contentArr);
                if(content.contains(paragraphs)){
                    result=original;
                    break;
                }
            }
            TitleDesc.add(result);
            Results.put(entry.getKey(), TitleDesc);
        }
    }
    public  void sortByValue(boolean order) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(temp.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
        temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list)
        {
            temp.put(entry.getKey(), entry.getValue());
        }
    }
    public List<String> getOutput() {
        return link;
    }
    public HashMap<String, List<String>> getResults() {
        return Results;
    }
}