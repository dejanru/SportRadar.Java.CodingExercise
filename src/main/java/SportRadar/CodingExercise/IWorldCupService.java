package SportRadar.CodingExercise;

import java.util.ArrayList;

public interface IWorldCupService {
    ArrayList<IMatch> getRunningMatches();
    ArrayList<IMatch> getArchiveMatches();
    ArrayList<IMatch> startNewMatch(String homeTeam, String awayTeam);
    ArrayList<IMatch> updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);
}
