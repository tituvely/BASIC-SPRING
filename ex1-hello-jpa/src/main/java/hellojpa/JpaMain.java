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

            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();

            // update쿼리가 나감 -> 조회로 가져온 Entity는 영속성 컨텍스트에서 관리됨
            Member findMember = result.get(0);
            findMember.setName("newMember");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
