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

            TypedQuery<Member> query1 = em.createQuery("select m from Member m where m.name = :name", Member.class);
            query1.setParameter("name", member.getName());
            Member singleResult = query1.getSingleResult();
            System.out.println("singleResult = " + singleResult.getName());

            Member singleResult2 = em.createQuery("select m from Member m where m.name = ?1", Member.class)
                    .setParameter(1, member.getName())
                    .getSingleResult();
            System.out.println("singleResult2 = " + singleResult2.getName());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
