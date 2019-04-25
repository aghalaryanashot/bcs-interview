package interview.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static java.util.Arrays.asList;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final int CONNECTION_TIMEOUT_MS = 10000;
    private static final int READ_TIMEOUT_MS = 15000;
    private static final int MAX_CONN_TOTAL = 50;
    private static final int MAX_CONN_PER_ROUTE = 30;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }

    @Bean
    public RestTemplate restTemplate() {
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(MAX_CONN_TOTAL)
                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE)
                .build();
        HttpComponentsClientHttpRequestFactory httpRequestFactory
                = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpRequestFactory.setConnectTimeout(CONNECTION_TIMEOUT_MS);
        httpRequestFactory.setReadTimeout(READ_TIMEOUT_MS);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.setMessageConverters(asList(
                new StringHttpMessageConverter(),
                new FormHttpMessageConverter(),
                new ByteArrayHttpMessageConverter(),
                new MappingJackson2HttpMessageConverter()));
        return restTemplate;
    }
}
