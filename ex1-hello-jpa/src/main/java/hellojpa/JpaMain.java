package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 엔티티 매니저는 쓰레드간에 공유X (사용하고 버려야 한다).
        EntityManager em = emf.createEntityManager();
        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // 영속
            Member member = em.find(Member.class, 1L);
            member.setName("A");

            System.out.println("=========================");
            // 변경 감지(더티 체킹 - Dirty Checking)
            // update나 save같은 메소드를 실행하지 않았는데 자동으로 update 쿼리가 날려진다.

            // 영속성 컨텍스트에서는 엔티티와 스냅샷을 비교해서, 값이 다르면 UPDATE SQL을 생성한다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
