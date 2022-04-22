
package com.company;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Crawler {
    private int numOfThreads;
    private ArrayList<String>myLinks;
    private CompactString CS = new CompactString();
    private DB db = new DB();
    private int numberOfLinks;
    ArrayList<Thread> threads;
    private class myThread implements Runnable{
        private int start;
        private int end;
        myThread(int s,int e)
        {
            start=s;
            end=e;
        }
        @Override
        public void run() {
            for(int i=start;i<end;i++)
            {
                //System.out.println(Thread.currentThread().getName()+" crawls with : "+myLinks.get(i));
                if(Thread.currentThread().isInterrupted())
                    break;
                crawl(myLinks.get(i));
            }
            for(Thread t:threads)
                t.interrupt();
        }
    }
    public Crawler(String link,int num)  {
        numberOfLinks=(int)db.getAttr("Globals", "key","counter","value" );
        System.out.println("counter : "+numberOfLinks);
        myLinks=new ArrayList<String>();
        System.out.println("WebCrawler created");
        try {
            Connection connect = Jsoup.connect(link);
            Document doc = connect.get();
            Elements childrenLinks = doc.select("a[href]");
            for (Element child : childrenLinks) {
                String childLink = child.absUrl("href");
                myLinks.add(childLink);
            }
            int numOfLinks = myLinks.size();
            numOfThreads = Math.min(num, numOfLinks);
            threads= new ArrayList<Thread>(numOfThreads);
            int s = 0;
            int quantity = numOfLinks / numOfThreads;
            int rem = numOfLinks % numOfThreads;
            System.out.println("numOfLinks : " + numOfLinks + " numOfThreads : " + numOfThreads + " quantity : " + quantity + " rem : " + rem);
            for (int i = 0; i < numOfThreads; i++) {
                int e = s + quantity;
                if (rem != 0) {
                    e++;
                    rem--;
                }
                //System.out.println(i+" Starts with : "+s+" Ends with : "+e);
                Thread temp = new Thread(new myThread(s, e));
                s = e;
                temp.setName(Integer.toString(i));
                threads.add(temp);
                temp.start();
            }
            for (Thread i : threads) {
                try {
                    i.join();
                } catch (InterruptedException exe) {
                    System.out.println("Error with joining");
                }
            }
        }
        catch (IOException e) {
        System.out.println("Error");
        }
        db.updateDB("Globals","key","counter","value",numberOfLinks);
    }

    public void crawl(String url)
    {
        Document doc=null;
        synchronized (db)
        {
            if(numberOfLinks>109)
                return;
            doc =request(url);
            if(doc==null)
                return;
            String C_String = CS.String_Compact(doc);
            if (db.isExists("URLs", "CompactString", C_String)) {
                return;
            }
            ArrayList<String>keys=new ArrayList<String>();
            ArrayList<Object>values=new ArrayList<Object>();
            keys.add("url");values.add(url);
            keys.add("id");values.add(numberOfLinks);
            keys.add("CompactString");values.add(C_String);
            db.insertToDB("URLs",keys,values);
            numberOfLinks++;

        }

        Elements childrenLinks=doc.select("a[href]");
        for(Element child:childrenLinks)
        {
            String childLink=child.absUrl("href");
            crawl(childLink);
        }

    }
    public Document request(String url)
    {
        try {
            if (db.isExists("URLs", "url", url))
                return null;
            Connection connect= Jsoup.connect(url);
            Document doc=connect.get();
            if(connect.response().statusCode()==200)
            {
                return doc;
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

}

