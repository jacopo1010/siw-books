package it.uniroma3.siw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class Credenziali {

    public static final String DEFAULT_ROLE = "UTENTE";
    public static final String ADMIN_ROLE = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;
    @NotBlank
    @Column(nullable = false)
    private String password;
    private String ruolo;
    @OneToOne(cascade = CascadeType.ALL)
    private Utente utente;
    @Column(name ="reset_passwors_token")
    private String resetPasswordToken;

    public Credenziali(String username, String password, String ruolo,Utente u) {
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
        this.utente = u;
    }

    public Credenziali(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Credenziali other = (Credenziali) obj;
        return this.id == other.getId() && this.username.equals(other.getUsername());
    }

    @Override
    public int hashCode() {
         return Objects.hash(id,username);
    }

    @Override
    public String toString() {
        return "Credenziali{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", utente=" + this.utente.toString() +
                '}';
    }
}
