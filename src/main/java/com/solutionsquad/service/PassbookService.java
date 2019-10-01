package com.solutionsquad.service;

import com.solutionsquad.model.PassbookInformation;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PassbookService {
    public PassbookInformation add(PassbookInformation passbookInformation);

    public PassbookInformation get(long id);

    public List<PassbookInformation> getAll();

    public void printPassbookReport(HttpServletResponse response);

}
