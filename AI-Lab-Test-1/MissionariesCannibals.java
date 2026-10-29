import java.util.*;

/**
 * Missionaries and Cannibals using Breadth-First Search (BFS).
 * State stores people on the left bank and boat location.
 */
public class MissionariesCannibals {
    static class State {
        int missionariesLeft, cannibalsLeft;
        boolean boatLeft;
        State parent;
        String action;

        State(int m, int c, boolean boatLeft, State parent, String action) {
            this.missionariesLeft = m;
            this.cannibalsLeft = c;
            this.boatLeft = boatLeft;
            this.parent = parent;
            this.action = action;
        }

        String key() {
            return missionariesLeft + "," + cannibalsLeft + "," + boatLeft;
        }
    }

    static boolean isSafe(int mLeft, int cLeft) {
        int mRight = 3 - mLeft;
        int cRight = 3 - cLeft;

        if (mLeft < 0 || mLeft > 3 || cLeft < 0 || cLeft > 3) {
            return false;
        }

        // Cannibals cannot outnumber missionaries on either bank
        // when at least one missionary is present.
        boolean leftSafe = (mLeft == 0 || mLeft >= cLeft);
        boolean rightSafe = (mRight == 0 || mRight >= cRight);
        return leftSafe && rightSafe;
    }

    public static void main(String[] args) {
        int[][] moves = {
            {1, 0}, // 1 missionary
            {2, 0}, // 2 missionaries
            {0, 1}, // 1 cannibal
            {0, 2}, // 2 cannibals
            {1, 1}  // 1 missionary and 1 cannibal
        };

        State start = new State(3, 3, true, null, "Start: everyone is on the left bank");
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(start);
        visited.add(start.key());

        State goal = null;

        while (!queue.isEmpty()) {
            State current = queue.remove();

            if (current.missionariesLeft == 0 && current.cannibalsLeft == 0
                    && !current.boatLeft) {
                goal = current;
                break;
            }

            for (int[] move : moves) {
                int m = move[0];
                int c = move[1];

                int nextM = current.boatLeft
                        ? current.missionariesLeft - m
                        : current.missionariesLeft + m;
                int nextC = current.boatLeft
                        ? current.cannibalsLeft - c
                        : current.cannibalsLeft + c;

                // A move must carry one or two people available on the boat's bank.
                int people = m + c;
                if (people < 1 || people > 2) {
                    continue;
                }
                if (current.boatLeft && (m > current.missionariesLeft || c > current.cannibalsLeft)) {
                    continue;
                }
                int mRight = 3 - current.missionariesLeft;
                int cRight = 3 - current.cannibalsLeft;
                if (!current.boatLeft && (m > mRight || c > cRight)) {
                    continue;
                }

                if (!isSafe(nextM, nextC)) {
                    continue;
                }

                boolean nextBoatLeft = !current.boatLeft;
                String direction = current.boatLeft ? "Left -> Right" : "Right -> Left";
                String action = "Move " + describe(m, c) + " (" + direction + ")";
                State next = new State(nextM, nextC, nextBoatLeft, current, action);

                if (!visited.contains(next.key())) {
                    visited.add(next.key());
                    queue.add(next);
                }
            }
        }

        if (goal == null) {
            System.out.println("No solution found.");
            return;
        }

        List<State> path = new ArrayList<>();
        for (State state = goal; state != null; state = state.parent) {
            path.add(state);
        }
        Collections.reverse(path);

        System.out.println("Missionaries and Cannibals Solution:");
        for (int i = 0; i < path.size(); i++) {
            State state = path.get(i);
            int mRight = 3 - state.missionariesLeft;
            int cRight = 3 - state.cannibalsLeft;
            String boat = state.boatLeft ? "Left bank" : "Right bank";
            System.out.printf("Step %d: %s | Left: M=%d C=%d | Right: M=%d C=%d | Boat: %s%n",
                    i, state.action, state.missionariesLeft, state.cannibalsLeft,
                    mRight, cRight, boat);
        }
        System.out.println("All missionaries and cannibals crossed safely.");
    }

    static String describe(int m, int c) {
        List<String> people = new ArrayList<>();
        if (m > 0) people.add(m + (m == 1 ? " missionary" : " missionaries"));
        if (c > 0) people.add(c + (c == 1 ? " cannibal" : " cannibals"));
        return String.join(" and ", people);
    }
}
