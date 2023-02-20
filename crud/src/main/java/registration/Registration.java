package registration;

import entity.Account;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

public class Registration {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private String username;
    private String password;
    private String name;
    Scanner scanner = new Scanner(System.in);

    public Registration() {
        username = "";
        password = "";
        name = "";
    }

    public int register() {
        int accountID = 0;
        System.out.println("Enter username: ");
        setUsername(scanner.nextLine());
        if (!lengthUsername(username)) {
            System.err.print("Too long username ! Username can not be longer than 100 characters.");
            accountID = 0;
        } else if (!checkingTheUsername()) {
            System.err.println("There is already a user with the given username, please choose another name");
            accountID = 0;
        } else {
            System.out.println("Enter password: ");
            setPassword(encoder.encode(scanner.nextLine()));
            System.out.println("Enter name: ");
            setName(scanner.nextLine());
            creatingNewAccount();
            accountID = getTheAccountID();
        }
        return accountID;
    }

    private boolean checkingTheUsername() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createNativeQuery("SELECT username FROM account  WHERE username = '" + getUsername() + "'")
                .getResultList()
                .isEmpty();
    }

    private void creatingNewAccount() {
        int lastAccountID;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            lastAccountID = (int) entityManager.createNativeQuery("SELECT accountID FROM account ORDER BY accountID DESC LIMIT 1")
                    .getSingleResult();
        } catch (NoResultException e) {
            lastAccountID = 0;
        }
        try {
            entityTransaction.begin();
            Account account = new Account();
            account.setAccountId(lastAccountID + 1);
            account.setUsername(getUsername());
            account.setPassword(getPassword());
            account.setName(getName());
            entityManager.persist(account);
            entityTransaction.commit();
        } finally {
            if (entityTransaction.isActive())
                entityTransaction.rollback();
        }
        entityManager.close();
        entityManagerFactory.close();
    }

    private int getTheAccountID() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (int) entityManager.createNativeQuery("SELECT accountID FROM account ORDER BY accountID DESC LIMIT 1")
                .getSingleResult();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    private boolean lengthUsername(String username) {
        return username.length() < 100;
    }

}
