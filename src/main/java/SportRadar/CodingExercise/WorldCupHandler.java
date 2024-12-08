package SportRadar.CodingExercise;

import java.util.ArrayList;
import java.util.Comparator;

public class WorldCupHandler implements IWorldCupHandler {
    private IWorldCupService _worldCupService;

    public WorldCupHandler(IWorldCupService worldCupService, IMatch match, ITeam team) {
        _worldCupService = worldCupService;
    }

    @Override
    public ArrayList<IMatch> getRunningMatches() {
        return  _worldCupService.getRunningMatches();
    }

    @Override
    public ArrayList<IMatch> getArchiveMatches() {
        return _worldCupService.getArchiveMatches();
    }

    @Override
    public ArrayList<IMatch> startNewMatch(String homeTeam, String awayTeam) throws Exception {

        return _worldCupService.startNewMatch(homeTeam, awayTeam);
    }

    @Override
    public ArrayList<IMatch> updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        return _worldCupService.updateScore(homeTeam, awayTeam, homeScore, awayScore);
    }

    @Override
    public IWorldCupMatches finishMatch(String homeTeam, String awayTeam) throws Exception {
        return _worldCupService.finishMatch(homeTeam, awayTeam);
    }

    @Override
    public ArrayList<IMatchSummary> getSummaryOfMatches() {
        Comparator<IMatch> comparator = Comparator.comparingLong(IMatch::createdAt_ticks);
        Comparator<IMatch> reversedComparator = comparator.reversed();
        ArrayList<IMatch> matches = _worldCupService.getRunningMatches();

        matches.sort(Comparator.comparing(IMatch::createdAt_ticks).reversed());

        ArrayList<IMatchSummary> results = new ArrayList<>();
        IMatchSummary summary;

        for (IMatch item : matches) {
//            System.out.println(item.toString());
            int homeScore = item.homeTeam().getScore();
            int awayScore = item.awayTeam().getScore();
            summary = new MatchSummary();
            summary.set_SumScore(homeScore + awayScore);
            summary.set_created_ticks(item.createdAt_ticks());
            summary.set_Match(item);
            results.add(summary);
        }

        results.sort(Comparator.comparing(IMatchSummary::get_SumScore)
                            .thenComparing(IMatchSummary::get_created_ticks).reversed());

//        for (IMatchSummary item : results) {
//            System.out.println(item.toString());
//        }

        return results;
    }
}
