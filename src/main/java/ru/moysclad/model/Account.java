package ru.moysclad.model;


import javax.persistence.*;

@Entity
@Table(name = "account")
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "name")
    private Long name;

    @Version
    private Integer version = 0;

    @JoinColumn(name = "sum")
    private Long sum = 0L;

    public Account(){

    }

    public Account(Long name, Long sum){
        this.name = name;
        this.sum = sum;
    }

    public Account(Long name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{id:");
        builder.append(getId());
        builder.append("{name:");
        builder.append(getName());
        builder.append("{sum:");
        builder.append(getSum());
        builder.append("}");

        return builder.toString();
    }

    public Long getId() {
        return id;
    }

    public Long getName() {
        return name;
    }

    public Long getSum() {
        return sum;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
