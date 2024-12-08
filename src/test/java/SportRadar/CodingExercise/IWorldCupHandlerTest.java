package SportRadar.CodingExercise;

import org.jmock.*;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class IWorldCupHandlerTest {

    private Mockery context;

    private WorldCupHandler _worldCupHandler;

    private IWorldCupService _worldCupService;
    private IMatch _match;
    private ITeam _team;


    @org.testng.annotations.BeforeMethod
    public void setUp() {
        context = new Mockery();
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
    public void testStartNewMatch() throws Exception {
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
    public void testFinishMatch() throws Exception {
        Match data1 = new Match("Austria", "Germany");
        Match data2 = new Match("Slovenia", "Italy");
        Match data3 = new Match("France", "England");
        ArrayList<IMatch> runningMatches = new ArrayList();
        ArrayList<IMatch> archiveMatches = new ArrayList();
        runningMatches.add(data1);
        runningMatches.add(data2);
        runningMatches.add(data3);

        // finish match2

        WorldCupMatches worldCupMatches = new WorldCupMatches();
        ArrayList<IMatch> runningMatches_after = new ArrayList();
        runningMatches_after.add(data1);
        runningMatches_after.add(data3);
        worldCupMatches. set_runningMatches(runningMatches_after);
        ArrayList<IMatch> finishedMatches_after = new ArrayList();
        finishedMatches_after.add(data2);
        worldCupMatches.set_finishedMatches(finishedMatches_after);

        context.checking(new Expectations() {{
            oneOf(_worldCupService).getRunningMatches();
            will(returnValue(runningMatches));

            oneOf(_worldCupService).finishMatch("Slovenia", "Italy");
            will(returnValue(worldCupMatches));
        }});

        // Invoke
        ArrayList<IMatch> matchesOnStart = _worldCupHandler.getRunningMatches();
        assertEquals(3, runningMatches.size());
        assertEquals(0, archiveMatches.size());

        IWorldCupMatches currentMatches = _worldCupHandler.finishMatch("Slovenia", "Italy");
        assertEquals(2, currentMatches.get_runningMatches().size());
        assertEquals(1, currentMatches.get_finishedMatches().size());

        context.assertIsSatisfied();
    }

    @org.testng.annotations.Test
    public void testSummaryOfMatches()
    {
        // NOTE : thread sleeep introduced to be able to have a big difference in ticks timestamp
        Match data1 = new Match("Mexico", "Canada", 0, 5);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Match data2 = new Match("Spain", "Brazil", 10, 2);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Match data3 = new Match("Germany", "France", 2,2);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Match data4 = new Match("Uruguay", "Italy", 6,6);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Match data5 = new Match("Argentina", "Australia", 3,1);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        ArrayList<IMatch> runningMatches = new ArrayList();
        runningMatches.add(data1);
        runningMatches.add(data2);
        runningMatches.add(data3);
        runningMatches.add(data4);
        runningMatches.add(data5);

        context.checking(new Expectations() {{
            oneOf(_worldCupService).getRunningMatches();
            will(returnValue(runningMatches));
        }});

        ArrayList<IMatchSummary> result = _worldCupHandler.getSummaryOfMatches();

        assertEquals(5, runningMatches.size());
        assertEquals(5, result.size());
        assertEquals("Uruguay", result.get(0).get_Match().homeTeam().getName());
        assertEquals("Spain", result.get(1).get_Match().homeTeam().getName());
        assertEquals("Mexico", result.get(2).get_Match().homeTeam().getName());
        assertEquals("Argentina", result.get(3).get_Match().homeTeam().getName());
        assertEquals("Germany", result.get(4).get_Match().homeTeam().getName());

        context.assertIsSatisfied();

    }
}