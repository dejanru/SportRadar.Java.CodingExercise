package SportRadar.CodingExercise;

import java.util.*;

public class Match implements IMatch {
    private final ITeam _homeTeam;
    private final ITeam _awayTeam;
    private final long _createdAt_ticks;

    public Match(String homeTeamName, String awayTeamName) {
        _homeTeam = new Team(homeTeamName) ;
        _awayTeam = new Team(awayTeamName);
        _createdAt_ticks = Calendar.getInstance().getTimeInMillis();
    }

    public Match(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        _homeTeam = new Team(homeTeam);
        _awayTeam = new Team(awayTeam);
        _createdAt_ticks = Calendar.getInstance().getTimeInMillis();
        _homeTeam.setScore(homeTeamScore);
        _awayTeam.setScore(awayTeamScore);
    }

    @Override
    public ITeam homeTeam() {
        return _homeTeam;
    }

    @Override
    public ITeam awayTeam() {
        return _awayTeam;
    }

    @Override
    public long createdAt_ticks() {
        return _createdAt_ticks;
    }

    @Override
    public String toString() {
        return "Item{" +
                "createdDate=" + _createdAt_ticks +
                ", homeTeam='" + _homeTeam.getName() + '\'' +
                ", awayTeam=" + _awayTeam.getName() +
                '}';
    }
}
