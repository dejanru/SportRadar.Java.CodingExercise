package SportRadar.CodingExercise;

public interface IMatch {
    ITeam homeTeam();
    ITeam awayTeam();
    long createdAt_ticks();
}
