package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

/**
 * @Data、@NoArgsConstructor、@AllArgsConstructor是lombok简化代码的注解，主要用于生成get、set以及构造函数。
 * @JacksonXmlRootElement、@JacksonXmlProperty注解是用来维护对象属性在xml中的对应关系。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName="User")
public class User {

	@JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "age")
    private Integer age;


}
