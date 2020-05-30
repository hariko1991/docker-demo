package com.hariko.docker.factory;

import com.hariko.docker.factory.entity.HarikoBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class HarikoFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new HarikoBean();
    }

    @Override
    public Class<?> getObjectType() {
        return HarikoBean.class;
    }
}
