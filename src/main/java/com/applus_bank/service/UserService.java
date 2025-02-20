package com.applus_bank.service;

import com.applus_bank.dto.SignInDTO;
import com.applus_bank.dto.SignUpDTO;
import com.applus_bank.handler.exception.DataDeliveryException;
import com.applus_bank.handler.exception.RedirectException;
import com.applus_bank.repository.interfaces.UserRepository;
import com.applus_bank.repository.model.User;
import com.applus_bank.utils.Define;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {

    // @Autowired : 가독성
    private final UserRepository userRepository;
    // private final PasswordEncoder passwordEncoder;

    // 회원가입 - signProc
    @Transactional
    public void createUser(SignUpDTO dto) {
        log.info("↗️ 여기는 서비스 - 회원가입 createUser()");
        // HTTP 응답으로 클라이언트에게 전달한 오류메시지는 최소한으로 유지하고,
        // 보안 및 사용자 경험 측면에서 민감한 정보를 노출하지 않도록 합니다.
        int result = 0;
        log.info("result : "+ result);
        try {
           result = userRepository.insert(dto.toUser());
            log.info("try - result : "+ result);

           // 여기서 예외 처리를 하면 상위 catch 블록에서 예외를 잡는다
        }catch (DataAccessException e){
            log.info("DataAccessException 데이터 저장소 오류 : "+ e.getMessage());
            // DataAccessException -> Spring 의 데이터 엑세스 예외 클래스로,
            // 데이터베이스 연결이나 쿼리 실행과 관련된 문제를 처리 합니다. --> 그럼 유저 만드는 로직이 실패하면? --> DataAccessException 이 실행되나?
            throw new DataDeliveryException("잘못된 처리 입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            // 그 외 예외 처리 - 페이지 이동 처리
            log.info("Exception 모든 알 수 없는 오류 : "+ e.getMessage());
            throw new RedirectException("알 수 없는 오류", HttpStatus.SERVICE_UNAVAILABLE);
         } // end of try-catch

        // 예외 클래스가 발생이 안 되지만, 프로세스 입장에서 예외 상황으로 바라봄
        if(result != 1){
            // 삽입된 행의 수가 1이 아닌 경우 예외 발생
            log.info("result 가 1이 아니면 예외 ㅠㅠ" + result);
            throw new DataDeliveryException("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
            // MyBatis 에서 insert 는 쿼리문 성공 시 --> 성공한 행(Row)의 합계를 반환 = 1
            // 반면, 실패 시 --> '0' 을 반환
            // 그래서 if(result != 1) 만약 결과값이 1이 아닐 경우
            // 결과값이 실패일 경우 --> "회원 가입 실패" 를 반환해라 라는 뜻의 if문!
        }

    } // end of createUser (회원가입)

    // 사용자 정보 조회
    public User readUser(SignInDTO dto) {
        log.info("👀 사용자 정보 조회 - readUser() ");
        // 사용자를 찾을 변수 초기화
        User user = null;

        try {
            // 아이디로 사용자 찾기
            user = userRepository.findByUsername(dto.getUsername());
            log.info("try - 아이디로 사용자 찾기 : " + user);
        }catch (DataAccessException e){ // 데이터 저장소 오류
            throw new DataDeliveryException(Define.INCORRECT_PROCESSING, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){ // 모든 오류
            log.info("모든 오류 (Exception) : " + e.getMessage());
            throw new RedirectException(Define.UNKNOWN_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
        }

        // 사용자가 없으면 로그인 실패
        if(user == null) {
            throw new DataDeliveryException(Define.ID_OR_PASSWORD, HttpStatus.BAD_REQUEST);
        }
        // 비밀번호가 맞는지 확인
        // boolean isPwdMatched = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        // boolean isPwdMatched = dto.getPassword(user.getPassword());

        // 사용자를 반환 (로그인 성공)
        return user;
    }
}
