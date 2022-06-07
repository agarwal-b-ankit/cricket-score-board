package com.score.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.score.board.factory.BallFactory;
import com.score.board.model.Ball;
import com.score.board.model.Player;
import com.score.board.model.Team;

public class ConsoleInputService implements InputService {
	private final Scanner sc;

	public ConsoleInputService() {
		sc = new Scanner(System.in);
	}

	@Override
	public int getNoOfPlayersPerTeam() {
		System.out.print("Enter the no of players for each team:");
		return sc.nextInt();
	}

	@Override
	public int getNoOfOvers() {
		System.out.print("Enter the no of overs:");
		return sc.nextInt();
	}

	@Override
	public List<Player> getBattingOrder(Team team, int noOfPlayers) {
		System.out.println("Batting Order for " + team.getName());
		List<Player> battingOrder = new ArrayList<>();
		for (int i = 0; i < noOfPlayers; i++) {
			String playerName = sc.next();
			battingOrder.add(Player.builder().name(playerName).build());
		}
		return battingOrder;
	}

	@Override
	public Ball getNextBall() {
		String ball = sc.next();
		return BallFactory.getBall(ball);
	}
}
