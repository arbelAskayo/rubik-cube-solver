import java.util.*;
//HW01_208642868_207850074

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CubeSolver solver = new CubeSolver();

        // Prompt user for initial state input
        System.out.println("Enter the initial state of the cube (For example: YGGWORRRYBGYOBYBGOOWWRWB):");
        String input = scanner.nextLine().trim();
        String[] initialStateArray = input.split("");

        // Check if the input is valid
        if (initialStateArray.length != 24) {
            System.out.println("Invalid input. The state must consist of 24 elements.");
            return;
        }

        // Create the initial state
        State initialState = new State(initialStateArray, null, null, 0);

        // Ask the user to choose the algorithm
        System.out.println("Choose the algorithm: A* (A) or Iterative Deepening Search (I) or Uniform Cost Search (U) :");
        String choice = scanner.nextLine().trim().toUpperCase();

        CubeSolver.Solution solution = null;
        switch (choice) {
            case "A":
                solution = solver.aStarSearch(initialState);
                break;
            case "I":
                solution = solver.iterativeDeepeningSearch(initialState);
                break;
            case "U":
                solution = solver.uniformCostSearch(initialState);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        // Print the solution path
        if (solution != null && !solution.getPath().isEmpty()) {
            System.out.println("Solution path:");
            for (String[] faces : solution.getPath()) {
                System.out.println(Arrays.toString(faces));
            }

            // Print the number of visited vertices
            System.out.println("Visited vertices: " + solution.getVisitedVertices());
        } else {
            System.out.println("No solution found.");
        }
    }
}
class CubeSolver {
    private int visitedVertices = 0;
    //check whether we reach the goal or not
    public static boolean isGoal(State state) {
        String[] faces = state.getFaces();
        for (int i = 0; i < faces.length; i += 4) {
            String color = faces[i];
            for (int j = 1; j < 4; j++) {
                if (!faces[i + j].equals(color)) {
                    return false;
                }
            }
        }
        return true;
    }
    //the successor func which return the possible moves as a list
    public static List<State> successorFunction(State state) {
        String[] moves = {"F", "R", "U", "B", "L", "D", "F'", "R'", "U'", "B'", "L'", "D'"};
        List<State> successors = new ArrayList<>();

        for (String move : moves) {
            successors.add(applyMove(state, move));
        }

        return successors;
    }
    //the function that responsible on the moves and on saving the path to the next step
    public static State applyMove(State state, String move){
        String[] faces = state.getFaces();
        String[] newFaces = faces.clone();
        if(move== "F"){
            newFaces[0] = faces[0];
            newFaces[1] = faces[1];
            newFaces[2] = faces[19];
            newFaces[3] = faces[17];
            newFaces[4] = faces[2];
            newFaces[5] = faces[5];
            newFaces[6] = faces[3];
            newFaces[7] = faces[7];
            newFaces[8] = faces[10];
            newFaces[9] = faces[8];
            newFaces[10] = faces[11];
            newFaces[11] = faces[9];
            newFaces[12] = faces[6];
            newFaces[13] = faces[4];
            newFaces[14] = faces[14];
            newFaces[15] = faces[15];
            newFaces[16] = faces[16];
            newFaces[17] = faces[12];
            newFaces[18] = faces[18];
            newFaces[19] = faces[13];
            newFaces[20] = faces[20];
            newFaces[21] = faces[21];
            newFaces[22] = faces[22];
            newFaces[23] = faces[23];
        }
        else if (move=="F'"){
            newFaces[0] = faces[0];
            newFaces[1] = faces[1];
            newFaces[2] = faces[4];
            newFaces[3] = faces[6];
            newFaces[4] = faces[13];
            newFaces[5] = faces[5];
            newFaces[6] = faces[12];
            newFaces[7] = faces[7];
            newFaces[8] = faces[9];
            newFaces[9] = faces[11];
            newFaces[10] = faces[8];
            newFaces[11] = faces[10];
            newFaces[12] = faces[17];
            newFaces[13] = faces[19];
            newFaces[14] = faces[14];
            newFaces[15] = faces[15];
            newFaces[16] = faces[16];
            newFaces[17] = faces[3];
            newFaces[18] = faces[18];
            newFaces[19] = faces[2];
            newFaces[20] = faces[20];
            newFaces[21] = faces[21];
            newFaces[22] = faces[22];
            newFaces[23] = faces[23];
        }
        else if (move=="R"){
            newFaces[0] = faces[0];
            newFaces[1] = faces[9];
            newFaces[2] = faces[2];
            newFaces[3] = faces[11];
            newFaces[4] = faces[6];
            newFaces[5] = faces[4];
            newFaces[6] = faces[7];
            newFaces[7] = faces[5];
            newFaces[8] = faces[8];
            newFaces[9] = faces[13];
            newFaces[10] = faces[10];
            newFaces[11] = faces[15];
            newFaces[12] = faces[12];
            newFaces[13] = faces[22];
            newFaces[14] = faces[14];
            newFaces[15] = faces[20];
            newFaces[16] = faces[16];
            newFaces[17] = faces[17];
            newFaces[18] = faces[18];
            newFaces[19] = faces[19];
            newFaces[20] = faces[3];
            newFaces[21] = faces[21];
            newFaces[22] = faces[1];
            newFaces[23] = faces[23];
        }
        else if (move=="R'"){
            newFaces[0] = faces[0];
            newFaces[1] = faces[22];
            newFaces[2] = faces[2];
            newFaces[3] = faces[20];
            newFaces[4] = faces[5];
            newFaces[5] = faces[7];
            newFaces[6] = faces[4];
            newFaces[7] = faces[6];
            newFaces[8] = faces[8];
            newFaces[9] = faces[1];
            newFaces[10] = faces[10];
            newFaces[11] = faces[3];
            newFaces[12] = faces[12];
            newFaces[13] = faces[9];
            newFaces[14] = faces[14];
            newFaces[15] = faces[11];
            newFaces[16] = faces[16];
            newFaces[17] = faces[17];
            newFaces[18] = faces[18];
            newFaces[19] = faces[19];
            newFaces[20] = faces[15];
            newFaces[21] = faces[21];
            newFaces[22] = faces[13];
            newFaces[23] = faces[23];
        }
        else if (move=="U"){
            newFaces[0] = faces[2];
            newFaces[1] = faces[0];
            newFaces[2] = faces[3];
            newFaces[3] = faces[1];
            newFaces[4] = faces[20];
            newFaces[5] = faces[21];
            newFaces[6] = faces[6];
            newFaces[7] = faces[7];
            newFaces[8] = faces[4];
            newFaces[9] = faces[5];
            newFaces[10] = faces[10];
            newFaces[11] = faces[11];
            newFaces[12] = faces[12];
            newFaces[13] = faces[13];
            newFaces[14] = faces[14];
            newFaces[15] = faces[15];
            newFaces[16] = faces[8];
            newFaces[17] = faces[9];
            newFaces[18] = faces[18];
            newFaces[19] = faces[19];
            newFaces[20] = faces[16];
            newFaces[21] = faces[17];
            newFaces[22] = faces[22];
            newFaces[23] = faces[23];
        }
        else if (move=="U'"){
            newFaces[0] = faces[1];
            newFaces[1] = faces[3];
            newFaces[2] = faces[0];
            newFaces[3] = faces[2];
            newFaces[4] = faces[8];
            newFaces[5] = faces[9];
            newFaces[6] = faces[6];
            newFaces[7] = faces[7];
            newFaces[8] = faces[16];
            newFaces[9] = faces[17];
            newFaces[10] = faces[10];
            newFaces[11] = faces[11];
            newFaces[12] = faces[12];
            newFaces[13] = faces[13];
            newFaces[14] = faces[14];
            newFaces[15] = faces[15];
            newFaces[16] = faces[20];
            newFaces[17] = faces[11];
            newFaces[18] = faces[18];
            newFaces[19] = faces[19];
            newFaces[20] = faces[4];
            newFaces[21] = faces[5];
            newFaces[22] = faces[22];
            newFaces[23] = faces[23];
        }
        else if (move=="B"){
            newFaces[0] = faces[5];
            newFaces[1] = faces[7];
            newFaces[2] = faces[2];
            newFaces[3] = faces[3];
            newFaces[4] = faces[4];
            newFaces[5] = faces[15];
            newFaces[6] = faces[6];
            newFaces[7] = faces[14];
            newFaces[8] = faces[8];
            newFaces[9] = faces[9];
            newFaces[10] = faces[10];
            newFaces[11] = faces[11];
            newFaces[12] = faces[12];
            newFaces[13] = faces[13];
            newFaces[14] = faces[16];
            newFaces[15] = faces[18];
            newFaces[16] = faces[1];
            newFaces[17] = faces[17];
            newFaces[18] = faces[0];
            newFaces[19] = faces[19];
            newFaces[20] = faces[22];
            newFaces[21] = faces[20];
            newFaces[22] = faces[23];
            newFaces[23] = faces[21];
        }
        else if (move=="B'"){
            newFaces[0] = faces[18];
            newFaces[1] = faces[16];
            newFaces[2] = faces[2];
            newFaces[3] = faces[3];
            newFaces[4] = faces[4];
            newFaces[5] = faces[0];
            newFaces[6] = faces[6];
            newFaces[7] = faces[1];
            newFaces[8] = faces[8];
            newFaces[9] = faces[9];
            newFaces[10] = faces[10];
            newFaces[11] = faces[11];
            newFaces[12] = faces[12];
            newFaces[13] = faces[13];
            newFaces[14] = faces[7];
            newFaces[15] = faces[5];
            newFaces[16] = faces[14];
            newFaces[17] = faces[17];
            newFaces[18] = faces[15];
            newFaces[19] = faces[19];
            newFaces[20] = faces[21];
            newFaces[21] = faces[23];
            newFaces[22] = faces[20];
            newFaces[23] = faces[22];
        }
        else if (move=="L"){
            newFaces[0] = faces[23];
            newFaces[1] = faces[1];
            newFaces[2] = faces[21];
            newFaces[3] = faces[3];
            newFaces[4] = faces[4];
            newFaces[5] = faces[5];
            newFaces[6] = faces[6];
            newFaces[7] = faces[7];
            newFaces[8] = faces[0];
            newFaces[9] = faces[9];
            newFaces[10] = faces[2];
            newFaces[11] = faces[11];
            newFaces[12] = faces[8];
            newFaces[13] = faces[13];
            newFaces[14] = faces[10];
            newFaces[15] = faces[15];
            newFaces[16] = faces[18];
            newFaces[17] = faces[16];
            newFaces[18] = faces[19];
            newFaces[19] = faces[17];
            newFaces[20] = faces[20];
            newFaces[21] = faces[14];
            newFaces[22] = faces[22];
            newFaces[23] = faces[12];
        }
        else if (move== "L'"){
            newFaces[0] = faces[8];
            newFaces[1] = faces[1];
            newFaces[2] = faces[10];
            newFaces[3] = faces[3];
            newFaces[4] = faces[4];
            newFaces[5] = faces[5];
            newFaces[6] = faces[6];
            newFaces[7] = faces[7];
            newFaces[8] = faces[12];
            newFaces[9] = faces[9];
            newFaces[10] = faces[14];
            newFaces[11] = faces[11];
            newFaces[12] = faces[23];
            newFaces[13] = faces[13];
            newFaces[14] = faces[21];
            newFaces[15] = faces[15];
            newFaces[16] = faces[17];
            newFaces[17] = faces[19];
            newFaces[18] = faces[16];
            newFaces[19] = faces[18];
            newFaces[20] = faces[20];
            newFaces[21] = faces[2];
            newFaces[22] = faces[22];
            newFaces[23] = faces[0];
        }
        else if (move=="D"){
            newFaces[0] = faces[0];
            newFaces[1] = faces[1];
            newFaces[2] = faces[2];
            newFaces[3] = faces[3];
            newFaces[4] = faces[4];
            newFaces[5] = faces[5];
            newFaces[6] = faces[10];
            newFaces[7] = faces[11];
            newFaces[8] = faces[8];
            newFaces[9] = faces[9];
            newFaces[10] = faces[18];
            newFaces[11] = faces[19];
            newFaces[12] = faces[14];
            newFaces[13] = faces[12];
            newFaces[14] = faces[15];
            newFaces[15] = faces[13];
            newFaces[16] = faces[16];
            newFaces[17] = faces[17];
            newFaces[18] = faces[22];
            newFaces[19] = faces[23];
            newFaces[20] = faces[20];
            newFaces[21] = faces[21];
            newFaces[22] = faces[6];
            newFaces[23] = faces[7];

        }
        else if (move=="D'"){
            newFaces[0] = faces[0];
            newFaces[1] = faces[1];
            newFaces[2] = faces[2];
            newFaces[3] = faces[3];
            newFaces[4] = faces[4];
            newFaces[5] = faces[5];
            newFaces[6] = faces[22];
            newFaces[7] = faces[23];
            newFaces[8] = faces[8];
            newFaces[9] = faces[9];
            newFaces[10] = faces[6];
            newFaces[11] = faces[7];
            newFaces[12] = faces[12];
            newFaces[13] = faces[15];
            newFaces[14] = faces[12];
            newFaces[15] = faces[14];
            newFaces[16] = faces[16];
            newFaces[17] = faces[17];
            newFaces[18] = faces[10];
            newFaces[19] = faces[11];
            newFaces[20] = faces[20];
            newFaces[21] = faces[21];
            newFaces[22] = faces[18];
            newFaces[23] = faces[19];
        }


        return new State(newFaces, state, move, state.getDepth()+1);

    }
    // IDS - iterative deepening search algorithm
    public  Solution iterativeDeepeningSearch(State initialState) {
        for (int depth = 0; depth < 15; depth++) {
            State result = depthLimitedSearch(initialState, 0, depth);
            if (result != null) {
                List<String[]> solutionPath =  new ArrayList<>(constructSolutionPath( result));
                return new Solution(solutionPath, visitedVertices); // Solution found

            }
        }
        return new Solution(new ArrayList<>(), visitedVertices); // No solution found
    }

