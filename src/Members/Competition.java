package Members;

import java.io.Serializable;
import java.time.LocalDate;

public class Competition implements Serializable {
    private String competitionName;
    private LocalDate competitionDate;
    private int placement;
    private double lapTime; // lap time in seconds

    public Competition(String competitionName, LocalDate competitionDate,int placement,double lapTime) {
        this.competitionName = competitionName;
        this.competitionDate = competitionDate;
        this.placement = placement;
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
        return "Competition Name: " + competitionName + "\nDate: " + competitionDate +"\nPlacement: "+placement+ "\nLap Time: " + lapTime + " seconds";
    }
}