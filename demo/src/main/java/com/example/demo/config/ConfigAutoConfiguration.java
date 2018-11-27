package com.example.demo.config;

import java.util.Iterator;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * 启动监听器，启动前设置启动参数
 * 
 * @author yangj
 *
 */
@Configuration
public class ConfigAutoConfiguration implements ApplicationListener<ApplicationEnvironmentPreparedEvent>{
private final static Logger logger=LoggerFactory.getLogger(ConfigAutoConfiguration.class);
	
//	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		logger.debug("event: ====="+event);
		logger.debug("getEnvironment: ====="+event.getEnvironment());
		MutablePropertySources propertySources = event.getEnvironment().getPropertySources();
		logger.debug("propertySources: ===="+propertySources);
		Iterator<PropertySource<?>> var3 = event.getEnvironment().getPropertySources().iterator();
        PropertySource source;
        logger.debug("---------------source begin-------------------");
        do {
            if (!var3.hasNext()) {
                return;
            }

            source = var3.next();
            logger.debug("source: ===="+source);
        } while(!(source instanceof OriginTrackedMapPropertySource));
        logger.debug("---------------source end-------------------");
        String[] var5 = ((OriginTrackedMapPropertySource)source).getPropertyNames();
        logger.debug("---------------PropertyName begin-------------------");
        for(String name:var5) {
        	logger.debug("PropertyName: ===="+name);
        }
        logger.debug("---------------PropertyName end-------------------");

        propertySources.addFirst(new PropertySource<Object>("config") {
            public Object getProperty(String name) {
            	Properties properties = new Properties();
            	properties.setProperty("demo.port", "8081");
            	logger.debug("---------------addFirst-------------------");
            	logger.debug(properties.get(name));
                return properties.get(name);
            }
        });
	}

}
