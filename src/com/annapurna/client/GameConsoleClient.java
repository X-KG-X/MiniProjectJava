package com.annapurna.client;

import com.annapurna.Game;
import com.annapurna.GameFactory;

import java.io.Console;

public class GameConsoleClient {
    public static void main(String[] args) throws InterruptedException {
        Console console= System.console();
        if (console == null) {
            System.out.print("No console available");
            return;
        }
        Game game= GameFactory.createGame();

        game.startGameAlt();

    }
}
