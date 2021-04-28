import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {

    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));

        assertFalse(palindrome.isPalindrome("rancor"));
    }

    @Test
    public void testIsPalindromeWithCharacterComparator() {
        CharacterComparator cc = new OffByOne();

        assertTrue(palindrome.isPalindrome("flake", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("", cc));

        assertFalse(palindrome.isPalindrome("dog", cc));
    }
}
