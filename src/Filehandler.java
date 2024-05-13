import Members.Member;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Filehandler {
    public static ArrayList<Member> readMembersFromFile(){
        if ((!fileIsEmpty("src/Files/members.ser"))) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/Files/members.ser"));
                return (ArrayList<Member>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
                return null;
            }
        } else {
            return new ArrayList<Member>();
        }
    }

    public static Boolean fileIsEmpty(String filepath){
        try {
            File file = new File(filepath);
            if(file.length() != 0){
                return false;
            } else {
                return true;
            }
        } catch (NullPointerException error) {
            error.printStackTrace();
            System.out.println("Couldn't locate file: " + filepath);
            return true;
        }
    }
}
