package com.sunga.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootApplication
public class SongApplication {

	@Bean
	CommandLineRunner seedSongs(SongRepository songRepository) {
		return args -> {
			if (songRepository.count() > 0) {
				return;
			}

			Song song1 = new Song();
			song1.setAlbum("Achtung Baby");
			song1.setArtist("U2");
			song1.setGenre("Rock");
			song1.setTitle("One");
			song1.setUrl("https://youtu.be/ftjEcrrf7r0");

			Song song2 = new Song();
			song2.setAlbum("...Baby One More Time");
			song2.setArtist("Britney Spears");
			song2.setGenre("Pop");
			song2.setTitle("Sometimes");
			song2.setUrl("https://youtu.be/t0bPrt69rag");

			Song song3 = new Song();
			song3.setAlbum("Ultraelectromagneticpop!");
			song3.setArtist("Eraserheads");
			song3.setGenre("OPM");
			song3.setTitle("Ligaya");
			song3.setUrl("https://youtu.be/XibB-5BPdrY");

			songRepository.saveAll(List.of(song1, song2, song3));
		};
	}

	public static void main(String[] args) {
		configureRenderPostgresUrl();
		SpringApplication.run(SongApplication.class, args);
	}

	private static void configureRenderPostgresUrl() {
		String databaseUrl = System.getenv("DATABASE_URL");
		if (databaseUrl == null || databaseUrl.isBlank()) {
			return;
		}

		if (!(databaseUrl.startsWith("postgres://") || databaseUrl.startsWith("postgresql://"))) {
			return;
		}

		try {
			URI uri = new URI(databaseUrl);
			String schemeSpecificHost = uri.getHost();
			if (schemeSpecificHost == null || schemeSpecificHost.isBlank()) {
				return;
			}

			int port = uri.getPort() > 0 ? uri.getPort() : 5432;
			String path = uri.getPath() == null ? "" : uri.getPath();
			String query = uri.getQuery() == null ? "" : "?" + uri.getQuery();
			String jdbcUrl = "jdbc:postgresql://" + schemeSpecificHost + ":" + port + path + query;

			System.setProperty("spring.datasource.url", jdbcUrl);
			System.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");
			System.setProperty("spring.jpa.database-platform", "org.hibernate.dialect.PostgreSQLDialect");

			String userInfo = uri.getUserInfo();
			if (userInfo != null && userInfo.contains(":")) {
				String[] parts = userInfo.split(":", 2);
				if (!parts[0].isBlank()) {
					System.setProperty("spring.datasource.username", parts[0]);
				}
				if (!parts[1].isBlank()) {
					System.setProperty("spring.datasource.password", parts[1]);
				}
			}
		} catch (URISyntaxException ignored) {
			// Keep default datasource settings when DATABASE_URL is malformed.
		}
	}

}