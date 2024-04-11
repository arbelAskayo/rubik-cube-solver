import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {
    private String[] faces;
    private State parentState;
    private String lastMove;
    private Integer depth;
    private Integer heuristicValue;

    public State(String[] faces, State parentState, String lastMove, Integer depth) {
        this.faces = faces;
        this.parentState = parentState;
        this.lastMove = lastMove;
        this.depth= depth;
        computeHeuristicValue();
    }
    //heuristic value is the number of misplaced tiles
    public void computeHeuristicValue() {
        int count = 0;
        for (int i = 0; i < faces.length; i += 4) {
            for (int j = 0; j < 4; j++) {
                if (!faces[i + j].equals(faces[i])) {
                    count++;
                }
            }
        }
        this.heuristicValue = count;
    }

    // Getters and Setters


    public Integer getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(Integer heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public String[] getFaces() {
        return faces;
    }

    public void setFaces(String[] faces) {
        this.faces = faces;
    }

    public State getParentState() {
        return parentState;
    }

    public void setParentState(State parentState) {
        this.parentState = parentState;
    }

    public String getLastMove() {
        return lastMove;
    }

    public void setLastMove(String lastMove) {
        this.lastMove = lastMove;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return Arrays.deepEquals(faces, state.faces);
    }
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(faces);
    }
}
