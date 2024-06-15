# Rubik's Cube Solver

## Overview
This project implements a solver for a simplified version of a Rubik's Cube using three different search algorithms: A* Search, Iterative Deepening Search (IDS), and Uniform Cost Search (UCS). The program allows users to input the initial state of the cube and choose the algorithm to find the solution.

## Key Features
- **User Input:** The program prompts the user to input the initial state of the cube as a string of 24 characters representing the cube's faces.
- **Algorithm Selection:** Users can choose between A* Search, Iterative Deepening Search (IDS), and Uniform Cost Search (UCS) to solve the cube.
- **Solution Path:** The program outputs the sequence of moves to solve the cube and the number of vertices visited during the search.

## Classes and Methods
### Solver Class
- `main(String[] args)`: The entry point of the program, handles user input and algorithm selection.

### CubeSolver Class
- `isGoal(State state)`: Checks if the current state is the goal state.
- `successorFunction(State state)`: Generates all possible moves from the current state.
- `applyMove(State state, String move)`: Applies a move to the current state and returns the resulting state.
- `iterativeDeepeningSearch(State initialState)`: Implements IDS to find a solution.
- `depthLimitedSearch(State state, int currentDepth, int depthLimit)`: Helper method for IDS.
- `aStarSearch(State initialState)`: Implements A* Search to find a solution.
- `uniformCostSearch(State initialState)`: Implements UCS to find a solution.
- `constructSolutionPath(State goalState)`: Constructs the solution path from the initial state to the goal state.

### State Class
Represents the state of the cube, including the faces, parent state, move applied, and depth.

## Search Algorithms
- **A* Search:** Uses a priority queue to explore the most promising paths first, combining the cost to reach a state and a heuristic estimate of the cost to reach the goal.
- **Iterative Deepening Search (IDS):** Combines the depth-first search's space efficiency and breadth-first search's completeness by iteratively deepening the search limit.
- **Uniform Cost Search (UCS):** Explores paths in order of their cost, ensuring that the lowest-cost solution is found.

## Usage
1. **Input the initial state:** Enter a 24-character string representing the cube's faces.
2. **Choose the algorithm:** Select 'A' for A* Search, 'I' for IDS, or 'U' for UCS.
3. **View the solution:** The program outputs the sequence of moves and the number of visited vertices.

## Example
```plaintext
Enter the initial state of the cube (For example: YGGWORRRYBGYOBYBGOOWWRWB):
YGGWORRRYBGYOBYBGOOWWRWB
Choose the algorithm: A* (A) or Iterative Deepening Search (I) or Uniform Cost Search (U) :
A
Solution path:
[Y, G, G, W, O, R, R, R, Y, B, G, Y, O, B, Y, B, G, O, O, W, W, R, W, B]
Visited vertices: 104
