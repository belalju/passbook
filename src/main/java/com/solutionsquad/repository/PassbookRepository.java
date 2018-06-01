package com.solutionsquad.repository;

import com.solutionsquad.model.PassbookInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassbookRepository extends JpaRepository<PassbookInformation, Long>{

    public PassbookInformation getByIdAndStatus(long id, int status);
}
