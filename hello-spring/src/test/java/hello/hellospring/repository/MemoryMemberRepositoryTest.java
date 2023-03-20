package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemoryMemberRepositoryTest {
   MemberRepository repository = new MemoryMemberRepository();

   // 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있으므로 오류 발생 가능성 있음
   // => 테스트 하나 끝날 때마다 데이터를 초기화 해야함
   @AfterEach
   public void afterEach(){
      repository.clearStore();
   }

   @Test
    public void save(){
       Member member = new Member();
       member.setName("spring");

       repository.save(member);
       Member result = repository.findById(member.getId()).get();
       // System.out.println("result = "+(result == member));

   }

   @Test
   public void findByName(){
      Member member1 = new Member();
      member1.setName("spring1");
      repository.save(member1);

      Member member2 = new Member();
      member2.setName("spring2");
      repository.save(member2);

      Member result = repository.findByName("spring1").get();
      assertThat(result).isEqualTo(member1);
   }

   @Test
   public void findAll(){
      Member member1 = new Member();
      member1.setName("spring1");
      repository.save(member1);

      Member member2 = new Member();
      member2.setName("spring1");
      repository.save(member2);

      List<Member> result = repository.findAll();
      assertThat(result.size()).isEqualTo(2);
   }

}
