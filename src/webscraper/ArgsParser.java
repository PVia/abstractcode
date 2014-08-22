package webscraper;

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

public class ArgsParser {
    private boolean isNeedNumberWords;
    private boolean isNeedNumberChars;
    private boolean isNeedSentences;
    private boolean isNeedTime;
    public ArgsParser(String[] args) {
        for (int i = 2; i < args.length; i++) {
            switch (args[i]) {
                case "-v":
                    isNeedTime = true;
                    break;
                case "-w":
                    isNeedNumberWords = true;
                    break;
                case "-c":
                    isNeedNumberChars = true;
                    break;
                case "-e":
                    isNeedSentences = true;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public boolean isNeedNumberWords() {
        return isNeedNumberWords;
    }

    public boolean isNeedNumberChars() {
        return isNeedNumberChars;
    }

    public boolean isNeedSentences() {
        return isNeedSentences;
    }

    public boolean isNeedTime() {
        return isNeedTime;
    }

}
