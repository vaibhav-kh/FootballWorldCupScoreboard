# Project Name
FootballWorldCupScoreboard

## Introduction
Live Football
World Cup Scoreboard library that shows all the ongoing matches and their scores.
The scoreboard supports the following operations:
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
   This should capture following parameters:
   a. Home team
   b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
   team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the
   same total score will be returned ordered by the most recently started match in the
   scoreboard.

## Getting Started

This is simple library with below details:
1. Use the library as a dependency. 
   - Path for Jar - out/artifacts/FootballWorldCupScoreboard_jar/FootballWorldCupScoreboard.jar
2. Attached are the test coverage.
   - Path for test coverage report - out/test coverage 
3. Source code path
   - src/main/java
4. Test cases path 
   - src/test/java

### Following assumption taken
1. Created Constant file to declare constant
2. Exception has been thrown if trying update score for match not in progress

# Command to clone the repository
git clone https://github.com/vaibhav-kh/FootballWorldCupScoreboard.git