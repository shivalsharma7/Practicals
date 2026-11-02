
import java.util.*;

public class BFS8Puzzle {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number from 0 to 8");
        String start = sc.next();
        String goal = "123456780";

        Queue<String> q = new LinkedList<>();
        HashMap<String, String> parent = new HashMap<>();

        q.add(start);   
        parent.put(start, null);

        int[] moves = {-3, 3, -1, 1};

        while (!q.isEmpty()) {
            String s = q.poll();

            if (s.equals(goal)) {
                Stack<String> path = new Stack<>();

                while (s != null) {
                    path.push(s);
                    s = parent.get(s);
                }

                while (!path.isEmpty()) {
                    String state = path.pop();

                    for (int i = 0; i < 9; i++) {
                        if (state.charAt(i) == '0')
                            System.out.print("_ ");
                        else
                            System.out.print(state.charAt(i) + " ");

                        if (i % 3 == 2) System.out.println();
                    }
                    System.out.println("-----");
                }
                return;
            }

            int pos = s.indexOf('0');

            for (int m : moves) {
                int next = pos + m;

                if (next < 0 || next > 8 ||
                    (m == -1 && pos % 3 == 0) ||
                    (m == 1 && pos % 3 == 2))
                    continue;

                char[] a = s.toCharArray();
                a[pos] = a[next];
                a[next] = '0';

                String ns = new String(a);

                if (!parent.containsKey(ns)) {
                    parent.put(ns, s);
                    q.add(ns);
                }
            }
        }

        System.out.println("No solution!");
        sc.close();
    }
}
