package Kolokvium_2.FootballTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Partial exam II 2016/2017
 */
public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = reader.readLine())!=null && line.length()!=0){
            String parts [] =line.split(";");
            table.addGame(parts[0], parts[1],
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]));
        }
//        reader.lines()
//                .map(line -> line.split(";"))
//                .forEach(parts -> table.addGame(parts[0], parts[1],
//                        Integer.parseInt(parts[2]),
//                        Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}

// Your code here
class Team{
    String name;
    int matchesPlayed;
    int wins;
    int draws;
    int loses;
    int points;
    int scoredGoals;
    int conceledGoals;
    public Team(String name) {
        this.name=name;
        this.matchesPlayed=0;
        this.scoredGoals=0;
        this.conceledGoals=0;
        this.wins=0;
        this.draws=0;
        this.loses=0;
        this.points=0;
    }
    void addGame(int w, int scored, int conceled){
        matchesPlayed++;
        if(w==1){
            wins++;
            points+=3;
        }else if(w==0){
            draws++;
            points++;
        }else{
            loses++;
        }
        scoredGoals+=scored;
        conceledGoals+=conceled;
    }
    @Override
    public String toString() {
        // 1. West Ham          10    6    2    2   20
        return String.format("%-15s%5d%5d%5d%5d%5d", name, matchesPlayed, wins,draws,loses,points);
    }
    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }
    public int getDifferece() {
        return scoredGoals-conceledGoals;
    }
}

class FootballTable{
    Map <String,Team> teamsTable = new HashMap<String,Team>();
    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals){
        teamsTable.putIfAbsent(homeTeam,new Team(homeTeam));
        teamsTable.putIfAbsent(awayTeam,new Team(awayTeam));
        if(homeGoals>awayGoals){
            teamsTable.get(homeTeam).addGame(1, homeGoals, awayGoals);
            teamsTable.get(awayTeam).addGame(-1, awayGoals, homeGoals);
        }else if(homeGoals == awayGoals){
            teamsTable.get(homeTeam).addGame(0, homeGoals, awayGoals);
            teamsTable.get(awayTeam).addGame(0, awayGoals, homeGoals);
        }else{
            teamsTable.get(homeTeam).addGame(-1, homeGoals, awayGoals);
            teamsTable.get(awayTeam).addGame(1, awayGoals, homeGoals);
        }
    }
    public void printTable(){
        List<Team> sortedTeams = teamsTable.values().stream()
                .sorted(Comparator.comparing(Team::getPoints)
                        .thenComparing(Team::getDifferece).reversed().thenComparing(Team::getName))
                .collect(Collectors.toList());
        for(int i=0;i<sortedTeams.size();i++){
            System.out.println(String.format("%2d. %s",i+1, sortedTeams.get(i).toString()));
        }
    }
}