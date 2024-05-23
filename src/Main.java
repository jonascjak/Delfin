import Members.Member;
import Utility.Filehandler;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main implements Serializable {
    public static ArrayList<Member> members = Filehandler.readMembersFromFile();
    public static ArrayList<Member> juniorTeam = Filehandler.readMembersFromFile();
    public static ArrayList<Member> seniorTeam = Filehandler.readMembersFromFile();

    public static void main(String[] args) {
        UI.loginMenu();
        // test code
        LocalDate birthdate = LocalDate.of(2000,2,2);
        Member temp = new Member(2,"John doe", birthdate,true, true);
    }
}