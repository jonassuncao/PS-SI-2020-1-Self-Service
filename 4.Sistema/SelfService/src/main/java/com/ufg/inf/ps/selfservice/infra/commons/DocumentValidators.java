package com.ufg.inf.ps.selfservice.infra.commons;

/**
 * @author jonathas.assuncao on 11/12/2020
 * @project SelfService
 */
public final class DocumentValidators {

  public static final int CPF_SIZE = 11;
  public static final int CNPJ_SIZE = 14;

  private static final int DIGIT_MAX = 9;
  private static final int BASE_VERIFY = 11;
  private static final int VERIFY_DIGITS_SIZE = 2;
  private static final int[] WEIGHT_CPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
  private static final int[] WEIGHT_CNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

  private DocumentValidators() {
    super();
  }

  public static boolean isValidCpf(String value) {
    return isValid(value, CPF_SIZE, WEIGHT_CPF);
  }

  public static boolean isValidCnpj(String value) {
    return isValid(value, CNPJ_SIZE, WEIGHT_CNPJ);
  }

  private static boolean isValid(String value, int size, int[] weight) {
    if (isInvalid(value, size)) {
      return false;
    }
    String number = value.substring(0, size - VERIFY_DIGITS_SIZE);
    int digit1 = digit(number, weight);
    int digit2 = digit(number + digit1, weight);
    return value.equals(number + digit1 + digit2);
  }

  private static int digit(String number, int[] weight) {
    int sum = 0;
    for (int index = number.length() - 1; index >= 0; index--) {
      int digito = Integer.parseInt(number.substring(index, index + 1));
      sum += digito * weight[weight.length - number.length() + index];
    }
    sum = BASE_VERIFY - sum % BASE_VERIFY;
    return sum > DIGIT_MAX ? 0 : sum;
  }

  private static boolean isInvalid(String value, int size) {
    if (value == null || value.trim().length() != size) {
      return true;
    }
    return hasAllRepeatedDigits(value);
  }

  private static boolean hasAllRepeatedDigits(String value) {
    for (int i = 1; i < value.length(); i++) {
      if (value.charAt(i) != value.charAt(0)) {
        return false;
      }
    }
    return true;
  }

}
