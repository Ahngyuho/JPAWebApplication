package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded   //내장타입임을 알려줌
    private Address address;

    @OneToMany(mappedBy = "member") //order테이블의 member에 의해 매핑 된다는 의미임
    private List<Order> orders = new ArrayList<>();
}
