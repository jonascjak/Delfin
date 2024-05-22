package Members;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class CompetitionMember extends Member {
    protected ArrayList<Competition> competitions;
    protected ArrayList<String> swimmingDisciplines;
    public CompetitionMember(int memberID, String name, LocalDate birthdate, boolean isMale, boolean isActive, ArrayList<Competition> competitions, ArrayList<String> swimmingDisciplines) {
        super(memberID, name, birthdate, isMale, isActive);
        ageCategory();
        this.competitions = new ArrayList<>();
        this.swimmingDisciplines = new ArrayList<>();
    }

    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Competition competition) {
        try {
            competitions.add(competition);
        } catch (InputMismatchException ignored){}
    }

    public ArrayList<String> getSwimmingDisciplines() {
        return swimmingDisciplines;
    }

    public void setSwimmingDisciplines(String discipline) {
        if (!swimmingDisciplines.contains(discipline)) {
            swimmingDisciplines.add(discipline);
        }
    }

    @Override
    public String toString() {
        StringBuilder disciplines = new StringBuilder();
        for (String discipline : swimmingDisciplines) {
            disciplines.append(discipline).append(", ");
        }

        StringBuilder membersCompetitions = new StringBuilder();
        for (Competition comp : competitions) {
            membersCompetitions.append(comp).append(", ");
        }

        return super.toString() + disciplines + membersCompetitions;
    }
}
