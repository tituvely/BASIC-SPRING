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
            member.setAge(10);
            em.persist(member);

            Member member2 = new Member();
            member2.setAge(20);
            em.persist(member2);

            Member member3 = new Member();
            member3.setName("관리자");
            member3.setAge(70);
            em.persist(member3);

            em.flush();
            em.clear();

            String query =
                    "select " +
                            "case when m.age <= 10 then '학생요금' " +
                            "     when m.age >= 60 then '경로요금' " +
                            "     else '일반요금' " +
                            "end " +
                    "from Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();
            for (String s: resultList) {
                System.out.println("s = " + s);
            }

            // COALESCE: 하나씩 조회해서 null이 아니면 반환, null이면 두번째 값 반환
            String query2 = "select coalesce(m.name, '이름없는회원') from Member m";
            resultList = em.createQuery(query2, String.class).getResultList();
            for (String s: resultList) {
                System.out.println("s = " + s);
            }

            // NULLIF: 두 값이 같으면 null 반환, 다르면 첫번째 값 반환
            String query3 = "select nullif(m.name, '관리자') from Member m";
            resultList = em.createQuery(query3, String.class).getResultList();
            for (String s: resultList) {
                System.out.println("s = " + s);
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
