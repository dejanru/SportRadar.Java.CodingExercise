package SportRadar.CodingExercise;

public interface IMatchSummary {
    void set_SumScore(int score);
    void set_created_ticks(long ticks);
    void set_Match(IMatch match);
    IMatch get_Match();
    long get_created_ticks();
    int get_SumScore();
}
