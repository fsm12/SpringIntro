package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
//    private DataSource dataSource;
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    // JPA
//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

    // Spring Data JPA
    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }

    // Spring Data JPA
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    AOP - TimeTraceAop에 컴포넌트 스캔 or 아래코드
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository(){
//        // return new MemoryMemberRepository();
//        // return new JdbcMemberRepository(dataSource);
//        // return new JdbcTemplateMemberRepository(dataSource);
//        // return new JpaMemberRepository(em);
//    }
}
