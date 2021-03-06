import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('b', 'a'));
        assertFalse(offByOne.equalChars('f', 'j'));
        assertTrue(offByOne.equalChars('K', 'L'));
        assertFalse(offByOne.equalChars('A', 'b'));
    }
}
