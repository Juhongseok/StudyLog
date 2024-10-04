package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GroupWordChecker {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());

        int result = 0;
        for (int i = 0; i < count; i++) {
            boolean checked = true;
            Set<String> set = new HashSet<>();
            String word = br.readLine();
            String[] words = word.split("");

            set.add(words[0]);
            String beforeWord = words[0];
            for (int j = 1; j < words.length; j++) {
                if (beforeWord.equals(words[j])) {
                    continue;
                }

                if (set.contains(words[j])) {
                    checked = false;
                }

                set.add(words[j]);
                beforeWord = words[j];
            }

            if (checked) {
                result++;
            }
        }

        System.out.println(result);
    }

}
