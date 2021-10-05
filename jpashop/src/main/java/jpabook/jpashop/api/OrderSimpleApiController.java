package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * xToOne(ManyToOne, OneToOne) 관계 최적화
 * Order
 * Order -> Member
 * Order -> Delivery
 *
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    /**
    * V1. 엔티티 직접 노출
    * - 양방향 관계 문제 발생 -> @JsonIgnore
    *   - 엔티티를 직접 노출할 때는 양방향 연관관계가 걸린 곳은 꼭 한곳을 @JsonIgnore 처리 해야 한다.
    * 안그러면 양쪽을 서로 호출하면서 무한 루프가 걸린다.
    * - Hibernate5Module 모듈 등록, LAZY=null 처리
    *   - order member 와 order address 는 지연 로딩이다. 따라서 실제 엔티티 대신에 프록시 존재
    *   - jackson 라이브러리는 기본적으로 이 프록시 객체를 json으로 어떻게 생성해야 하는지 모름 예외 발생
    *   - Hibernate5Module을 스프링 빈으로 등록하면 해결(스프링 부트 사용중)
    */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기화
        }
        return all;
    }

//    앞에서 계속 강조했듯이 엔티티를 API 응답으로 외부로 노출하는 것은 좋지 않다.
//    따라서 Hibernate5Module를 사용하기 보다는 DTO로 변환해서 반환하는 것이 더 좋은 방법이다.

    /**
     * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X)
     * - 단점: 지연로딩으로 쿼리 N번 호출
     * 쿼리가 총 1 + N + N번 실행된다. (v1과 쿼리수 결과는 같다.)
     * order 조회 1번(order 조회 결과 수가 N이 된다.) order -> member 지연 로딩 조회 N 번
     * order -> delivery 지연 로딩 조회 N 번
     * (참고: 지연로딩은 영속성 컨텍스트에서 조회하므로, 이미 조회된 경우 쿼리를 생략한다.)
     */
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());

        return result;
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDateTime();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }

    }

}
