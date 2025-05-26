package com.sicong.spring.taskmanager.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String username;

        @Column(nullable = false)
        private String password;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Role role;

        public User(){
        }

        public User(Long id, String email, String username, String password, Role role){
                this.id = id;
                this.email=email;
                this.username=username;
                this.password=password;
                this.role= role;
        }

        public long getId() {
                return id;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
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

        public Role getRole() {
                return role;
        }

        public void setRole(Role role) {
                this.role = role;
        }

        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", email='" + email + '\'' +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", role=" + role +
                        '}';
        }
}




