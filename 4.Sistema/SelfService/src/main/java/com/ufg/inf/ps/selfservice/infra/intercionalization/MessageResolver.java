package com.ufg.inf.ps.selfservice.infra.intercionalization;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author jonathas.assuncao on 12/11/2020
 * @project pdv
 */
@Component
public class MessageResolver {

  private static final Locale LOCALE = new Locale("pt", "BR");

  @Bean
  private MessageSource messageSource() {
    Locale.setDefault(LOCALE);
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.addBasenames("classpath:/messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  public String resolve(I18nKey key) {
    return resolve(key.get());
  }

  public String resolve(I18nKey key, Object[] params) {
    return resolve(key.get(), params);
  }

  public String resolve(String key, Object[] params) {
    return messageSource().getMessage(key, params, LOCALE);
  }

  public String resolve(String key) {
    return messageSource().getMessage(key, null, LOCALE);
  }
}
