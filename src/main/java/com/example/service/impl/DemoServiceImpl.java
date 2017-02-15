package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.PersonRepository;
import com.example.domain.Person;
import com.example.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService{

	@Autowired
	PersonRepository personRepository;
	
	@Transactional(rollbackFor={IllegalArgumentException.class})//特定异常时，数据回滚
	@Override
	public Person savePersonWithRollBack(Person person) {
		Person p = personRepository.save(person);
		
		if(p.getName().equals("xiawq")) {
			throw new IllegalArgumentException("xiawq已存在，数据将回滚");
		}
		return p;
	}

	
	@Transactional(noRollbackFor={IllegalArgumentException.class})//特定异常时，数据不回滚
	@Override
	public Person savePersonWithoutRollBack(Person person) {
		Person p = personRepository.save(person);
		
		if(p.getName().equals("xiawq")) {
			throw new IllegalArgumentException("xiawq已存在，数据将不会回滚");
		}
		return p;
	}

}
