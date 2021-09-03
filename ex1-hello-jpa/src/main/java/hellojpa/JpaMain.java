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
            /* 연관관계 저장 */
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            //쿼리를 확인하고 싶다면 영속성 컨텍스트를 초기화해줘야 한다.
            //그렇지 않으면 1차 캐시에서 가져옴.
            em.flush(); //쿼리 날리기
            em.clear(); //영속성 컨텍스트 초기화

            Team findTeam = em.find(Team.class, team.getId());

            int memberSize = findTeam.getMembers().size();

            System.out.println(memberSize);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
