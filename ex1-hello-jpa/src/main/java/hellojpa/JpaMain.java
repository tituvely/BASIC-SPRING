package hellojpa;

import javax.persistence.*;
import java.util.List;

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
            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            em.flush();
            em.clear();

            // 임베디드 타입 프로젝션
            em.createQuery("select m.homeAddress from Member m", Address.class).getResultList();
            // 엔티티 타입 프로젝션 - 조인
            em.createQuery("select m.team from Member m", Team.class).getResultList();
            // 스칼라 타입 프로젝션
            em.createQuery("select m.name from Member m").getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
