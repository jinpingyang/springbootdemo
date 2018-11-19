package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.vo.UserVo;
import com.github.pagehelper.PageHelper;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Cacheable(cacheNames="user",key="#id")
	public User findById(int id) {
		return userRepository.findById(id).isPresent()?userRepository.findById(id).get():null;
	}
	
	public List<UserVo> findByAll(){
		return userRepository.findByAll();
	}
	
	public Page<User> findByAll(int page,int size){
//		Pageable pageable=new PageRequest(page, size, Sort.Direction.DESC, "id");
		PageRequest pageRequest = PageRequest.of(page, size,Sort.Direction.DESC, "id");
		return userRepository.findAll(pageRequest);
	}
	
	public Page<User> findByAll(Map<String, Object> params,Pageable pageable){
	        Page<User> page = userRepository.findAll((root, query, criteriaBuilder)->{
	            List<Predicate> list = new ArrayList<>();
	            Predicate[] p = new Predicate[list.size()];
	            if(params!=null) {
	                params.forEach((k, v) -> list.add(criteriaBuilder.equal(root.get(k), params.get(k))));
	            }
	            return criteriaBuilder.and(list.toArray(p));
	        }
	        ,pageable);
	        return page;
	}
	
	public Page<User> findCriteria(int page,int size,User user){
//		Pageable pageable=new PageRequest(page, size, Sort.Direction.DESC, "id");
		PageRequest pageRequest = PageRequest.of(page, size,Sort.Direction.DESC, "id");
		return userRepository.findAll(new Specification<User>() {
			
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(user.getNo()!=null&&!user.getNo().equals("")) {
					list.add(criteriaBuilder.equal(root.get("no").as(String.class), user.getNo()));
				}
				 Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}
		},pageRequest);
	}	
	
	
	public List<User> selectAll(){
		com.github.pagehelper.Page<User> page= PageHelper.startPage(1, 1);
		return userMapper.selectAll();
	}
}
