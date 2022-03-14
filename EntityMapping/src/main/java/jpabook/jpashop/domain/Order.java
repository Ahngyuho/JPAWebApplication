package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //테이블 명 설정가능
public class Order {

    @Id @GeneratedValue
    @Column(name = "orders_id") //컬럼명 설정 가능
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //즉시로딩 -> 지연 로딩으로 바꿔야 한다.
    @JoinColumn(name = "member_id") //fk가 member의 id가 됨 연관관계의 주인
                                    //이 값이 바뀔 때 변경이 이루어짐 반대편은 값에 영향을 주지 않음
    private Member member;

    //이렇게 즉시로딩으로 해놓으면 조회할 때 order과 연관된 것들을 한번에 다 조회함
    //order를 조회할 때 member를 join해서 가져옴 em.find()로 하나만 조회할 경우
    //JPQL select o From order o ->이게 sql select * from order로 번역됨
    //order가 100개라면 100개의 쿼리가 나가게 됨 N+1문제 발생
    //연관 관계에서 발생하는 이슈로 연관 관계가 설정된 엔티티를 조회
    // 할 경우에 조회된 데이터 갯수(n) 만큼 연관관계의 조회 쿼리가 추가로 발생하여 데이터를 읽어오게 된다

    //cascadeALL 해주게 되면 이 orders를 persist해주기만 해도 반대편 사이드의 것도 같이 persist됨 끝이 @~One일 경우
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL) //일대일 관계의 주인은 액세스를 많이 하는 쪽으로 함 오더를 보고 딜리버리 확인하므로
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //==연관관계 메서드== 이 연관관계 메서드의 위치는 상대적으로 컨트롤하는 입장이 되는 곳이 좋음//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this); //현재 이 order의 member를 넣어주고 이 member의 orders에 이 order를 넣어주는 것것
   }

   public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
   }

   public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
   }
}
