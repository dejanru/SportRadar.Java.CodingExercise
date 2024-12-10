package org.example;
import SportRadar.CodingExercise.*;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!%n");
        IMatch match = null;
        ITeam team = null;
        IWorldCupService worldCupService = new WorldCupService();
        IWorldCupHandler worldCupHandler = new WorldCupHandler(worldCupService, match, team);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a number between 0 and 6: ");
            System.out.println("1 : Get current state (running matches and archive matches)");
            System.out.println("2 : Start new match");
            System.out.println("3 : Update score for existing match");
            System.out.println("4 : Finish existing match");
            System.out.println("5 : Get summary of matches");
            System.out.println("6 : Fill test data for sample summary");
            System.out.println("0 : Finish() and good bye.");

            int input = scanner.nextInt();

            if (input < 0 || input > 6) {

                System.out.println("Invalid input. Please try again.");
            }

            if (input == 0) {
                System.out.printf("Exiting %n");
                break;
            }
            try {

                switch (input) {
                    case 1:
                        GetCurrentState(worldCupHandler);
                        break;
                    case 2:
                        System.out.print("enter Home team name: ");
                        String homeTeamName = scanner.next();
                        System.out.print("enter Away team name: ");
                        String awayTeamName = scanner.next();

                        worldCupHandler.startNewMatch(homeTeamName, awayTeamName);
                        break;
                    case 3:
                        System.out.print("select existing match: ");
                        ArrayList<IMatch> runningMatches = worldCupHandler.getRunningMatches();
                        System.out.printf("---------------------------------------------------------%n");
                        System.out.printf("Running matches: %d %n", runningMatches.size());
                        int cnt = 0;
                        for (IMatch mtch: runningMatches)  {
                            System.out.printf("%s : HomeTeam : %s (%d) |  AwayTeam : %s (%d) %n", cnt, mtch.homeTeam().getName(), mtch.homeTeam().getScore(), mtch.awayTeam().getName(), mtch.awayTeam().getScore());
                            cnt++;
                        }
                        int m = scanner.nextInt();
                        System.out.printf("provide score for %s: ", runningMatches.get(m).homeTeam().getName());
                        int homeScore = scanner.nextInt();
                        System.out.printf("provide score for %s: ", runningMatches.get(m).awayTeam().getName());
                        int awayScore = scanner.nextInt();
                        worldCupHandler.updateScore(runningMatches.get(m).homeTeam().getName(),
                                runningMatches.get(m).awayTeam().getName(),
                                homeScore,
                                awayScore);
                        break;
                    case 4:
                        IMatch runningMatch = GetRunningMatch(worldCupHandler, scanner);
                        worldCupHandler.finishMatch(runningMatch.homeTeam().getName(), runningMatch.awayTeam().getName());

                        break;
                    case 5:
                        ArrayList<IMatchSummary> summaryOfMatches = worldCupHandler.getSummaryOfMatches();
                        DisplaySummary(summaryOfMatches);
                        break;
                    case 6:
                        worldCupHandler.startNewMatch("Mexico", "Canada");
                        worldCupHandler.updateScore("Mexico", "Canada", 0,5);

                        worldCupHandler.startNewMatch("Spain", "Brazil");
                        worldCupHandler.updateScore("Spain", "Brazil", 10,2);

                        worldCupHandler.startNewMatch("Germany", "France");
                        worldCupHandler.updateScore("Germany", "France",2,2);

                        worldCupHandler.startNewMatch("Uruguay", "Italy");
                        worldCupHandler.updateScore("Uruguay", "Italy",6,6);

                        worldCupHandler.startNewMatch("Argentina", "Australia");
                        worldCupHandler.updateScore("Argentina", "Australia",3,1);
                        break;
                }
            }
            catch (Exception e) {
                System.out.printf("Invalid input. Please try again.%n Message : %s%n", e.getMessage());
            }

        }

        scanner.close();
    }

    private static IMatch GetRunningMatch(IWorldCupHandler worldCupHandler, Scanner scanner) {
        System.out.print("select existing match: ");
        ArrayList<IMatch> runningMatches = worldCupHandler.getRunningMatches();
        System.out.printf("---------------------------------------------------------%n");
        System.out.printf("Running matches: %d %n", runningMatches.size());
        int cnt = 0;
        for (IMatch mtch: runningMatches)  {
            System.out.printf("%s : HomeTeam : %s (%d) |  AwayTeam : %s (%d) %n", cnt, mtch.homeTeam().getName(), mtch.homeTeam().getScore(), mtch.awayTeam().getName(), mtch.awayTeam().getScore());
            cnt++;
        }
        int m = scanner.nextInt();

        return runningMatches.get(m);
    }

    public static void GetCurrentState(IWorldCupHandler worldCupHandler) {
        ArrayList<IMatch> runningMatches = worldCupHandler.getRunningMatches();
        System.out.printf("---------------------------------------------------------%n");
        System.out.printf("Running matches: %d %n", runningMatches.size());
        System.out.printf("HomeTeam         |   AwayTeam%n");
        for (IMatch match : runningMatches) {
            System.out.printf("%s [%s] | " , match.homeTeam().getName(), match.homeTeam().getScore());
            System.out.printf("%s [%s] %n" , match.awayTeam().getName(), match.awayTeam().getScore());
        }

        ArrayList<IMatch> archiveMatches = worldCupHandler.getArchiveMatches();
        System.out.printf("--------------------%n");
        System.out.printf("Archive matches: %d %n", archiveMatches.size());
        System.out.printf("HomeTeam         |   AwayTeam%n");
        for (IMatch match : archiveMatches) {
            System.out.printf("%s [%s] | " , match.homeTeam().getName(), match.homeTeam().getScore());
            System.out.printf("%s [%s] %n" , match.awayTeam().getName(), match.awayTeam().getScore());
        }

        System.out.printf("---------------------------------------------------------%n");
    }
    public static void DisplaySummary(ArrayList<IMatchSummary> summaryOfMatches) {
        System.out.printf("--------------------%n");
        System.out.printf("Running matches summary: %d %n", summaryOfMatches.size());
        for (IMatchSummary sum :summaryOfMatches)
        {
            System.out.printf("HomeTeam: %s [%s] | " , sum.get_Match().homeTeam().getName(), sum.get_Match().homeTeam().getScore());
            System.out.printf("AwayTeam: %s [%s] %n" , sum.get_Match().awayTeam().getName(), sum.get_Match().awayTeam().getScore());
        }
    }
}