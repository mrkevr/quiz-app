package dev.mrkevr.quizapp.api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
public class SwaggerController {

	@ApiIgnore
	@GetMapping
	void swagger(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
	}
}
