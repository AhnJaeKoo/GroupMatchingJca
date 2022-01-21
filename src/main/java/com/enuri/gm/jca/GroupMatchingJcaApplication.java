package com.enuri.gm.jca;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;

import com.enuri.gm.jca.banner.BannerPrinter;

@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
public class GroupMatchingJcaApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GroupMatchingJcaApplication.class);
		app.setBannerMode(Mode.OFF);
		ConfigurableApplicationContext context = app.run(args);
	    app.setBannerMode(Mode.CONSOLE);
		new BannerPrinter(context).print(GroupMatchingJcaApplication.class);
	}
}
