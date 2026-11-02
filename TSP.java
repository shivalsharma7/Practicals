
import java.util.*;

public class TSP {

    static int[][] dist = {
        {0, 10, 15, 20, 10},
        {10, 0, 35, 25, 17},
        {15, 35, 0, 30, 28},
        {20, 25, 30, 0, 12},
        {10, 17, 28, 12, 0}
    };

    static int cost(int[] path) {
        int sum = 0;

        for (int i = 0; i < path.length; i++) {
            sum += dist[path[i]][path[(i + 1) % path.length]];
        }

        return sum;
    }

    static void solve() {

        int k = 3;

        ArrayList<int[]> beam = new ArrayList<>();

        beam.add(new int[]{0, 1, 2, 3, 4});
        beam.add(new int[]{0, 1, 3, 2, 4});
        beam.add(new int[]{0, 2, 1, 4, 3});

        int[] best = beam.get(0).clone();

        for (int step = 0; step < 100; step++) {

            ArrayList<int[]> next = new ArrayList<>();
            HashSet<String> visited = new HashSet<>();

            for (int[] route : beam) {

                for (int i = 1; i < 5; i++) {
                    for (int j = i + 1; j < 5; j++) {

                        int[] newRoute = route.clone();

                        int temp = newRoute[i];
                        newRoute[i] = newRoute[j];
                        newRoute[j] = temp;

                        String key = Arrays.toString(newRoute);

                        if (visited.add(key))
                            next.add(newRoute);
                    }
                }
            }

            next.sort((a, b) ->
                Integer.compare(cost(a), cost(b)));

            beam = new ArrayList<>(next.subList(0, Math.min(k, next.size())));

            if (cost(beam.get(0)) < cost(best))
                best = beam.get(0).clone();
        }

        System.out.println("Best Route:");

        for (int city : best)
            System.out.print((char)('A' + city) + " -> ");

        System.out.println("A");
        System.out.println("Minimum Distance: " + cost(best));
    }

    public static void main(String[] args) {
        solve();
    }
}
