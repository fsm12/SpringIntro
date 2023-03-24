package hello.core.scan.filter;

// 클래스 선언 시 Annotation 선택

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
