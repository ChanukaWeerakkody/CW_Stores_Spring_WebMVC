package lk.ijse.spring.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/
@EnableWebMvc
@Configuration
@ComponentScan("lk.ijse.spring")
public class WebAppConfig {

}
