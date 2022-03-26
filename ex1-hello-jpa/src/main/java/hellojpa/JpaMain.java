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

            // FLUSH 자동 호출 -> 데이터베이스에 query가 나가기 때문
            // 벌크 연산: 쿼리 한 번으로 여러 테이블 로우 변경
            // UPDATE, DELETE 지원
            int resultCount = em.createQuery("update Member m set m.age = 50")
                    .executeUpdate();
            System.out.println("resultCount = " + resultCount);

            // 벌크 연산은 영속성 컨텍스트를 무시하고 데이터베이스에 직접 쿼리를 날리기 때문에 정합성이 안 맞는 경우가 생긴다.
            System.out.println("member = " + member.getAge());
            System.out.println("member2 = " + member2.getAge());
            System.out.println("member3 = " + member3.getAge());

            // 따라서
            // 1. 벌크 연산부터 먼저 실행하거나
            // 2. 벌크 연산 수행 후 영속성 컨텍스트를 초기화한다.

            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            Member findMember2 = em.find(Member.class, member2.getId());
            Member findMember3 = em.find(Member.class, member3.getId());
            System.out.println("member = " + findMember.getAge());
            System.out.println("member2 = " + findMember2.getAge());
            System.out.println("member3 = " + findMember3.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
