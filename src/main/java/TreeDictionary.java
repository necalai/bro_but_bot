import java.util.HashMap;
import java.util.Map;

public class TreeDictionary {
    private Entry root = new Entry();

    public Entry getRoot() {
        return root;
    }

    public void add(String word) {
        char[] wordLetters = word.toCharArray();
        Entry currentNode = root;

        for (char letter : wordLetters) {
            if (!currentNode.letters.containsKey(letter)) {
                currentNode.letters.put(letter, currentNode = new Entry());
            } else {
                currentNode = currentNode.letters.get(letter);
            }
        }
    }

    public boolean find(String word) {
        char[] wordLetters = word.toCharArray();
        Entry currentNode = root;

        for (char letter : wordLetters) {
            if (!currentNode.letters.containsKey(letter)) {
                return false;
            } else {
                currentNode = currentNode.letters.get(letter);
            }
        }

        return true;
    }

    private class Entry {
        Map<Character, Entry> letters = new HashMap<>();

        @Override
        public String toString() {
            return letters.toString();
        }
    }
}
