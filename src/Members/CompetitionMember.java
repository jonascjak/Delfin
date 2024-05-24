package Members;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class CompetitionMember extends Member {
    protected ArrayList<Competition> competitions;
    protected ArrayList<SwimmingDiscipline> swimmingDisciplines;
    public CompetitionMember(int memberID, String name, LocalDate birthdate, boolean isMale, boolean isActive, ArrayList<Competition> competitions, ArrayList<SwimmingDiscipline> swimmingDisciplines) {
        super(memberID, name, birthdate, isMale, isActive);
        ageCategory();
        this.competitions = competitions;
        this.swimmingDisciplines = swimmingDisciplines;
    }
    @Override
    public int calculatePrice() {
        if (isJunior) {
            return 1000;
        } else if (isSenior) {
            return 1400;
        } else {
            return 1600;
        }
    }
    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Competition competition) {
        try {
            competitions.add(competition);
        } catch (InputMismatchException ignored){}
    }

    public ArrayList<SwimmingDiscipline> getSwimmingDisciplines() {
        return swimmingDisciplines;
    }

    public void setSwimmingDisciplines(SwimmingDiscipline discipline) {
        if (!swimmingDisciplines.contains(discipline)) {
            swimmingDisciplines.add(discipline);
        }
    }

    @Override
    public String toString() {
        StringBuilder allString = new StringBuilder(super.toString());
        for (SwimmingDiscipline discipline : swimmingDisciplines) {
            allString.append("\n").append(discipline.toString());
        }
        if(this.competitions != null) {
            for (Competition competition : competitions) {
                allString.append("\n").append(competition.toString());
            }
        }
        return allString.toString();
    }
}
