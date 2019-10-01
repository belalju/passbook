package com.solutionsquad.controller;

import com.solutionsquad.service.PassbookService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
public class IndexController {

    protected static Logger logger = Logger.getLogger("controller");

    @Autowired
    PassbookService passbookService;

    @GetMapping("/")
    public String index(Model m) {
        m.addAttribute("currentDate", new Date().toString());
        return "index";
    }

    @RequestMapping(value = "/passbookList", method = RequestMethod.GET)
    @ResponseBody
    public String passbookList(HttpServletResponse response) {
        passbookService.printPassbookReport(response);
        return "passbookList";
    }

    @RequestMapping(value = "/generateReport")
    public String generateReport( HttpServletRequest request,HttpServletResponse response) {
        try{
            //generatePdfReport(response);
            passbookService.printPassbookReport(response);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

    public void generatePdfReport(HttpServletResponse response) {
        /*try {
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
        }*/

    }


}
