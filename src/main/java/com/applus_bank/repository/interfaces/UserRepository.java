package com.applus_bank.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import com.applus_bank.repository.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
    날짜 : 2025.02.17 (일)
    이름 : 김민희
    내용 : @Mapper 선언했지만 Spring Boot 가 자동 감지 못함 이슈 → MyBatis 의존성 추가

    이력 : 2025.02.20 (목) - 사용자 정보 조회를 위한 findByUsername () 추가
 */

@Mapper
@Repository
public interface UserRepository {

    public int insert(User user);
    public int updateById(User user);
    public int deleteById(Integer id);
    public int findById(Integer id);
    public List<User> findAll();

    public User findByUsernameAndPassword();

    public User findByUsername(String username);
}
