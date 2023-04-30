package Unipi.Java.ex1;

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
    void setType(String type) 
    {
        if(type.equals("student") || type.equals("professor") 
        || type.equals("secretariat") || type.equals("administration"))
            this.type = type;
        else System.out.println("Type must be one of student, professor, secretariat or administration");
    }
    void setEmail(String email) {this.email = email;}
    String getName() {return this.name;}
    int getId() {return this.id;}

    static void insert(List<Member> memberList)
    {
        Member newMember = new Member();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Give id");
            int id = Integer.parseInt(br.readLine());
            newMember.setId(id);
            
            System.out.println("Give name");
            String name = br.readLine();
            newMember.setName(name);
            
            System.out.println("Give type");
            String type = br.readLine();
            newMember.setName(type);

            System.out.println("Give e-mail");
            String email = br.readLine();
            newMember.setName(email);

            memberList.add(newMember);
            System.out.println("New member added");
            System.out.println("");
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    static void showAll (List<Member> memberList)
    {
        System.out.println("");
        for(Member member:memberList)
        {
            member.show();
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
