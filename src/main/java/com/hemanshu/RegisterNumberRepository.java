package com.hemanshu;

import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface RegisterNumberRepository extends CrudRepository<RegisterNumbers, Integer> {
    
    ArrayList<RegisterNumbers> findByDate(String date);
}
