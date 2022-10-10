package my.virkato.kata312.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.UniqueConstraint;
import javax.persistence.GenerationType;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id", "authority"}))
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;

    public RoleEntity(String role) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        setAuthority(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleEntity entity)) return false;
        return authority.equals(entity.authority);
    }

    @Override
    public int hashCode() {
        return authority.hashCode();
    }

    @Override
    public String toString() {
        return authority.replaceAll("^ROLE_", "");
    }
}
