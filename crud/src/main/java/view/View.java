package view;

import login.Login;
import note.Note;
import registration.Registration;

import java.util.Scanner;

public class View {
    private Scanner scanner;
    private int accountID;
    public View(){
        scanner = new Scanner(System.in);
    }
    public void MainView() {
        chooseToLoginOrRegister();
        notesMenu();
    }

    public void chooseToLoginOrRegister(){
        boolean bool = true;
        while (bool) {
            System.out.print("1.Login\n2.Registration\n3.Exit\n");
            switch (scanner.nextLine()) {
                case "1":
                    Login login = new Login();
                    setAccountID(login.signIn());
                    if(accountID != 0){
                        bool = false;
                    }
                    break;
                case "2":
                    Registration registration = new Registration();
                    setAccountID(registration.register());
                    if(accountID != 0)
                        bool = false;
                    break;
                case "3":
                    System.exit(1);
                default:
                    System.err.print("Wrong choice!\n");
            }
        }
    }

    public void notesMenu(){
        boolean bool = true;
        Note note = new Note();
        String choose = "";
        while(bool) {
            choose = "";
            System.out.println("\n\n1.Create new note\n2.Read note\n3.Edit note\n4.Delete note");
            choose = scanner.nextLine();
            switch(choose) {
                case "1":
                    bool = note.createNewNote(accountID);
                    break;
                case "2":
                    note.readNote(accountID);
                    bool = false;
                    break;
                case"3":
                    note.editNote(accountID);
                    bool = false;
                    break;
                case "4":
                    note.deleteNote(accountID);
                    bool = false;
                    break;
                default:
                    System.out.println("cos tam");
            }
        }
    }
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

}
