package com.example.EcoCity.models.entities;

import com.example.EcoCity.models.enums.RecordState;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 * <p>
 * Сущость пользователя
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "record_state", nullable = false)
    private RecordState recordState;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "last_online_date", nullable = false)
    private LocalDateTime lastOnlineDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public static class Builder{
        private Long id;
        private String email;
        private String surname;
        private String name;
        private String password;
        private RecordState recordState;
        private LocalDateTime createDate;
        private LocalDateTime lastOnlineDate;
        private Set<Role> roles;

        private Builder(){}
        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder recordState(RecordState recordState){
            this.recordState = recordState;
            return this;
        }

        public Builder createDate(LocalDateTime createDate){
            this.createDate = createDate;
            return this;
        }

        public Builder lastOnlineDate(LocalDateTime lastOnlineDate){
            this.lastOnlineDate = lastOnlineDate;
            return this;
        }

        public Builder roles(Set<Role> roles){
            this.roles = roles;
            return this;
        }

        public User build(){
            User user = new User();
            user.id = this.id;
            user.email = this.email;
            user.surname = this.surname;
            user.name = this.name;
            user.password = this.password;
            user.recordState = this.recordState;
            user.createDate = this.createDate;
            user.lastOnlineDate = this.lastOnlineDate;
            user.roles = this.roles;
            return user;
        }
    }

    public Builder builder(){
        return new Builder();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Objects.equals(recordState, RecordState.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(recordState, RecordState.ACTIVE);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RecordState getRecordState() {
        return recordState;
    }

    public void setRecordState(RecordState recordState) {
        this.recordState = recordState;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastOnlineDate() {
        return lastOnlineDate;
    }

    public void setLastOnlineDate(LocalDateTime lastOnlineDate) {
        this.lastOnlineDate = lastOnlineDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
