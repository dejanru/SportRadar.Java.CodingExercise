package SportRadar.CodingExercise;

import java.util.ArrayList;

public interface IWorldCupMatches {
    ArrayList<IMatch> get_runningMatches();
    ArrayList<IMatch> get_finishedMatches();
    void set_runningMatches(ArrayList<IMatch> _runningMatches);
    void set_finishedMatches(ArrayList<IMatch> _finishedMatches);
}

