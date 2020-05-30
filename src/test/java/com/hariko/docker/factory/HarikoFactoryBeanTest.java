package com.hariko.docker.factory;

import com.hariko.docker.HarikoDemoApplication;
import com.hariko.docker.factory.entity.HarikoBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = HarikoDemoApplication.class)
@RunWith(SpringRunner.class)
public class HarikoFactoryBeanTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HarikoFactoryBeanTest.class);

    @Resource(name = "&harikoFactoryBean")
    private HarikoFactoryBean harikoFactoryBean;

    @Resource
    private HarikoBean harikoBean;

    @Test
    public void test(){
        LOGGER.info("得到HarikoFactoryBean生产的bean实例是: {}", harikoFactoryBean);
        LOGGER.info("得到HarikoFactoryBean生产的bean实例是: {}", harikoBean);
    }

}
