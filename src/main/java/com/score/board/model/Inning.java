package com.score.board.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Inning {
	private final Team team;
	private List<Player> battingOrder;
	private int battingPlayerIndex;
	private int offStrikePlayerIndex;
	private int extras;
	private int wicketsDown;
	private boolean inningsFinished;

	public Inning(Team team, List<Player> players) {
		this.team = team;
		battingOrder = new ArrayList<>();
		players.forEach(player -> {
			battingOrder.add(player);
			player.setScoreCard(new ScoreCard());
		});
		battingPlayerIndex = 0;
		offStrikePlayerIndex = 1;
	}

	public Player getCurrentBattingPlayer() {
		return battingOrder.get(battingPlayerIndex);
	}

	public Player getOffStrikePlayer() {
		return battingOrder.get(offStrikePlayerIndex);
	}

	public void changeStrike() {
		int temp = battingPlayerIndex;
		battingPlayerIndex = offStrikePlayerIndex;
		offStrikePlayerIndex = temp;
	}

	public void updateBattingPlayer() {
		battingPlayerIndex = 1 + Math.max(battingPlayerIndex, offStrikePlayerIndex);
	}

	public boolean isNextBattingPlayerAvailable() {
		int index = 1 + Math.max(battingPlayerIndex, offStrikePlayerIndex);
		return index < battingOrder.size();
	}

	public void addExtraBall() {
		extras++;
	}

	public int getTotalScore() {
		int playerScores = 0;
		for (Player player : battingOrder) {
			playerScores += player.getScoreCard().getNoOfRuns();
		}
		// int playerScores = (int) battingOrder.stream().map(player ->
		// player.getScoreCard().getNoOfRuns()).count();
		return playerScores + extras;
	}

	public int getTotalWickets() {
		int wickets = 0;
		for (Player player : battingOrder) {
			if (player.getScoreCard().isOut()) {
				wickets++;
			}
		}
		// int wickets = (int) battingOrder.stream().filter(player ->
		// player.getScoreCard().isOut()).count();
		return wickets;
	}

	public int getTotalBalls() {
		int balls = 0;
		for (Player player : battingOrder) {
			balls += player.getScoreCard().getNoOfBalls();
		}
		// int balls = (int) battingOrder.stream().map(player ->
		// player.getScoreCard().getNoOfBalls()).count();
		return balls;
	}

	public void finishInnings() {
		inningsFinished = true;
	}

	public void displayScoreBoard() {
		System.out.println();
		System.out.println("Score card for " + getTeam().getName() + ":");
		System.out
				.println("Player Name                              Score                4s          6s         Balls");
		for (Player player : getBattingOrder()) {
			System.out.print(player.getName() + "                ");
			System.out.print(player.getScoreCard().getNoOfRuns() + "                ");
			System.out.print(player.getScoreCard().getNoOfFours() + "                ");
			System.out.print(player.getScoreCard().getNoOfSixes() + "                ");
			System.out.println(player.getScoreCard().getNoOfBalls());
		}
		System.out.println("Total: " + getTotalScore() + "/" + getTotalWickets());
		int ballsPlayed = getTotalBalls();
		System.out.print("Overs: " + ballsPlayed / 6);
		if (ballsPlayed % 6 != 0)
			System.out.print("." + ballsPlayed % 6);
		System.out.println();
	}

}
