## Runner console app
Yes, bad naming, but in /org.example I prepared a simple runner using prepared lib. So end user can play a little.
## Test data
a. Mexico 0 - Canada 5
b. Spain 10 - Brazil 2
c. Germany 2 - France 2
d. Uruguay 6 - Italy 6
e. Argentina 3 - Australia 1

### Order after test data are returned  (summary)
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2


## SportRadar.CodingExercise test 
using jMock to mock objects and simplify tests.


## SportRadar.CodingExercise
- WorldCupHandler : base runner class to be called from users of lib. Can be easily converted to API / used by REST API.
- WorldCupService : service layer, prepares and computes all data (except summary of matches)
- Match, Team : base classes used for implementation
- MatchSummary : in .net used Tuple, here I didn't want to search for Tuple libs so implemeted the other way.
