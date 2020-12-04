package com.ufg.inf.ps.selfservice.infra.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
public final class WebUtils {
  public static final String FILE_INLINE = "inline";
  public static final String FILE_ATTACHMENT = "attachment";
  public static final String FILE_NAME = "X-FileName";

  private WebUtils() {
    super();
  }

  public static boolean containsParam(WebRequest webRequest, String key) {
    return StringUtils.isNotBlank(webRequest.getParameter(key));
  }

  public static boolean containsHeader(WebRequest webRequest, String key) {
    return StringUtils.isNotBlank(webRequest.getHeader(key));
  }

  public static void downloadHeaderZip(HttpServletResponse res, String fileName) {
    String zipFileName = StringUtils.appendIfMissingIgnoreCase(fileName, ".zip");
    downloadHeader(res, zipFileName);
  }

  public static void downloadHeader(HttpServletResponse res, String fileName) {
    var contentDisposition = ContentDisposition.builder(WebUtils.FILE_ATTACHMENT).filename(fileName).build();
    res.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
    res.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
    res.setHeader(FILE_NAME, fileName);
  }

  public static void pdfHeader(HttpServletResponse res, String fileName) {
    pdfHeader(res, fileName, WebUtils.FILE_INLINE);
  }

  public static void pdfHeader(HttpServletResponse res, String fileName, String type) {
    String pdfFileName = StringUtils.appendIfMissingIgnoreCase(fileName, ".pdf");
    var contentDisposition = ContentDisposition.builder(type).filename(pdfFileName).build();
    res.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
    res.setContentType(MediaType.APPLICATION_PDF_VALUE);
  }
}
