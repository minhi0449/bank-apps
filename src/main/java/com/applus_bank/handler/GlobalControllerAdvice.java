package com.applus_bank.handler;

import com.applus_bank.handler.exception.DataDeliveryException;
import org.springframework.web.bind.annotation.ControllerAdvice;

/*
    @RestController = @Controller + @ResponseBody
 */

// 중앙에서 관리한 예외 처리 만들어 보기
@ControllerAdvice
public class GlobalControllerAdvice {

    // 모든 예외 클래스를 알 수 없기 때문에 로깅으로 확인할 수 있도록 적용
    public void exception(Exception e){
        System.out.println("-------------------");
        System.out.println(e.getClass().getName());
        System.out.println(e.getMessage());
        System.out.println("-------------------");
    }

    // 데이터로 예외를 클라이언트에게 전달
    // 데이터로 클라이언트에게 전달
//    public String dataDeliveryException(DataDeliveryException e){
//        // 버퍼 사용하는 이유는 매번 생성 되는 것을 막기 위해서 사용
//        StringBuffer sb = new StringBuffer();
//        sb.append("<script>");
//        sb.append("alert(' "+e.getMessage()+" ');");
//        // sb.append("window.history.")
//        sb.append("</script>");
//    }


}
