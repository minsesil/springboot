package exam4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test04_delete {

	public static void main(String[] args) {
		// 무조건 필수
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			Member4 user = em.find(Member4.class, "test@test.com");
			if(user == null) {
				System.out.println("존재하지 않습니다");
				return;
			}
			em.remove(user);     // 영속성 컨테이너에서 삭제
			
			em.getTransaction().commit();
			System.out.println("탈퇴처리 하였습니다");
		} catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		
		em.close();
		emf.close();
	}

}