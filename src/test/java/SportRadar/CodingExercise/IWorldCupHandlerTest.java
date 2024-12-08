package SportRadar.CodingExercise;

import org.jmock.*;
import org.junit.Assert;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class IWorldCupHandlerTest {

    private WorldCupHandler _worldCupHandler;

    private IWorldCupService _worldCupService;
    private IMatch _match;
    private ITeam _team;

    private Mockery context = new Mockery();

    @org.testng.annotations.BeforeMethod
    public void setUp() {
        _worldCupService = context.mock(IWorldCupService.class);
        _match = context.mock(IMatch.class);
        _team = context.mock(ITeam.class);

        _worldCupHandler = new WorldCupHandler(_worldCupService, _match, _team);
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() {
        // worldCupHandler = null;
    }

    @org.testng.annotations.Test
    public void testGetRunningMatches() {
        // define test data
        Match data1 = new Match("Austria", "Germany");
        Match data2 = new Match("Slovenia", "Italy");

        ArrayList<IMatch> matches = new ArrayList();
        matches.add(data1);
        matches.add(data2);

        context.checking(new Expectations() {{
            oneOf(_worldCupService).getRunningMatches();
            will(returnValue(matches));
        }});

        // invoke
        ArrayList<IMatch> actualMatches = _worldCupHandler.getRunningMatches();

        assertEquals(matches, actualMatches);

        context.assertIsSatisfied();
    }

    @org.testng.annotations.Test
    public void testGetArchiveMatches() {
        // define test data
        Match data1 = new Match("Austria", "Germany");
        Match data2 = new Match("Slovenia", "Italy");

        ArrayList<IMatch> matches = new ArrayList();
        matches.add(data1);
        matches.add(data2);

        context.checking(new Expectations() {{
            oneOf(_worldCupService).getArchiveMatches();
            will(returnValue(matches));
        }});

        // invoke
        ArrayList<IMatch> actualMatches = _worldCupHandler.getArchiveMatches();
        assertEquals(matches, actualMatches);
        context.assertIsSatisfied();
    }

    @org.testng.annotations.Test
    public void testStartNewMatch() {
        // empty result
        ArrayList<IMatch> matches = new ArrayList();
        // result after added match
        Match data1 = new Match("Austria", "Germany");
        ArrayList<IMatch> matchesAfterStart = new ArrayList();
        matchesAfterStart.add(data1);

        context.checking(new Expectations() {{
            oneOf(_worldCupService).getRunningMatches();
            will(returnValue(matches));

            oneOf(_worldCupService).startNewMatch("Austria", "Germany");
            will(returnValue(matchesAfterStart));
        }});


        // Invoke
        ArrayList<IMatch> matchesOnStart = _worldCupHandler.getRunningMatches();
        assertEquals(0, matchesOnStart.size());

        ArrayList<IMatch> addedMatches = _worldCupHandler.startNewMatch("Austria", "Germany");

        assertEquals(matchesAfterStart, addedMatches);

        context.assertIsSatisfied();
    }

    @org.testng.annotations.Test
    public void testUpdateScore() {
        // prepare result
        Match data1 = new Match("Austria", "Germany");
        Match data2 = new Match("Slovenia", "Italy");
        Match data3 = new Match("France", "England");
        ArrayList<IMatch> matches = new ArrayList();
        matches.add(data1);
        matches.add(data2);
        matches.add(data3);

        ArrayList<IMatch> updatedMatches = new ArrayList(matches);
        Match updatedData = new Match("Slovenia", "Italy", 0, 2);
        updatedMatches.set(1, updatedData);

        context.checking(new Expectations() {{
            oneOf(_worldCupService).getRunningMatches();
            will(returnValue(matches));

            oneOf(_worldCupService).updateScore("Slovenia", "Italy", 0, 2);
            will(returnValue(updatedMatches));

        }});

        // Invoke
        ArrayList<IMatch> matchesOnStart = _worldCupHandler.getRunningMatches();
        assertEquals(3, matchesOnStart.size());

        ArrayList<IMatch> updatedRunningMatches = _worldCupHandler.updateScore("Slovenia", "Italy", 0, 2);

        assertEquals(updatedMatches, updatedRunningMatches);

        context.assertIsSatisfied();
    }

    @org.testng.annotations.Test
    public void testFinishMatch()
    {

    }
}