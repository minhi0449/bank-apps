package com.applus_bank.handler;

import com.applus_bank.handler.exception.DataDeliveryException;
import com.applus_bank.handler.exception.RedirectException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/*
    @RestController = @Controller + @ResponseBody
 */



// 중앙에서 관리한 예외 처리 만들어 보기
// 모든 컨트롤러에서 발생하는 예외를 처리하는 클래스
@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * 모든 예외(Exception) 발생 시 실행됨
     * 예외 정보를 콘솔에 출력하여 로그를 남김
     * @param e
     */
    // 모든 예외 클래스를 알 수 없기 때문에 로깅으로 확인할 수 있도록 적용
    @ExceptionHandler({Exception.class}) // 모든 예외처리의 최상위
    public void exception(Exception e){
        System.out.println("-------------------");
        System.out.println("여기는 예외클레스 : " + e.getClass().getName()); // 예외 클래스 이름 출력
        System.out.println("여기는 예외메시지 : " + e.getMessage()); // 예외 메시지 출력
        System.out.println("-------------------");
    }

    /**
     * DataDeliveryException 발생 시 실행되는 메서드
     * 사용자에게 JavaScript alert 창을 띄우고 이전 페이지로 이동하게 만듦
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(DataDeliveryException.class)
    public String dataDeliveryException(DataDeliveryException e){
        // 문자열을 효율적으로 만들기 위해서 StringBuffer 사용
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert(' "+e.getMessage()+" ');"); // 에러 메시지를 alert로 표시
        sb.append("window.history.back()"); // 이전 페이지로 이동
        sb.append("</script>");
        return sb.toString(); // HTML 응답으로 반환
    }

    public String unAuthorizedException(Exception e){
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+e.getMessage()+"')");
        sb.append("window.history.back();");
        sb.append("</script>");
        return sb.toString();
    }

    @ExceptionHandler(RedirectException.class)
    public ModelAndView redirectException(RedirectException e){
        ModelAndView modelAndView = new ModelAndView("/errorPage");
        //modelAndView.addObject("statusCode", e.getStatus.value());
        modelAndView.addObject("statusCode", e.getStatus().value());
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }


}
