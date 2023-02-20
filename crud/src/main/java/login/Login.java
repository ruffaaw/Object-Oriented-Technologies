package login;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

public class Login {
    private String username;
    private String password;
    Scanner scanner = new Scanner(System.in);
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Login() {
        username = "";
        password = "";
        Scanner scanner = new Scanner(System.in);
    }

    public int signIn() {
        int accountID = 0;
        System.out.print("Enter username: ");
        setUsername(scanner.nextLine());
        if (!lengthUsername(username)) {
            System.err.print("Too long username ! Username can not be longer than 100 characters.\n");
            accountID = 0;
        } else {
            System.out.print("Enter password: ");
            setPassword(scanner.nextLine());
            if (checkingTheLogin()) {
                System.err.println("Incorrect login or password!\n");
                accountID = 0;
            } else if (!checkingThePassword()) {
                System.err.println("Incorrect login or password!\n");
                accountID = 0;
            } else {
                accountID = getTheAccountID();
            }
        }
        return accountID;
    }

    private boolean checkingTheLogin() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createNativeQuery("SELECT username FROM account WHERE username = '" + getUsername() + "'")
                .getResultList()
                .isEmpty();
    }

    private boolean checkingThePassword() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String password = (String) entityManager.createNativeQuery("SELECT password FROM account WHERE username= '" + getUsername() + "'")
                .getSingleResult();
        return encoder.matches(getPassword(), password);
    }


    private int getTheAccountID() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return (int) entityManager.createNativeQuery("SELECT accountID FROM account WHERE username = '" + getUsername() + "'")
                    .getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }

    private String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private boolean lengthUsername(String username) {
        return username.length() < 100;
    }
}