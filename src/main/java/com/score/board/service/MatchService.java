package com.score.board.service;

import java.util.List;

import com.score.board.model.Ball;
import com.score.board.model.Inning;
import com.score.board.model.Match;
import com.score.board.model.Player;
import com.score.board.model.Team;

public class MatchService {
	private final InputService inputService;
	private Match match;

	public MatchService() {
		inputService = new ConsoleInputService();
	}

	public void startMatch() {
		int noOfPlayersPerTeam = inputService.getNoOfPlayersPerTeam();
		int noOfOvers = inputService.getNoOfOvers();
		match = new Match(noOfPlayersPerTeam, noOfOvers);
		playInning(match.getCurrentBattingTeam(), -1);
		match.switchBattingTeam();
		playInning(match.getCurrentBattingTeam(), match.getCurrentBowlingTeam().getTotalScore());
		match.displayResult();
	}

	private void playInning(Team team, int scoreToBeat) {
		List<Player> battingOrder = inputService.getBattingOrder(team, match.getNoOfPlayers());
		Inning inning = new Inning(team, battingOrder);
		team.addInnings(inning);
		for (int currentOver = 1; currentOver <= match.getTotalOvers(); currentOver++) {
			playOver(currentOver, inning, scoreToBeat);
			inning.displayScoreBoard();
			if (inning.isInningsFinished())
				break;
		}
	}

	private void playOver(int overNo, Inning inning, int scoreToBeat) {
		System.out.println();
		System.out.println("Over " + overNo + ":");
		for (int i = 0; i < 6; i++) {
			Ball ball = inputService.getNextBall();
			if (ball.isWicket()) {
				inning.getCurrentBattingPlayer().getScoreCard().updateScore(0);
				inning.getCurrentBattingPlayer().getScoreCard().markOutStatus();
				if (inning.isNextBattingPlayerAvailable()) {
					inning.updateBattingPlayer();
				} else {
					inning.finishInnings();
				}
			} else if (ball.isExtra()) {
				inning.addExtraBall();
				i--; // decrement i to skip the extra ball
			} else {
				inning.getCurrentBattingPlayer().getScoreCard().updateScore(ball.getRuns());
				if (ball.getRuns() % 2 == 1) {
					inning.changeStrike();
				}
			}
			if (scoreToBeat != -1 && inning.getTotalScore() > scoreToBeat) {
				inning.finishInnings();
			}
			if (inning.isInningsFinished())
				break;
		}
		inning.changeStrike();
	}
}
