package entity;

import jakarta.persistence.*;

@Entity
public class Notes {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "notesID")
    private int notesId;
    @Basic
    @Column(name = "notesAccountID")
    private int notesAccountId;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "notesAccountID", referencedColumnName = "accountID", nullable = false, insertable = false, updatable = false)
    private Account accountByNotesAccountId;

    public int getNotesId() {
        return notesId;
    }

    public void setNotesId(int notesId) {
        this.notesId = notesId;
    }

    public int getNotesAccountId() {
        return notesAccountId;
    }

    public void setNotesAccountId(int notesAccountId) {
        this.notesAccountId = notesAccountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notes notes = (Notes) o;

        if (notesId != notes.notesId) return false;
        if (notesAccountId != notes.notesAccountId) return false;
        if (title != null ? !title.equals(notes.title) : notes.title != null) return false;
        if (content != null ? !content.equals(notes.content) : notes.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = notesId;
        result = 31 * result + notesAccountId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    public Account getAccountByNotesAccountId() {
        return accountByNotesAccountId;
    }

    public void setAccountByNotesAccountId(Account accountByNotesAccountId) {
        this.accountByNotesAccountId = accountByNotesAccountId;
    }
}
