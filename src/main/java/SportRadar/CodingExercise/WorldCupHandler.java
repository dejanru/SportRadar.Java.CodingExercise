package SportRadar.CodingExercise;

import java.util.ArrayList;

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
    public ArrayList<IMatch> startNewMatch(String homeTeam, String awayTeam) {

        return _worldCupService.startNewMatch(homeTeam, awayTeam);
    }

    @Override
    public ArrayList<IMatch> updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        return _worldCupService.updateScore(homeTeam, awayTeam, homeScore, awayScore);
    }

    @Override
    public WorldCupMatches finishMatch(String homeTeam, String awayTeam) {
        return _worldCupService.finishMatch(homeTeam, awayTeam);
    }
}