    private State depthLimitedSearch(State state, int currentDepth, int depthLimit) {
        visitedVertices++; // Increment visited vertices count
        if (isGoal(state)) {
            return state; // Goal state reached
        }
        if (currentDepth == depthLimit) {
            return null; // Depth limit reached, no solution
        }

        for (State childState : successorFunction(state)) {
            State result = depthLimitedSearch(childState, currentDepth + 1, depthLimit);
            if (result != null) {
                return result; // Solution found in a deeper level
            }
        }
        return null; // No solution found at this depth
    }

    public List<String[]> constructSolutionPath(State goalState) {
        List<String[]> solutionPath = new ArrayList<>();
        State currentState = goalState;

        while (currentState != null ) {
            solutionPath.add(0, currentState.getFaces()); // Add the faces at the beginning
            currentState = currentState.getParentState();
        }

        return solutionPath;
    }
    //A* solution
    public Solution aStarSearch(State initialState) {
        // Initialize the priority queue with a comparator based on depth + heuristic value
        PriorityQueue<State> openSet = new PriorityQueue<>(
                Comparator.comparingInt((State s) -> s.getDepth() + s.getHeuristicValue())
                        .thenComparing(s -> Arrays.toString(s.getFaces()))
        );

        // Add the initial state to the open set
        initialState.setDepth(0);
        initialState.computeHeuristicValue();
        //adding the first element to the Priority Queue
        openSet.add(initialState);

        // Map to store the lowest cost to a state (using the state's faces as key)
        Map<String, Integer> costSoFar = new HashMap<>();
        costSoFar.put(Arrays.toString(initialState.getFaces()), 0);

        while (!openSet.isEmpty()) {
            State currentState = openSet.poll();

            // Check if the current state is the goal state
            if (isGoal(currentState)) {
                // Reconstruct the path from the goal state to the initial state
                return new Solution(constructSolutionPath(currentState), visitedVertices);
            }

            // Iterate through all successors of the current state
            for (State nextState : successorFunction(currentState)) {
                int newCost = currentState.getDepth() + 1; // Cost to reach this successor

                String nextStateKey = Arrays.toString(nextState.getFaces());
                if (!costSoFar.containsKey(nextStateKey) || newCost < costSoFar.get(nextStateKey)) {
                    // Update the cost for this state
                    costSoFar.put(nextStateKey, newCost);

                    nextState.setDepth(newCost);
                    nextState.computeHeuristicValue();

                    openSet.add(nextState);
                    visitedVertices++; // Increment visited vertices count
                }
            }
        }

        return new Solution(new ArrayList<>(), visitedVertices); // No solution found
    }

