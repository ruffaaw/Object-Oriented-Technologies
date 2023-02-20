package note;

import entity.Notes;
import jakarta.persistence.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Note {
    private String title;
    private String content;
    Scanner scanner = new Scanner(System.in);

    public void Note() {
        title = "";
        content = "";
    }

    public boolean createNewNote(int accountID) {
        System.out.println("Enter the title of the note: ");
        setTitle(scanner.nextLine());
        if (!lengthUsername(getTitle())) {
            System.err.print("Too long title ! Title can not be longer than 100 characters.");
            return true;
        }
        System.out.println("Enter the content of the note, use \"Ctrl+D\" to exit");
        StringBuilder stringBuilder = new StringBuilder();
        String text = "";
        while (scanner.hasNextLine()) {
            text = new String(scanner.nextLine());
            stringBuilder.append(text).append("\n");
        }
        setContent(String.valueOf(stringBuilder));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        int lastNotesID;
        try {
            lastNotesID = (int) entityManager.createNativeQuery("SELECT notesID FROM notes ORDER BY notesID DESC LIMIT 1")
                    .getSingleResult();
        } catch (NoResultException e) {
            lastNotesID = 0;
        }

        try {
            entityTransaction.begin();
            Notes notes = new Notes();
            notes.setNotesId(lastNotesID + 1);
            notes.setNotesAccountId(accountID);
            notes.setTitle(getTitle());
            notes.setContent(getContent());
            entityManager.persist(notes);
            entityTransaction.commit();
        } finally {
            if (entityTransaction.isActive())
                entityTransaction.rollback();
        }
        entityManager.close();
        entityManagerFactory.close();
        return false;
    }

    public void readNote(int accountID) {
        List listNotesIDs = getNotesIDs(accountID);
        displayTitles(listNotesIDs);
        displayContent(listNotesIDs);
    }

    public void deleteNote(int accountID) {
        List listNotesIDs = getNotesIDs(accountID);
        displayTitles(listNotesIDs);
        delete(listNotesIDs);
    }

    public void editNote(int accountID) {
        List listNotesIDs = getNotesIDs(accountID);
        displayTitles(listNotesIDs);
        edit(listNotesIDs);

    }

    private List getNotesIDs(int accountID) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createNativeQuery("SELECT notesID FROM notes where notesAccountID = " + accountID)
                .getResultList();
    }

    private void displayTitles(List listNotesIDs) {
        List listTitles = null;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        for (int i = 0; i < listNotesIDs.size(); i++) {
            System.out.println(i + 1 + ". " + entityManager.createNativeQuery("SELECT title FROM notes where notesID = " + listNotesIDs.get(i))
                    .getSingleResult());
        }
    }

    private void displayContent(List listNotesIDs) {
        System.out.println("Select the note numer: ");
        int notesID = 0;
        try {
            notesID = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Wrong sign!");
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println(entityManager.createNativeQuery("SELECT title FROM notes where notesID = " + listNotesIDs.get(notesID - 1))
                .getSingleResult() + "\n");
        System.out.println(entityManager.createNativeQuery("SELECT content FROM notes where notesID = " + listNotesIDs.get(notesID - 1))
                .getSingleResult());
    }

    private void delete(List listNotesIDs) {
        System.out.println("Select the note numer: ");
        int notesID = 0;
        try {
            notesID = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Wrong sign!");
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.createNativeQuery("DELETE FROM notes WHERE notesID = " + listNotesIDs.get(notesID - 1))
                    .executeUpdate();
            entityTransaction.commit();
        } finally {
            if (entityTransaction.isActive())
                entityTransaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
            scanner.close();
        }
    }

    private void edit(List listNodesIDs) {
        int notesID = 0;
        try {
            notesID = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Wrong sign!");
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        System.out.println("Enter new content, use \"Ctrl+D\" to exit");
        StringBuilder stringBuilder = new StringBuilder();
        String text = "";
        while (scanner.hasNextLine()) {
            text = new String(scanner.nextLine());
            stringBuilder.append(text).append("\n");
        }
        setContent(String.valueOf(stringBuilder));
        try {
            entityTransaction.begin();
            entityManager.createNativeQuery("UPDATE notes SET content = '" + getContent() + "' WHERE notesID = " + listNodesIDs.get(notesID - 1))
                    .executeUpdate();
            entityTransaction.commit();
        } finally {
            if (entityTransaction.isActive())
                entityTransaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private String getTitle() {
        return title;
    }

    private String getContent() {
        return content;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setContent(String content) {
        this.content = content;
    }

    private boolean lengthUsername(String title) {
        return title.length() < 100;
    }

}
