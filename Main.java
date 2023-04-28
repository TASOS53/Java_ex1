package Unipi.JAVA.ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



class Main
{
    public static void main(String[] args) 
    {
        while(true)
        {
            showOptions();
            switch(readOption())
            {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7: 
                default: break;    
            }            
        }
    }

    private static void showOptions()
    {
        System.out.println("Παρακαλώ επιλέξτε μία από τις κάτωθι ενέργειες");
        System.out.println("1. Προβολή όλων των διαθέσιμων καταγεγραμμένων μελών");
        System.out.println("2. Προσθήκη νέου μέλους");
        System.out.println("3. Αναζήτηση βάσει ονόματος");
        System.out.println("4. Αναζήτηση βάσει κωδικού");
        System.out.println("5. Επεξεργασία στοιχείων μέλους βάσει κωδικού");
        System.out.println("6. Διαγραφή μέλους βάσει κωδικού");
        System.out.println("7. Έξοδος από την εφαρμογή");
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

}
