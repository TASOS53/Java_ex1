package Unipi.JAVA.ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;



public class Member implements Serializable {
    private int id;
    private String name;
    private String type;
    private String email;
    
    Member()
    {
        id = 0;
        name = null;
        type = null;
        email = null;
    }

    void setId(int id) {this.id = id;}
    void setName(String name) {this.name = name;}
    void setType(String type) {this.type = type;}
    void setEmail(String email) {this.email = email;}
    String getName() {return this.name;}
    int getId() {return this.id;}

    static void insert(List<Member> memberList)
    {
        Member newMember = new Member();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Δώσε id");
            int id = Integer.parseInt(br.readLine());
            newMember.setId(id);
            
            System.out.println("Δώσε ονοματεπώνυμο");
            String name = br.readLine();
            newMember.setName(name);
            
            System.out.println("Δώσε ιδιότητα");
            String type = br.readLine();
            newMember.setName(type);

            System.out.println("Δώσε e-mail");
            String email = br.readLine();
            newMember.setName(email);

            memberList.add(newMember);
            System.out.println("Το νέο μέλος προστέθηκε");
            System.out.println("");
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    static void showAll (List<Member> memberList)
    {
        System.out.println("");
        int counter=1;
        for(Member member:memberList)
        {
            System.out.println(Integer.toString(counter) + ". " + member.getName());
            counter++;
        }
    }


    void show()
    {
        System.out.println("ID: " + this.getId() + ", Name: " + this.getName() + ".");
    }


    static void searchByName(List<Member> memberList, String name)
    {
        for(Member member:memberList)
        {
            if(member.getName().equals(name))   member.show();
        }
    }


    static Member searchById(List<Member> memberList, int id)
    {
        
        for(Member member:memberList)
        {
            if(member.getId() == id)   return member;
        }
        return null;
    }


    static void delete(List<Member> memberList, Member member)
    {
        memberList.remove(member);   
    }

    static void update(List<Member> memberList, int id, Member newMember)
    {
        Member oldMember = Member.searchById(memberList, id);
        if(oldMember != null)
        {
            oldMember.name = newMember.name;
            oldMember.type = newMember.type;
            oldMember.email = newMember.email;
        }
    }

}
