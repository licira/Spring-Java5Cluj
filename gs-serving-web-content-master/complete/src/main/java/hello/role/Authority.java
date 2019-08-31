package hello.role;

import javax.persistence.*;

@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Authority() {
    }

    public Authority(Role name) {
        this.role = name;
    }
    // getters and setters
}