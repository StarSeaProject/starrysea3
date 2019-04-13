package top.starrysea.dal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.KumaSqlDaoImpl;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public KumaSqlDao getKumaSqlDao() {
		return new KumaSqlDaoImpl();
	}
}
