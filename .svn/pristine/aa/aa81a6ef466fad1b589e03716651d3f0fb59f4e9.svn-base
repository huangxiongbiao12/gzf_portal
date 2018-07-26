package com.gzf;

import com.gzf.persist.common.GzfPreceptor;
import com.gzf.util.storage.StorageService;
import com.lxy.persistence.mybatis.registry.BatisPersistence;
import com.lxy.persistence.redis.registry.RedisInterface;
import com.lxy.persistence.redis.registry.RedisPersistence;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.gzf")
@MapperScan("com.gzf.persist.mapper")
@BatisPersistence(value = {"com.gzf.persist.pojo", "com.gzf.service.bean"},
		preceptor = GzfPreceptor.class)
@RedisPersistence(value = "com.gzf.persist.pojo",
		interfaces = {@RedisInterface(interfaces = "com.gzf.persist.redisao")})
@EnableTransactionManagement //开启事务支持
@EnableScheduling //开启定时
public class PortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
