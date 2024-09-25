package dev.thallesrafaell.FinSync.entities;

import jakarta.persistence.*;
import org.hibernate.sql.ast.tree.insert.Values;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Getters and setters

    public enum  Values {
        ADMIN(1),
        USER(2);

        long roleId;

        Values(long roleId){
            this.roleId = roleId;
        }
    }
}
