package hw4.puzzle;

import edu.princeton.cs.algs4.*;

public class Solver {
    int miniMoves = 0;
    MinPQ<SearchNode> path = new MinPQ<>();
    Stack<WorldState> solution = new Stack<>();

    public Solver(WorldState initial) {

        SearchNode currentNode = new SearchNode(initial, miniMoves, null);

        path.insert(currentNode);

        while (true) {
            currentNode = path.delMin();
            solution.push(currentNode.worldState);
            if (currentNode.worldState.isGoal()) {
                break;
            }
            miniMoves += 1;
            for (WorldState neighbor: currentNode.worldState.neighbors()) {
                if (!neighbor.equals(currentNode.worldState)) {
                    SearchNode newNode = new SearchNode(neighbor, miniMoves, currentNode);
                    path.insert(newNode);
                }
            }
        }

    }

    public int moves() {return miniMoves;}
    public Iterable<WorldState> solution() {return solution;}

    public static void main(String[] args) {
        String start = "cube";
        String goal = "tubes";

        Word startState = new Word(start, goal);
        Solver solver = new Solver(startState);

        StdOut.println("Minimum number of moves = " + solver.moves());
        for (WorldState ws : solver.solution()) {
            StdOut.println(ws);
        }
    }
}
