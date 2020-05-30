package com.hariko.docker;

import com.hariko.docker.factory.HarikoFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class HarikoDemoApplication implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(HarikoDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HarikoDemoApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        HarikoFactoryBean harikoFactoryBean = applicationContext.getBean(HarikoFactoryBean.class);
        LOGGER.info("根据bean的class类型获取bean工厂实例为: {}", harikoFactoryBean);
    }
}
