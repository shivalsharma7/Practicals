
import java.util.*;

public class DFS {

    static int A = 4, B = 3, target = 2;

    static HashSet<String> visited = new HashSet<>();
    static ArrayList<String> path = new ArrayList<>();

    static boolean dfs(int a, int b) {

        String state = a + "," + b;

        if (visited.contains(state))
            return false;

        visited.add(state);
        path.add("(" + a + ", " + b + ")");

        if (a == target || b == target)
            return true;

        int x = Math.min(a, B - b);
        int y = Math.min(b, A - a);

        int[][] next = {
            {A, b},       // Fill Jug A
            {a, B},       // Fill Jug B
            {0, b},       // Empty Jug A
            {a, 0},       // Empty Jug B
            {a - x, b + x}, // Pour A into B
            {a + y, b - y}  // Pour B into A
        };

        for (int[] s : next) {
            if (dfs(s[0], s[1]))
                return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {

        if (dfs(0, 0)) {
            System.out.println("Solution Path:");

            for (int i = 0; i < path.size(); i++)
                System.out.println("State " + (i + 1) + ": " + path.get(i));

            System.out.println("Goal Reached!");
        }
        else {
            System.out.println("No solution found!");
        }
    }
}
