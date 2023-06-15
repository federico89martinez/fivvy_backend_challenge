package com.example.demo.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Acceptance;
import com.example.demo.entities.Disclaimer;

import com.example.demo.service.FivvyService;

@RestController
public class FivvyController {

	@Autowired
	private FivvyService fivvyService;

	public FivvyController() {
	}

	@PostMapping(path = "/filter")
	public ResponseEntity<Disclaimer> createDisclaimer(@RequestBody Disclaimer disclaimer){
		return ResponseEntity.ok(fivvyService.addDisclaimer(disclaimer));
	}

	@GetMapping(path = "/get-text")
	public ResponseEntity<List<Document>> getDisclaimer(@RequestParam String text){
		return ResponseEntity.ok(fivvyService.getDisclaimer(text));
	}

	@DeleteMapping(path = "/delete-text")
	public ResponseEntity<Void> deleteDisclaimer(@RequestParam String text){
		fivvyService.deleteDisclaimer(text);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping(path = "/update-text")
	public  ResponseEntity<Void> updateDisclaimer(@RequestParam String text, @RequestBody Disclaimer disclaimer) {
		fivvyService.updateDisclaimer(text,disclaimer);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(path = "/filter-user")
	public ResponseEntity<Acceptance> createAcceptance(@RequestBody Acceptance acceptance){
		return ResponseEntity.ok(fivvyService.addAcceptance(acceptance));
	}

	@GetMapping(path = "/get-user")
	public ResponseEntity<List<Document>> getAcceptance(@RequestParam int user_id){
		return ResponseEntity.ok(fivvyService.getAcceptance(user_id));
	}

}
