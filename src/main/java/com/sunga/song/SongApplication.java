package com.sunga.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SongApplication {

	@Bean
	CommandLineRunner seedSongs(JdbcTemplate jdbcTemplate) {
		return args -> {
			jdbcTemplate.update("delete from song");

			jdbcTemplate.update(
				"insert into song (id, album, artist, genre, title, url) values (?, ?, ?, ?, ?, ?)",
				2L,
				"Achtung Baby",
				"U2",
				"Rock",
				"One",
				"https://youtu.be/ftjEcrrf7r0"
			);

			jdbcTemplate.update(
				"insert into song (id, album, artist, genre, title, url) values (?, ?, ?, ?, ?, ?)",
				3L,
				"...Baby One More Time",
				"Britney Spears",
				"Pop",
				"Sometimes",
				"https://youtu.be/t0bPrt69rag"
			);

			jdbcTemplate.update(
				"insert into song (id, album, artist, genre, title, url) values (?, ?, ?, ?, ?, ?)",
				4L,
				"Ultraelectromagneticpop!",
				"Eraserheads",
				"OPM",
				"Ligaya",
				"https://youtu.be/XibB-5BPdrY"
			);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SongApplication.class, args);
	}

}