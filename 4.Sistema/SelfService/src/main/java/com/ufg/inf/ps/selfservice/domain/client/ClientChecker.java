package com.ufg.inf.ps.selfservice.domain.client;

import com.ufg.inf.ps.selfservice.infra.commons.Checker;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author jonathas.assuncao on 11/12/2020
 * @project SelfService
 */
@Component
class ClientChecker {

  private static final String REGEX_PATTERN = "^[\\w-]+(?:\\.[\\w-]+)*@(?:[\\w-]+\\.)+[a-zA-Z]{2,7}";
  private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
  private static final int MIN_SIZE = 5;
  private static final int MAX_SIZE = 15;

  private final ClientRepository clientRepository;

  public ClientChecker(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public void check(Client client) {
    checkerUsername(client.getUsername());
  }

  public void checkerPassword(Client client) {
    Checker.isTrue(validPassword(client.getPassword()), I18nClient.CLIENT_INVALID_PASSWORD, MIN_SIZE, MAX_SIZE);
  }

  private boolean validPassword(String password) {
    final boolean minSize = password.length() >= MIN_SIZE;
    final boolean maxSize = password.length() <= MAX_SIZE;
    return minSize && maxSize;
  }

  private void checkerUsername(String value) {
    Checker.isTrue(validMail(value), I18nClient.CLIENT_USERNAME_INVALID, value);
    Checker.isFalse(clientRepository.existsByUsernameIgnoreCase(value), I18nClient.CLIENT_USERNAME_INUSE, value);
  }

  private boolean validMail(String value) {
    return Optional.ofNullable(value).filter(StringUtils::isNotBlank)
        .map(v -> PATTERN.matcher(v).matches()).orElse(false);
  }
}
