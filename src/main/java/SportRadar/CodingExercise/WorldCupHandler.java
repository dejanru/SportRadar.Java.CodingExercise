package SportRadar.CodingExercise;

import java.util.ArrayList;

public class WorldCupHandler implements IWorldCupHandler {
    @Override
    public ArrayList<IMatch> getRunningMatches() {
        return null;
    }

    @Override
    public ArrayList<IMatch> getArchiveMatches() {
        return null;
    }

    @Override
    public ArrayList<IMatch> startNewMatche(String homeTeam, String awayTeam) {
        return null;
    }

    @Override
    public ArrayList<IMatch> updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        return null;
    }
}
