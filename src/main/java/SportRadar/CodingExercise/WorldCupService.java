package SportRadar.CodingExercise;

import java.util.ArrayList;

public class WorldCupService
implements IWorldCupService{
    private ArrayList<IMatch> _runningMatches;
    private ArrayList<IMatch> _archiveMatches;

    public WorldCupService() {
        _runningMatches = new ArrayList<>();
        _archiveMatches = new ArrayList<>();
    }

    @Override
    public ArrayList<IMatch> getRunningMatches() {
        return _runningMatches;
    }

    @Override
    public ArrayList<IMatch> getArchiveMatches() {

        return _archiveMatches;
    }

    @Override
    public ArrayList<IMatch> startNewMatch(String homeTeam, String awayTeam) {
        IMatch newMatch = new Match(homeTeam, awayTeam);
        _runningMatches.add(newMatch);

        return _runningMatches;
    }

    @Override
    public ArrayList<IMatch> updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        IMatch match = _runningMatches.stream()
                            .filter(f -> f.awayTeam().getName() == awayTeam &&
                                                f.homeTeam().getName() == homeTeam)
                            .findFirst()
                            .get();
        if (match == null)
        {
            throw  new RuntimeException("Match not found");
        }

        match.homeTeam().setScore(homeScore);
        match.awayTeam().setScore(awayScore);

        return _runningMatches;
    }

    @Override
    public WorldCupMatches finishMatch(String homeTeam, String awayTeam) {
        IMatch match = _runningMatches.stream()
                .filter(f -> f.awayTeam().getName() == awayTeam &&
                        f.homeTeam().getName() == homeTeam)
                .findFirst()
                .get();
        if (match == null)
        {
            throw  new RuntimeException("Match not found");
        }

        _runningMatches.remove(match);
        _archiveMatches.add(match);

        WorldCupMatches matches = new WorldCupMatches();
        matches._finishedMatches = _archiveMatches;
        matches._runningMatches = _runningMatches;

        return matches;
    }
}
