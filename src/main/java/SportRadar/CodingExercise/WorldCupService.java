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
    public ArrayList<IMatch> startNewMatch(String homeTeam, String awayTeam) throws Exception {
        boolean matchAlreadyExists = _runningMatches.stream()
                .anyMatch(m -> m.homeTeam().getName().equals(homeTeam) && m.awayTeam().getName().equals(awayTeam));

        boolean homeTeamAlreadyPlays = _runningMatches.stream()
                .anyMatch(m -> m.homeTeam().getName().equals(homeTeam) || m.awayTeam().getName().equals(homeTeam) );
        boolean awayTeamAlreadyPlays = _runningMatches.stream()
                .anyMatch(m -> m.homeTeam().getName().equals(awayTeam) | m.awayTeam().getName().equals(awayTeam));
        if (!matchAlreadyExists)
        {
            if (homeTeamAlreadyPlays ){
                throw new Exception("Team "+ homeTeam +" already playes match in progress.");
            }
            if (awayTeamAlreadyPlays){
                throw new Exception("Team "+ awayTeam +" already playes match in progress.");
            }
            IMatch newMatch = new Match(homeTeam, awayTeam);
            _runningMatches.add(newMatch);
        }
        else {
            throw new Exception("Match between teams : Home("+ homeTeam + ") and Away (" +awayTeam +") already in progress.");
        }

        return _runningMatches;
    }

    @Override
    public ArrayList<IMatch> updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        IMatch match = _runningMatches.stream()
                            .filter(f -> f.awayTeam().getName().equals(awayTeam) &&
                                                f.homeTeam().getName().equals(homeTeam))
                            .findFirst()
                            .get();

        if (match == null)
        {
            throw new RuntimeException("No running match between " + homeTeam + " and " + awayTeam);
        }

        match.homeTeam().setScore(homeScore);
        match.awayTeam().setScore(awayScore);

        return _runningMatches;
    }

    @Override
    public WorldCupMatches finishMatch(String homeTeam, String awayTeam) throws Exception {
        IMatch match = _runningMatches.stream()
                .filter(f -> f.awayTeam().getName().equals(awayTeam) &&
                        f.homeTeam().getName().equals(homeTeam))
                .findFirst()
                .get();

        if (match == null)
        {
            throw new Exception("No running match between " + homeTeam + " and " + awayTeam);
        }

        _runningMatches.remove(match);
        _archiveMatches.add(match);

        WorldCupMatches matches = new WorldCupMatches();
        matches.set_finishedMatches(_archiveMatches);
        matches.set_runningMatches(_runningMatches);

        return matches;
    }
}
