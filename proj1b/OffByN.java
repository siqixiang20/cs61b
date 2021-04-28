public class OffByN implements CharacterComparator {
    private final int n;
    public OffByN(int N) {
        n = N;
    }

    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == n || diff == -n;
    }
}
