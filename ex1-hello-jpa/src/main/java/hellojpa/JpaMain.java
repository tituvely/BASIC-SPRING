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

            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);

            // 결과 조회 API
            // query.getResultList(): 결과가 없으면 빈 리스트 반환
            // query.getSingleResult()
            //  - 결과가 없으면: javax.persistence.NoResultException
            //  - 둘 이상이면: javax.persistence.NonUniqueResultException
            List<Member> resultList = query1.getResultList();
            Member singleResult = query1.getSingleResult();
            System.out.println("singleResult = " + singleResult.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
