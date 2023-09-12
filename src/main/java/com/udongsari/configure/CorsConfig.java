package com.udongsari.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();

      config.setAllowCredentials(true); // js 접근 허용
      config.addAllowedOrigin("*"); // 모든 주소 접근 허용
      config.addAllowedHeader("*"); // 모든 헤더 접근 허용
      config.addAllowedMethod("*"); // 모든 Method(get, post, put, delete, fetch 등) 접근 허용

      source.registerCorsConfiguration("/api/**", config);

      return new CorsFilter(source);
   }

}
