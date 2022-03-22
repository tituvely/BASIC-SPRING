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
            member.setAge(20);
            em.persist(member);

            em.flush();
            em.clear();

            // 프로젝션에서 여러 값을 조회하는 방법
            // 1) Query 타입으로 조회
            List resultList = em.createQuery("SELECT m.name, m.age FROM Member m").getResultList();

            Object o = resultList.get(0);
            Object[] result = (Object[]) o;
            System.out.println("name = " + result[0]);
            System.out.println("age = " + result[1]);

            // 2) Object[] 타입으로 조회
            List<Object[]> resultList2 = em.createQuery("SELECT m.name, m.age FROM Member m").getResultList();

            Object[] result2 = (Object[]) o;
            System.out.println("name = " + result2[0]);
            System.out.println("age = " + result2[1]);

            // BEST!!!!!
            // 3) new 명령어로 조회
            List<MemberDTO> resultList3 = em.createQuery("SELECT new hellojpa.MemberDTO(m.name, m.age) FROM Member m", MemberDTO.class).getResultList();

            MemberDTO memberDTO = resultList3.get(0);
            System.out.println("name = " + memberDTO.getName());
            System.out.println("age = " + memberDTO.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
