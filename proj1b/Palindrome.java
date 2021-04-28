public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            Character newChar = word.charAt(i);
            wordDeque.addLast(newChar);
        }
        return wordDeque;
    }

    private boolean isPalindromeHelper(Deque<Character> wordDeque) {
        if (wordDeque.size() <= 1) {
            return true;
        }

        Character first = wordDeque.removeFirst();
        Character last = wordDeque.removeLast();

        return first == last && isPalindromeHelper(wordDeque);
    }

    public boolean isPalindrome(String word) {
        Deque<Character> testDeque = wordToDeque(word);

        return isPalindromeHelper(testDeque);
    }

    private boolean isPalindromeHelper(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() <= 1) {
            return true;
        }

        Character first = wordDeque.removeFirst();
        Character last = wordDeque.removeLast();

        return cc.equalChars(first, last) && isPalindromeHelper(wordDeque, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> testDeque = wordToDeque(word);

        return isPalindromeHelper(testDeque, cc);
    }

}
