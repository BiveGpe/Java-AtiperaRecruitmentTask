package com.example.atipera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Informacje do zadania
 * 1. Proszę nie robić nic poza rzeczami wymienionymi w tasku. Robienie rzeczy ponad proszone, będzie wadą nie zaletą. Skupiamy się na tym co jest proszone i robimy jak najlepiej
 * 2. Nie używany Webflux
 * 3. Do zadania potrzebny jest jeden test integracyjny (celem jak napisanie go bez mocków/najmniej mocków jak to możliwe), sprawdzający happy path. Proszę zamodelować tak given i when, żeby w jednym teście sprawdzić jak najwięcej z punktu widzenia biznesowego/treści zadania. Skupiamy się tylko na tym teście i nie piszemy więcej.
 * 4. Nie obsługujemy paginacji (ani w wystawianym endpoint, ani przy konsumowaniu API)
 * 5. Nie wprowadzamy struktury DDD/Hexagonalnej do projektu.
 * 6. Wysyłam zdania rekrutacyjne. Proszę je zrobić w Java lub Java 21 + Spring 3.5.
 * 7. Zadania przyjmujemy jako link do publicznego github
 * Acceptance criteria:
 * As an api consumer, I would like to list all his github repositories, which are not forks. Information, which I require in the response, is:
 * Repository Name
 * Owner Login
 * For each branch it’s name and last commit sha
 * As an api consumer, given not existing github user, I would like to receive 404 response in such a format:
 * {
 *     “status”: ${responseCode}
 *     “message”: ${whyHasItHappened}
 * }
 * Notes:
 * Please full-fill the given acceptance criteria, delivering us your best code compliant with industry standards.
 * Please use https://developer.github.com/v3 as a backing API
 * Application should have a proper README.md file
 */

@SpringBootApplication
public class AtiperaApplication {
	public static void main(String[] args) {
		SpringApplication.run(AtiperaApplication.class, args);
	}
}
