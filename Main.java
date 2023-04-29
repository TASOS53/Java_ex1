package Unipi.JAVA.ex1;

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
        memberList = deserializeAllMembers();
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
                    System.out.println("Εισάγετε όνομα");
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
                    System.out.println("Εισάγετε κωδικό");
                    try {
                        int id;
                        id = Integer.parseInt(br.readLine());
                        Member member = Member.searchById(memberList, id);
                        if (member!=null)   member.show();
                        else System.out.println("Το id: " + id + " δεν βρέθηκε");
                    }
                    catch (IOException ioe) {
                        System.exit(1);
                    }
                    break;

                case 5:
                    System.out.println("Εισάγετε κωδικό");
                    try {
                        int id;
                        id = Integer.parseInt(br.readLine());
                        Member member = Member.searchById(memberList, id);
                        if (member!=null)
                        {
                            Member newMember = new Member();

                            System.out.println("Δώσε νέο ονοματεπώνυμο");
                            String name = br.readLine();
                            newMember.setName(name);
                            
                            System.out.println("Δώσε νέα ιδιότητα");
                            String type = br.readLine();
                            newMember.setName(type);
                
                            System.out.println("Δώσε νέο e-mail");
                            String email = br.readLine();
                            newMember.setName(email);

                            Member.update(memberList, id, newMember);
                
                        }
                        else System.out.println("Το id: " + id + " δεν βρέθηκε");
                    }
                    catch (IOException ioe) {
                        System.exit(1);
                    }
                    
                    break;

                case 6:
                    System.out.println("Εισάγετε κωδικό");
                    try {
                        int id;
                        id = Integer.parseInt(br.readLine());
                        Member member = Member.searchById(memberList, id);
                        Member.delete(memberList, member);
                    }
                    catch (IOException ioe) {
                        System.exit(1);
                    }
                    break;

                case 7:
                    serializeAllMembers(memberList);
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
        System.out.println("Παρακαλώ επιλέξτε μία από τις κάτωθι ενέργειες");
        System.out.println("1. Προβολή όλων των διαθέσιμων καταγεγραμμένων μελών");
        System.out.println("2. Προσθήκη νέου μέλους");
        System.out.println("3. Αναζήτηση βάσει ονόματος");
        System.out.println("4. Αναζήτηση βάσει κωδικού");
        System.out.println("5. Επεξεργασία στοιχείων μέλους βάσει κωδικού");
        System.out.println("6. Διαγραφή μέλους βάσει κωδικού");
        System.out.println("7. Έξοδος από την εφαρμογή");
        System.out.println("");
    }

    private static int readOption() {
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
