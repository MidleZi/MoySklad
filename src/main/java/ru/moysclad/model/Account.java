package ru.moysclad.model;


import javax.persistence.*;

@Entity
@Table(name = "account")
@NamedQuery(name = "Account.findAll", query = "SELECT p FROM Account p")
public class Account {

    @Id
    @Column(name = "id")
    private String id;

    @Version
    private Integer version = 0;

    @Column(name = "sum")
    private Long sum = 0L;

    public Account(){

    }

    public Account(String id, Long sum){
        this.id = id;
        this.sum = sum;
    }

    public Account(String id) {
        this.id = id;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{id:");
        builder.append(getId());
        builder.append("{sum:");
        builder.append(getSum());
        builder.append("}");

        return builder.toString();
    }

    public String getId() {
        return id;
    }

    public Long getSum() {
        return sum;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
