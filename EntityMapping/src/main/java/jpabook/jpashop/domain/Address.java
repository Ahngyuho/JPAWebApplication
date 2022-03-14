package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    //값 타입은 변경이 불가능하게 만들어야 함 그래서 setter는 만들어 두지 않음
    //이런식으로 jpa는 기본 생성자를 필요로 하므로(리플렉션 프록시 제공 가능) 넣어줌
    //이걸 protected로 해두면 남들이 봤을 때 함부로 new를 사용하면 안됨을 알려줌 + jpa스펙상 만들어두었음을 알려줌
    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
