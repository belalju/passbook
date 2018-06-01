package com.solutionsquad.service;

import com.solutionsquad.model.PassbookInformation;
import com.solutionsquad.repository.PassbookRepository;
import com.solutionsquad.util.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class PassbookServiceImpl implements PassbookService{

    @Autowired
    PassbookRepository passbookRepository;

    public PassbookInformation add(PassbookInformation passbookInformation) {
        passbookInformation.setCreatedDate(new Date());
        passbookInformation.setStatus(CommonConstant.STATUS_ACTIVE);
        return passbookRepository.save(passbookInformation);
    }

    @Transactional(readOnly = true)
    public PassbookInformation get(long id) {
        return passbookRepository.getByIdAndStatus(id, CommonConstant.STATUS_ACTIVE);
    }

    @Transactional(readOnly = true)
    public List<PassbookInformation> getAll(){
        return passbookRepository.findAll();
    }
}
