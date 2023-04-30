package Unipi.Java.ex1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


class Main
{
    public static void main(String[] args) 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Member> memberList = null;
        memberList = deserializeAllMembers();       //load serialized members if there are any
        if(memberList == null) memberList = new ArrayList<>();
        boolean exitFlag = false;
        while(exitFlag == false)
        {
            showOptions();
            switch(readOption())
            {

                case 1:
                    Member.showAll(memberList);
                    break;

                case 2:
                    Member.insert(memberList);
                    break;

                case 3:
                    System.out.println("Give a name");
                    try {
                        String name;
                        name = br.readLine();
                        Member.searchByName(memberList, name);
                    }
                    catch (IOException ioe) {
                        System.exit(1);
                    }
                    break;

                case 4:
                    System.out.println("Give an id");
                    try {
                        int id;
                        id = Integer.parseInt(br.readLine());
                        Member member = Member.searchById(memberList, id);
                        if (member!=null)   member.show();
                        else System.out.println("ID: " + id + " not found");
                    }
                    catch (IOException ioe) {
                        System.exit(1);
                    }
                    break;

                case 5:
                    System.out.println("Give an id");
                    try {
                        int id;
                        id = Integer.parseInt(br.readLine());
                        Member member = Member.searchById(memberList, id);
                        if (member!=null)
                        {
                            Member newMember = new Member();

                            System.out.println("Give new name");
                            String name = br.readLine();
                            newMember.setName(name);
                            
                            System.out.println("Give new type");
                            String type = br.readLine();
                            newMember.setType(type);
                
                            System.out.println("Give new e-mail");
                            String email = br.readLine();
                            newMember.setEmail(email);

                            Member.update(memberList, id, newMember);                
                        }
                        else System.out.println("ID: " + id + " not found");
                    }
                    catch (IOException ioe) {
                        System.exit(1);
                    }
                    
                    break;

                case 6:
                    System.out.println("Give id");
                    try {
                        int id;
                        id = Integer.parseInt(br.readLine());
                        Member member = Member.searchById(memberList, id);
                        if (member!=null)
                        {
                            Member.delete(memberList, id);
                        }
                        else System.out.println("ID: " + id + " not found");
                    }
                    catch (IOException ioe) {
                        System.exit(1);
                    }
                    break;

                case 7:
                    serializeAllMembers(memberList);        //serialize all members if any exist
                    System.out.println("Goodbye");
                    exitFlag = true;
                    break;

                default:
                    showOptions();
                    break;    
            }            
        }
        System.exit(0);
    }

    private static void showOptions()
    {
        System.out.println("");
        System.out.println("Please choose one of the following:");
        System.out.println("1. Show all members");
        System.out.println("2. Add new member");
        System.out.println("3. Search by name");
        System.out.println("4. Search by id");
        System.out.println("5. Process existing member");
        System.out.println("6. Delete member by id");
        System.out.println("7. Exit");
        System.out.println("");
    }

    static int readOption() {
        int Option = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            Option = Integer.parseInt(br.readLine());
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
        }
        return Option;
    }

    static void serializeAllMembers (List<Member> memberList)
    {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("members.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(memberList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static List<Member> deserializeAllMembers()
    {
        List<Member> memberList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("members.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            memberList = (List<Member>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }

}
