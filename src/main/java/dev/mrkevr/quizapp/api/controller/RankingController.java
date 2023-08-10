package dev.mrkevr.quizapp.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mrkevr.quizapp.api.dto.RankingResponse;
import dev.mrkevr.quizapp.api.service.RankingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/rankings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RankingController {
	
	RankingService rankingServ;
	
	@GetMapping
	ResponseEntity<List<RankingResponse>> getAll() {
		List<RankingResponse> rankings = rankingServ.getAll();
		return  ResponseEntity.ok(rankings);
	}

	@GetMapping("/{id}")
	ResponseEntity<RankingResponse> getById(@PathVariable String id) {
		RankingResponse ranking = rankingServ.getById(id);
		return ResponseEntity.ok(ranking);
	}
	
	@GetMapping("/category/{id}")
	ResponseEntity<RankingResponse> getByCateoryId(@PathVariable String id) {
		RankingResponse ranking = rankingServ.getByCategoryId(id);
		return ResponseEntity.ok(ranking);
	}
}
