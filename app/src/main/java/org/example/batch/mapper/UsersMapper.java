package org.example.batch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.example.batch.entity.User;

@Mapper
public interface UsersMapper {
  int insertUser(User user);
  List<User> findAll();
}
