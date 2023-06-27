package com.gman.bitcoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class BitcoinAlt {

    private static final Logger log = LoggerFactory.getLogger(BitcoinAlt.class);


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.valueOf("application/javascript")));
        messageConverters.add(jsonMessageConverter);
        return builder.messageConverters(messageConverters).build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
        return args -> {
            USD usd = restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", USD.class);
            log.info(usd.toString());
        };
    }

    @Bean
    public CommandLineRunner run2(RestTemplate restTemplate) throws Exception{
        return args -> {
            GBP gbp = restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", GBP.class);
            log.info(gbp.toString());
        };
    }

    @Bean
    public CommandLineRunner run3(RestTemplate restTemplate) throws Exception{
        return args -> {
            EUR eur = restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", EUR.class);
            log.info(eur.toString());
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(BitcoinAlt.class, args);
    }

}