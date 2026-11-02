import java.util.*;

public class TowerOfHanoi {

    static void solve(int n) {

        String start = "A".repeat(n);
        String goal = "C".repeat(n);

        Queue<String> q = new LinkedList<>();
        HashMap<String, String> path = new HashMap<>();

        q.add(start);
        path.put(start, "");

        while (!q.isEmpty()) {

            String s = q.poll();

            if (s.equals(goal)) {
                System.out.println(path.get(s));
                return;
            }

            int[] top = {-1, -1, -1};

            for (int i = 0; i < n; i++) {
                int rod = s.charAt(i) - 'A';
                if (top[rod] == -1)
                    top[rod] = i;
            }

            for (int from = 0; from < 3; from++) {
                int d = top[from];

                if (d == -1) continue;

                for (int to = 0; to < 3; to++) {

                    if (from == to) continue;

                    if (top[to] == -1 || top[to] > d) {

                        char[] arr = s.toCharArray();
                        arr[d] = (char)('A' + to);

                        String next = new String(arr);

                        if (!path.containsKey(next)) {

                            String move = "Move disk " + (d + 1)
                                + " from " + (char)('A' + from)
                                + " to " + (char)('A' + to);

                            path.put(next, path.get(s) + move + "\n");
                            q.add(next);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of disks: ");
        int n = sc.nextInt();

        solve(n);
    }
}
