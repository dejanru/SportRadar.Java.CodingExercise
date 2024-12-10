package SportRadar.CodingExercise;

import java.util.ArrayList;

public class WorldCupMatches implements IWorldCupMatches{
    private ArrayList<IMatch> _runningMatches;
    private ArrayList<IMatch> _finishedMatches;

    public WorldCupMatches() {

    }

    public void set_finishedMatches(ArrayList<IMatch> _finishedMatches) {
        this._finishedMatches = _finishedMatches;
    }

    public ArrayList<IMatch> get_finishedMatches() {
        return _finishedMatches;
    }

    public ArrayList<IMatch> get_runningMatches() {
        return _runningMatches;
    }

    public void set_runningMatches(ArrayList<IMatch> _runningMatches) {
        this._runningMatches = _runningMatches;
    }

}
