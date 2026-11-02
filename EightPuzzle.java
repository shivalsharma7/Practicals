
import java.util.*;

public class EightPuzzle {

    static String goal = "123456780";

    // Manhattan Distance Heuristic
    static int heuristic(String state) {
        int distance = 0;

        for (int i = 0; i < 9; i++) {
            char ch = state.charAt(i);

            if (ch != '0') {
                int value = ch - '1';

                int currentRow = i / 3;
                int currentCol = i % 3;

                int goalRow = value / 3;
                int goalCol = value % 3;

                distance += Math.abs(currentRow - goalRow)
                          + Math.abs(currentCol - goalCol);
            }
        }
        return distance;
    }

    static class Node {
        String state;
        int g, h;
        Node parent;

        Node(String state, int g, Node parent) {
            this.state = state;
            this.g = g;
            this.h = heuristic(state);
            this.parent = parent;
        }

        int f() {
            return g + h;
        }
    }

    static void solve(String start) {

        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.f(), b.f())
        );

        HashMap<String, Integer> visited = new HashMap<>();

        pq.add(new Node(start, 0, null));
        visited.put(start, 0);

        int[] row = {-1, 1, 0, 0};
        int[] col = {0, 0, -1, 1};

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            if (current.g != visited.get(current.state))
                continue;

            if (current.state.equals(goal)) {
                printSolution(current);
                return;
            }

            int pos = current.state.indexOf('0');
            int r = pos / 3;
            int c = pos % 3;

            for (int i = 0; i < 4; i++) {

                int nr = r + row[i];
                int nc = c + col[i];

                if (nr >= 0 && nr < 3 && nc >= 0 && nc < 3) {

                    int newPos = nr * 3 + nc;

                    char[] arr = current.state.toCharArray();

                    arr[pos] = arr[newPos];
                    arr[newPos] = '0';

                    String newState = new String(arr);
                    int newG = current.g + 1;

                    if (!visited.containsKey(newState) ||
                        newG < visited.get(newState)) {

                        visited.put(newState, newG);

                        pq.add(new Node(newState, newG, current));
                    }
                }
            }
        }

        System.out.println("No solution found!");
    }

    static void printSolution(Node node) {

        Stack<Node> path = new Stack<>();

        while (node != null) {
            path.push(node);
            node = node.parent;
        }

        System.out.println("Solution:");

        while (!path.isEmpty()) {
            String s = path.pop().state;

            for (int i = 0; i < 9; i++) {
                System.out.print(s.charAt(i) + " ");

                if (i % 3 == 2)
                    System.out.println();
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 8 Puzzle (use 0 for blank):");

        String start = "";

        for (int i = 0; i < 9; i++) {
            start += sc.next();
        }

        solve(start);

        sc.close();
    }
}
