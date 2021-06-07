package com.fgt.walletsystem.entities;
import com.fgt.walletsystem.models.Audit;
import javax.persistence.*;

@Entity(name = "user_accounts")
public class User extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="user_seqgen", sequenceName="User_SEQ",allocationSize=1)
    private long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String customerCode;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Wallet wallet;

    public void addWallet(Wallet wallet) {
        wallet.setUser( this );
        this.wallet = wallet;
    }

    public void removeDetails() {
        if ( wallet != null ) {
            wallet.setUser( null );
            this.wallet = null;
        }
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
