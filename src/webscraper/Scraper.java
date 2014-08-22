package webscraper;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;



public class Scraper {

    private static ArgsParser argsParser; // is used to check args
    private static int totalNumChars; //is used for total report
    private static ArrayList<String> totalSentences = new ArrayList<>(); // contains all sentences from all URLs

    public static void main(String[] args) {
        long startTime = new Date().getTime();

        try {

            ArrayList<String> urlList = new ArrayList<>(); // contains list of urls to scan
            if (args[0].matches(".*\\.txt")) {
                urlList = getUrls(args[0]);
            }
            else
                urlList.add(args[0]);
            String[] words = args[1].split(",");
            int[] totalCountWords = new int[words.length];

            // loop for every URL
            for (String siteName : urlList) {

                int[] countWords;
                long startOneTime = new Date().getTime();
                argsParser = new ArgsParser(args);
                UrlParser urlParser = new UrlParser(siteName);
                String source = urlParser.getResultString();
                WordsFinder wordsFinder = new WordsFinder(source, words);
                System.out.println("Result of scraping for " + siteName + ":");

                //making of report starts
                ReportMaker report = new ReportMaker();
                if (argsParser.isNeedNumberWords()) {
                    countWords = wordsFinder.getNumWords();
                    report.addNumWords(words, countWords);
                    for (int i = 0; i < countWords.length; i++) {
                        totalCountWords[i] += countWords[i];
                    }

                }
                if (argsParser.isNeedNumberChars()) {
                    int numChars = source.length();
                    report.addNumChars(numChars);
                    totalNumChars += numChars;
                }
                if (argsParser.isNeedSentences()) {
                    ArrayList<String> sentences = wordsFinder.getSentences();
                    report.addSentences(sentences);
                    totalSentences.addAll(sentences);

                }
                if (argsParser.isNeedTime()) {
                    long timeSpent = new Date().getTime() - startOneTime;
                    report.addTime(timeSpent);
                }

                System.out.println(report.getReport());
            }

            //making total report
            if (urlList.size() > 1) {
                System.out.println("Total result of scraping:");
                ReportMaker report = new ReportMaker();
                if (argsParser.isNeedNumberWords()) {
                    report.addNumWords(words, totalCountWords);
                }
                if (argsParser.isNeedNumberChars())
                    report.addNumChars(totalNumChars);
                if (argsParser.isNeedSentences()) {
                    report.addSentences(totalSentences);
                }
                if (argsParser.isNeedTime()) {
                    long timeSpent = new Date().getTime() - startTime;
                    report.addTime(timeSpent);
                }

                System.out.println(report.getReport());
            }

        }
        catch (Exception e) {
            System.out.print("Something went wrong: ");
            System.out.println(e.toString());
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
