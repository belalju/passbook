package com.solutionsquad.service;

import com.solutionsquad.model.PassbookInformation;
import com.solutionsquad.repository.PassbookRepository;
import com.solutionsquad.util.CommonConstant;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PassbookServiceImpl implements PassbookService{

    protected static Logger logger = Logger.getLogger("service");

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

    public void printPassbookReport(HttpServletResponse response) {

        try {
            InputStream jrxmlInput = this.getClass().getClassLoader().getResource("ledger.jrxml").openStream();
            JasperDesign design = JRXmlLoader.load(jrxmlInput);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            logger.info("Report compiled");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap());
            logger.info("JasperPrint" + jasperPrint);

            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
            pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
            pdfExporter.exportReport();

            response.setContentType("application/pdf");
            response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
            response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");

            OutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(pdfReportStream.toByteArray());
            responseOutputStream.close();
            pdfReportStream.close();
            logger.info("Completed Successfully: ");
        } catch (Exception e) {
            logger.info("Error: ", e);
        }

    }
}
