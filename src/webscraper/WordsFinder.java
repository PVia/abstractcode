package webscraper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WordsFinder {
    private String[] words;
    private String resource;

    //constructor delete tags and comments
    public WordsFinder(String resource, String[] words) {
        this.words = words;
        resource = resource.replaceAll("<!--[\\s\\S]*?-->", "");
        resource = resource.replaceAll("<head[\\s\\S]*?/head>", "");
        resource = resource.replaceAll("<script[\\s\\S]*?/script>", "");
        resource = resource.replaceAll("<style[\\s\\S]*?/style>", "");
        resource = resource.replaceAll("</?span*?>", "");
        this.resource = resource.replaceAll("<[\\s\\S]*?>", " . ");
    }

    // returns number of words in web source without words from head-section and from tags
    public int[] getNumWords() {

        int[] result = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            Pattern pattern = Pattern.compile("(?<!\\w)(?i)" + words[i] + "(?!\\w)");
            Matcher matcher = pattern.matcher(resource);
            while (matcher.find()) {
                result[i]++;
            }
        }
        return result;
    }
    //find sentences with given words and adds it to list without repetition
    public ArrayList<String> getSentences() {
        ArrayList<String> listWords = new ArrayList<>();
        for (String word: words) {
            Pattern pattern = Pattern.compile("[^.!?\\s][^.!?\\n\\r]*(?<!\\w)(?i)" + word +"(?-i)(?!\\w)[^.!?\\n\\r]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)");
            Matcher matcher = pattern.matcher(resource);
            while (matcher.find()) {
                String result = matcher.group();
                if (!listWords.contains(result))
                    listWords.add(result);
            }
        }
        return listWords;
    }


}
