package Utility;

import Members.Member;

import java.io.*;
import java.util.ArrayList;

public class Filehandler {

    public static void writeMembersToFile(ArrayList<Member> memberList){
        try  {
            ObjectOutputStream outPutStream = new ObjectOutputStream(new FileOutputStream("src/Files/members.ser",false));
            outPutStream.writeObject(memberList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
