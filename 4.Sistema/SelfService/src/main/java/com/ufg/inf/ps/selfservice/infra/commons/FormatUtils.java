package com.ufg.inf.ps.selfservice.infra.commons;

import org.apache.commons.lang3.StringUtils;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * @author guilherme.pacheco
 */
public final class FormatUtils {

  private FormatUtils() {
    super();
  }

  public static String maskFormat(String mask, Object value) {
    try {
      return maskFormatter(mask).valueToString(value);
    } catch (ParseException ex) {
      throw new IllegalArgumentException("Invalid value for mask: " + mask, ex);
    }
  }

  public static MaskFormatter maskFormatter(String mask) {
    try {
      MaskFormatter formatter = new MaskFormatter(mask);
      formatter.setValueContainsLiteralCharacters(false);
      return formatter;
    } catch (ParseException ex) {
      throw new IllegalArgumentException("Invalid mask", ex);
    }
  }

  public static String replace(Map<String, Object> values, String string) {
    return Optional.ofNullable(values).map(map -> replaceMapString(map, string)).orElse(string);
  }

  private static String replaceMapString(Map<String, Object> values, String string) {
    for (Entry<String, Object> entry : values.entrySet()) {
      String key = String.format("{%s}", entry.getKey());
      String value = String.valueOf(entry.getValue());
      string = StringUtils.replace(string, key, value);
    }
    return string;
  }

}
