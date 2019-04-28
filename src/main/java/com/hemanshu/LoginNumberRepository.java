package com.hemanshu;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;

public interface LoginNumberRepository extends CrudRepository<loginNumbers, Integer> {

    ArrayList<loginNumbers> findByDate(String date);
}
