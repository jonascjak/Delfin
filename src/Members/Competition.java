package Members;

import java.time.LocalDate;

public class Competition {
    private String competitionName;
    private LocalDate competitionDate;
    private double lapTime; // lap time in seconds

    public Competition(String competitionName, LocalDate competitionDate, double lapTime) {
        this.competitionName = competitionName;
        this.competitionDate = competitionDate;
        this.lapTime = lapTime;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public LocalDate getCompetitionDate() {
        return competitionDate;
    }

    public double getLapTime() {
        return lapTime;
    }

    @Override
    public String toString() {
        return "Competition Name: " + competitionName + "\nDate: " + competitionDate + "\nLap Time: " + lapTime + " seconds";
    }
}