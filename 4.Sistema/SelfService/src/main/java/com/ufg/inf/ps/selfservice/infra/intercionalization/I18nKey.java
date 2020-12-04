package com.ufg.inf.ps.selfservice.infra.intercionalization;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @author jonathas.assuncao on 12/11/2020
 * @project pdv
 */
@FunctionalInterface
public interface I18nKey extends Supplier<String>, Serializable {

}
