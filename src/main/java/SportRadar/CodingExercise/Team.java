package SportRadar.CodingExercise;

public class Team implements ITeam {
    private String _name;
    private int _score;

    public Team(String name){
        _name = name;
        _score = 0;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
    public int getScore() {
        return _score;
    }
    public void setScore(int score) {
        _score = score;
    }
}
