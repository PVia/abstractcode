package webscraper;

import java.util.ArrayList;

/*
Class makes String report
 */

public class ReportMaker {
    private String report = "";
    public ReportMaker() {

    }

    public void addNumWords(String[] words, int[] countWords) {
        for (int i = 0; i < words.length; i++) {
            report += String.format("Number of words \"%s\": %d.%n", words[i], countWords[i]);
        }
    }
    public void addNumChars(int numChars) {
        report += String.format("Number of chars in source code is: %d.%n", numChars);
    }
    public void addSentences(ArrayList<String> sentences) {
        report += String.format("List of sentences:%n");
        for (String s : sentences) {
            report += String.format(" - %s%n", s);
        }
    }
    public void addTime(long timeSpent) {
        report += String.format("Time spent on data scraping and data processing: %d ms.%n", timeSpent);
    }

    public String getReport() {
        return report;
    }
}
