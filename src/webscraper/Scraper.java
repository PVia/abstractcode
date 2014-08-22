package webscraper;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;



/*
Create console web scraper (http://en.wikipedia.org/wiki/Web_scraping) utility which:

-          accepts as command line parameters:
o   (must) web resources URL or path to plain text file containing a list of URLs
o   (must) data command(s)
o   (must) word (or list of words with “,” delimiter)
o   (nice to have) output verbosity flag,  if on then the output should contains information about time spend on data scraping and data processing (-v)

-          supports the following data processing commands:
o   (must) count number of provided word(s) occurrence on webpage(s). (-w)
o   (must) count number of characters of each web page (-c)
o   (nice to have) extract sentences’ which contain given words (-e)

Data processing results should be printed to output for each web resources separately and for all resources as total.

Command line parameters example for Java implementation:
java –jar scraper.jar http://www.cnn.com Greece,default –v –w –c –e
 */
public class Scraper {
    public static void main(String[] args) {
        long startTime = new Date().getTime();
        String[] params = {"http://www.hopetech.com/about-us/", "machines,product", "-v", "-w", "-c", "-e"};
        try {
            ArrayList<String> urlList = new ArrayList<>();
            if (params[0].matches(".*\\.txt"))
                urlList = getUrls(params[0]);
            else
                urlList.add(params[0]);
            for (String siteName : urlList) {
                String[] words = params[1].split(",");
                int[] countWords;

                ArgsParser argsParser = new ArgsParser(params);
                UrlParser urlParser = new UrlParser(siteName);
                String source = urlParser.getResultString();
                WordsFinder wordsFinder = new WordsFinder(source, words);
                System.out.println("Result of scraping for " + siteName + ":");
                if (argsParser.isNeedNumberWords()) {
                    countWords = wordsFinder.getNumWords();
                    for (int i = 0; i < words.length; i++) {
                        System.out.println("Number of words \"" + words[i] + "\": " + countWords[i]);
                    }
                }
                if (argsParser.isNeedNumberChars())
                    System.out.println("Number of chars in source code is: " + source.length());
                if (argsParser.isNeedSentences()) {
                    System.out.println("List of sentences:");
                    for (String s : wordsFinder.getSentences()) {
                        System.out.println(" - " + s);
                    }
                }
                if (argsParser.isNeedTime()) {
                    long timeSpent = new Date().getTime() - startTime;
                    System.out.println("Time spent on data scraping and data processing: " + timeSpent + " ms");
                }
            }

        }
        catch (IOException e) {
            System.out.println("Error: IOException");
        }


    }

    //creates list with urls from file of urls on separate strings
    public static ArrayList<String> getUrls(String fileName) throws IOException{
        ArrayList<String> urlList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        while (reader.ready()) {
            urlList.add(reader.readLine().trim());
        }
        return urlList;
    }




}
