package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 설정 정보
@ComponentScan( // 컴포넌트 스캔
        // 탐색위치 지정
        // 양식 : [basePackages = {"hello.core", "hello.service"}]
        // basePackages = "hello.core.member",

        /*
        컴포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록됨 (AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고, 실행)
        => excludeFilters를 이용해서 설정 정보는 컴포넌트 스캔 대상에서 제외함
           보통 설정 정보를 컴포넌트 스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택
        */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
//    // MemoryMemberRepository의 이름도 memoryMemberRepository
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
