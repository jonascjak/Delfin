package Members;

import java.time.LocalDate;

public class CompetitionMember extends Member {

    public CompetitionMember(int memberID, String name, LocalDate birthdate, boolean isMale, boolean isActive) {
        super(memberID, name, birthdate, isMale, isActive);
    }
}
