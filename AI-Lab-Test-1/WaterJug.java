import java.util.*;

/**
 * Water Jug Problem using Breadth-First Search (BFS).
 * State: (amount in jug A, amount in jug B).
 */
public class WaterJug {
    static class State {
        int a, b;
        State parent;
        String action;

        State(int a, int b, State parent, String action) {
            this.a = a;
            this.b = b;
            this.parent = parent;
            this.action = action;
        }

        String key() {
            return a + "," + b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter capacity of Jug A: ");
        int capA = sc.nextInt();
        System.out.print("Enter capacity of Jug B: ");
        int capB = sc.nextInt();
        System.out.print("Enter target amount: ");
        int target = sc.nextInt();

        if (capA <= 0 || capB <= 0 || target < 0 || target > Math.max(capA, capB)) {
            System.out.println("Invalid input. Capacities must be positive and target must be between 0 and the larger capacity.");
            return;
        }

        State start = new State(0, 0, null, "Start: both jugs are empty");
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(start);
        visited.add(start.key());

        State goal = null;

        while (!queue.isEmpty()) {
            State current = queue.remove();

            if (current.a == target || current.b == target) {
                goal = current;
                break;
            }

            int[][] nextAmounts = {
                {capA, current.b}, // Fill A
                {current.a, capB}, // Fill B
                {0, current.b},    // Empty A
                {current.a, 0},    // Empty B
                {current.a - Math.min(current.a, capB - current.b),
                 current.b + Math.min(current.a, capB - current.b)}, // Pour A -> B
                {current.a + Math.min(current.b, capA - current.a),
                 current.b - Math.min(current.b, capA - current.a)}  // Pour B -> A
            };

            String[] actions = {
                "Fill Jug A",
                "Fill Jug B",
                "Empty Jug A",
                "Empty Jug B",
                "Pour water from Jug A into Jug B",
                "Pour water from Jug B into Jug A"
            };

            for (int i = 0; i < nextAmounts.length; i++) {
                int nextA = nextAmounts[i][0];
                int nextB = nextAmounts[i][1];
                State next = new State(nextA, nextB, current, actions[i]);

                if (!visited.contains(next.key())) {
                    visited.add(next.key());
                    queue.add(next);
                }
            }
        }

        if (target == 0) {
            goal = start;
        }

        if (goal == null) {
            System.out.println("No solution found for this target with the given jug capacities.");
            System.out.println("Hint: a target is possible only if it is not greater than either jug's capacity and is divisible by gcd(capacity A, capacity B).");
            return;
        }

        List<State> path = new ArrayList<>();
        for (State state = goal; state != null; state = state.parent) {
            path.add(state);
        }
        Collections.reverse(path);

        System.out.println("\nSolution:");
        for (int i = 0; i < path.size(); i++) {
            State state = path.get(i);
            System.out.printf("Step %d: %s -> Jug A = %d, Jug B = %d%n",
                    i, state.action, state.a, state.b);
        }
        System.out.println("Target reached: " + target + " litre(s).");
    }
}
