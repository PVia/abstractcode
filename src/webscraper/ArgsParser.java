package webscraper;

/*
methods of class returns true if some event is needed

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
