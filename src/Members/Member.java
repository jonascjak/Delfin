package Members;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Member implements Serializable {
    protected int memberID;
    protected String Name;
    protected LocalDate birthdate;

    protected boolean isMale;
    protected boolean isActive;
    public boolean isJunior;
    protected boolean isSenior;

    public Member(int memberID, String name, LocalDate birthdate, boolean isMale, boolean isActive){
        this.memberID = memberID;
        this.Name = name;
        this.birthdate = birthdate;
        this.isMale = isMale;
        this.isActive = isActive;
        ageCategory();
    }
    protected void ageCategory(){
        int age = calculateAge();
        this.isJunior = age < 18;
        this.isSenior = age > 60;
    }
    protected int calculateAge(){
        LocalDate currentDate = LocalDate.now();
        if (birthdate == null || birthdate.isAfter(currentDate)){
            System.out.println("Something went wrong with the persons age");
        }
        Period age = Period.between(birthdate, currentDate);
        return age.getYears();
    }

    @Override
    public String toString() {
        return
            "\nmember ID: " + memberID +
            "\nName: " + Name +
            "\nbirthdate: " + birthdate +
            "\nSex: " + (isMale? "Male": "Female");
    }

    public int getMemberID() {
        return memberID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
