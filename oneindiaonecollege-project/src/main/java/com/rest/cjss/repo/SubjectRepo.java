package com.rest.cjss.repo;

import com.rest.cjss.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends JpaRepository<SubjectEntity,Integer> {
}
