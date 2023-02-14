package de.heidelberg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "t_user")
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_LOGIN, query = "SELECT u FROM User u WHERE u.name = :name"),
        @NamedQuery(name = User.FIND_BY_LOGIN_PASSWORD, query = "SELECT u FROM User u WHERE u.name = :name AND u"
                + ".password = :password")})
public class User implements Serializable {

    public static final String FIND_BY_LOGIN = "User.findByLogin";
    public static final String FIND_BY_LOGIN_PASSWORD = "User.findByLoginAndPassword";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String password;

    @Column(length = 50, name = "name", nullable = false)
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @Column
    private String email;

    private UserRole role;

    private VerificationStatus verificationStatus;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public VerificationStatus getVerificationStatus()
    {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus)
    {
        this.verificationStatus = verificationStatus;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
