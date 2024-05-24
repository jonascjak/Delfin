package Members;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class SwimmingDiscipline implements Serializable, Comparable<SwimmingDiscipline> {
    public String discipleName;
    public double bestTime;
    public LocalDate bestDate;

    public SwimmingDiscipline(String discipleName, double bestTime, LocalDate bestDate){
        this.discipleName = discipleName;
        this.bestTime = bestTime;
        this.bestDate = bestDate;
    }
    public String toString(){
        return discipleName;
    }

    public static boolean isBestTime(SwimmingDiscipline discipline, double newTime){
        if(newTime > discipline.getBestTime()){
            return true;
        } else {
            return false;
        }
    }

    public int compareTo(SwimmingDiscipline other) {
        return Double.compare(this.bestTime, other.bestTime);
    }
    public String getDiscipleName() {
        return discipleName;
    }

    public void setDiscipleName(String discipleName) {
        this.discipleName = discipleName;
    }

    public double getBestTime() {
        return bestTime;
    }

    public void setBestTime(double bestTime) {
        this.bestTime = bestTime;
    }

    public LocalDate getBestDate() {
        return bestDate;
    }

    public void setBestDate(LocalDate bestDate) {
        this.bestDate = bestDate;
    }
}
