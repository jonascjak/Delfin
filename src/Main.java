import Members.Member;
import java.io.Serializable;
import java.util.ArrayList;

public class Main implements Serializable {
    public static ArrayList<Member> members = Filehandler.readMembersFromFile();
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}