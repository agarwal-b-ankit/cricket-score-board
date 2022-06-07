package com.score.board;

import com.score.board.service.MatchService;

public class Application {
	public static void main(String[] args) {
		MatchService matchService = new MatchService();
		matchService.startMatch();
	}
}
