package br.com.rei.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YamlToHttpMessageConverter extends AbstractJackson2HttpMessageConverter {

	protected YamlToHttpMessageConverter() {
		super(new YAMLMapper()
				.setSerializationInclusion(JsonInclude.Include.NON_NULL),
				MediaType.parseMediaType("application/x-yaml"));
	}

}
