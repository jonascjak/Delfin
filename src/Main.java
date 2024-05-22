import Members.Member;
import Utility.Filehandler;

import java.io.Serializable;
import java.util.ArrayList;

public class Main implements Serializable {
    public static ArrayList<Member> members = Filehandler.readMembersFromFile();
    public static void main(String[] args) {

    }
}