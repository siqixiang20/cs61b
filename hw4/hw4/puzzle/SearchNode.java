package hw4.puzzle;

public class SearchNode implements Comparable<SearchNode> {

    public WorldState worldState;
    public int moves;
    public SearchNode prev;

    public SearchNode(WorldState ws, int moves, SearchNode prevNode) {
        this.worldState = ws;
        this.moves = moves;
        this.prev = prevNode;
    }

    @Override
    public int compareTo(SearchNode o) {
        int this_len = this.worldState.estimatedDistanceToGoal() + this.moves;
        int object_len = o.worldState.estimatedDistanceToGoal() + o.moves;
        if (this_len > object_len) {
            return 1;
        } else if (this_len < object_len) {
            return -1;
        }
        return 0;
    }
}
