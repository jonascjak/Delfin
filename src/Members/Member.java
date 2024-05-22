package Members;

import java.time.LocalDate;
import java.time.Period;

public class Member {
    protected int memberID;
    protected String Name;
    protected LocalDate birthdate;

    protected boolean isMale;
    protected boolean isActive;
    protected boolean isJunior;
    protected boolean isSenior;

    public Member(int memberID, String name, LocalDate birthdate, boolean isMale, boolean isActive, boolean isJunior, boolean isSenior){
        this.memberID = memberID;
        this.Name = name;
        this.birthdate = birthdate;
        this.isMale = isMale;
        this.isActive = isActive;
        ageCategory();
    }
    public void ageCategory(){
        int age = calculateAge();
        this.isJunior = age < 18;
        this.isSenior = age > 60;
    }
    public int calculateAge(){
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthdate, currentDate);
        return age.getYears();
    }
}
