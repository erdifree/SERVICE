package com.example.demo.Repository;


import com.example.demo.Entiy.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Request,Integer> {


}
