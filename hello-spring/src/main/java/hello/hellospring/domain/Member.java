package hello.hellospring.domain;

import javax.persistence.*;

@Entity // : JPA가 관리하는 엔티티
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity :  DB가 알아서 생성해주는 것
    private long id;

    // @Column(name = "username")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
