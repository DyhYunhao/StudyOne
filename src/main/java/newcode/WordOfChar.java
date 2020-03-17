package newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class WordOfChar {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.puzzlers.org/pub/wordlists/unixdict.txt");
        InputStreamReader in = new InputStreamReader(url.openStream());
        BufferedReader reader = new BufferedReader(in);

        Map<String, Collection<String>> ana = new HashMap<>();
        String word;
        int count = 0;
        while ((word = reader.readLine()) != null){
//            System.out.println(word);
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!ana.containsKey(key)) {
                ana.put(key, new ArrayList<String>());
            }
            ana.get(key).add(word);
            count = Math.max(count, ana.size());
        }
        reader.close();

        for (Collection<String> a: ana.values()){
            if (a.size() >= count) {
                System.out.println(ana.toString());
            }
        }
    }

}
