package fi.haagahelia.activitytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ch.qos.logback.classic.Logger;
import fi.haagahelia.domain.ActivityRepository;
import fi.haagahelia.domain.AppUser;
import fi.haagahelia.domain.AppUserRepository;
import fi.haagahelia.domain.Category;
import fi.haagahelia.domain.CategoryRepository;

@SpringBootApplication
public class ActivitytrackerApplication {
	private static final Logger log = LoggerFactory.getLogger(ActivitytrackerApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ActivitytrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner activityTracker(ActivityRepository repository, CategoryRepository crepository, AppUserRepository urepository, BCryptPasswordEncoder passwordEncoder ) {
		return args -> {
			log.info("Initializing book database...");

			Category category1 = new Category("Strength", null);
			Category category2 = new Category("Cardio", null);
			Category category3 = new Category("Recovery", null);
			
			crepository.save(category1);
			crepository.save(category2);
			crepository.save(category3);

			log.info("Activities in database:");
			repository.findAll().forEach(activity ->
				log.info("Title {}, Description {}, Activity Date {}, Duration {}",
					activity.getTitle(), activity.getDescription(), activity.getActivityDate(), activity.getDuration())
				);
			
				urepository.save(new AppUser("user",
                    passwordEncoder.encode("user"),
                    "USER",
                    "user@email.com"));

            urepository.save(new AppUser("admin",
                    passwordEncoder.encode("admin"),
                    "ADMIN",
                    "admin@email.com"));
			
		};
	}

}
