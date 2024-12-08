package SportRadar.CodingExercise;

public class MatchSummary implements IMatchSummary {
    private int _totalScore;
    private long _createdTicks;
    private IMatch _match;

    @Override
    public void set_SumScore(int score) {
        _totalScore = score;
    }

    @Override
    public void set_created_ticks(long ticks) {
        _createdTicks = ticks;
    }

    @Override
    public void set_Match(IMatch match) {
        _match = match;
    }

    @Override
    public IMatch get_Match() {
        return _match;
    }

    @Override
    public long get_created_ticks() {
        return _createdTicks;
    }

    @Override
    public int get_SumScore() {
        return _totalScore;
    }

    @Override
    public String toString() {
        return "Item{" +
                "createdDate=" + _createdTicks +
                ", totalScore='" + _totalScore + '\'' +
                ", homeTeam=" + _match.homeTeam().getName() +
                ", awayTeam=" + _match.awayTeam().getName() +
                '}';
    }
}
