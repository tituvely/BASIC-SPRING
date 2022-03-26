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
            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            Team team2 = new Team();
            team2.setName("team2");
            em.persist(team2);

            Member member = new Member();
            member.setName("member1");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("member2");
            member2.setAge(20);
            member2.setTeam(team);
            em.persist(member2);

            Member member3 = new Member();
            member3.setName("member3");
            member3.setAge(20);
            member3.setTeam(team2);
            em.persist(member3);

            em.flush();
            em.clear();

            // NamedQuery는 애플리케이션 로딩 시점에 쿼리를 검증
            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("name", "member1")
                    .getResultList();

            for(Member m: resultList) {
                System.out.println("m = " + m);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
