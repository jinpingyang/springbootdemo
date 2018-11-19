package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
import com.example.demo.vo.UserVo;

@Repository
public interface UserRepository extends  BaseRepository<User,Integer>{

	@Query("select new com.example.demo.vo.UserVo(u.id,u.no,u.name,u.address) from User u")
	public List<UserVo> findByAll();
	
	public Optional<User> findById(int id);
}
