package SportRadar.CodingExercise;

import java.util.*;

public class Match implements IMatch {
    private ITeam _homeTeam;
    private ITeam _awayTeam;
    private long _createdAt_ticks;

    public Match(String homeTeamName, String awayTeamName) {
        _homeTeam = new Team(homeTeamName) ;
        _awayTeam = new Team(awayTeamName);
        _createdAt_ticks = Calendar.getInstance().getTimeInMillis();
    }

    public Match(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        _homeTeam = new Team(homeTeam);
        _awayTeam = new Team(awayTeam);
        _createdAt_ticks = Calendar.getInstance().getTimeInMillis();;
        _homeTeam.setScore(0);
        _awayTeam.setScore(0);
    }

    @Override
    public ITeam homeTeam() {
        return null;
    }

    @Override
    public ITeam awayTeam() {
        return null;
    }

    @Override
    public long createdAt_ticks() {
        return 0;
    }
}
