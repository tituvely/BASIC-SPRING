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
            for (int i = 0; i < 100; i++) {
            }
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setAge(10);

            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m inner join m.team t", Member.class).getResultList();
            List<Member> result2 = em.createQuery("select m from Member m left join m.team t", Member.class).getResultList();
            List<Member> result3 = em.createQuery("select m from Member m, Team t where m.name = t.name", Member.class).getResultList();

            // 조인 대상 필터링
            List<Member> result4 = em.createQuery("select m from Member m left join Team t on t.name = 'teamA' ", Member.class).getResultList();
            System.out.println("result4.size() = " + result4.size());
            System.out.println("member = " + result4.get(0));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