    //Uniform Cost Search solution - needs at least 16GB RAM to run
    public Solution uniformCostSearch(State initialState) {
        // Initialize the priority queue with a comparator based on depth + heuristic value
        PriorityQueue<State> openSet = new PriorityQueue<>(Comparator
                .<State>comparingInt(State::getDepth)
                .thenComparing(s -> Arrays.toString(s.getFaces())));

        // Add the initial state to the open set
        initialState.setDepth(0);
        //adding the first element to the Priority Queue
        openSet.add(initialState);

        // Map to store the lowest cost to a state (using the state's faces as key)
        Map<String, Integer> costSoFar = new HashMap<>();
        costSoFar.put(Arrays.toString(initialState.getFaces()), 0);

        while (!openSet.isEmpty()) {

            State currentState = openSet.poll();

            // Check if the current state is the goal state
            if (isGoal(currentState)) {
                // Reconstruct the path from the goal state to the initial state
                return new Solution(constructSolutionPath(currentState), visitedVertices);
            }

            // Iterate through all successors of the current state
            for (State nextState : successorFunction(currentState)) {
                int newCost = currentState.getDepth() + 1; // Cost to reach this successor

                String nextStateKey = Arrays.toString(nextState.getFaces());
                if (!costSoFar.containsKey(nextStateKey) || newCost < costSoFar.get(nextStateKey)) {
                    // Update the cost for this state
                    costSoFar.put(nextStateKey, newCost);

                    nextState.setDepth(newCost);

                    openSet.add(nextState);
                    visitedVertices++; // Increment visited vertices count
                }
            }
        }

        return new Solution(new ArrayList<>(), visitedVertices); // No solution found
    }



    //the solution class
    public static class Solution {
        private final List<String[]> path;
        private final int visitedVertices;

        public Solution(List<String[]> path, int visitedVertices) {
            this.path = path;
            this.visitedVertices = visitedVertices;
        }

        // Getters
        public List<String[]> getPath() {
            return path;
        }

        public int getVisitedVertices() {
            return visitedVertices;
        }
    }
}