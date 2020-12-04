package com.ufg.inf.ps.selfservice.application.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */

@RestController
@RequestMapping("/api/person")
public class PersonQueryResource {

  @GetMapping
  public Object person(){
    return "Hello";
  }
}
