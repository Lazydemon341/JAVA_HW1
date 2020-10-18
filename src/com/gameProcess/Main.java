package com.gameProcess;

import cells.*;
import players.Bot;
import players.Player;
import players.RealPlayer;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author <a href="mailto:avvlasyuk_1@edu.hse.ru"> Alex Vlasyuk</a>
 */
public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        RealPlayer realPlayer;
        Bot bot;

        Map map;

        try {
            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);
            map = new Map(width, height);

            int startMoney = Integer.parseInt(args[2]);
            realPlayer = new RealPlayer(map, startMoney);
            bot = new Bot(map, startMoney);

        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        letsPlay(realPlayer, bot);
    }

    /**
     * Процесс игры.
     *
     * @param realPlayer Реальный игрок.
     * @param bot        Бот.
     */
    static void letsPlay(RealPlayer realPlayer, Bot bot) {
        Player[] players = tossCoin(realPlayer, bot);
        pressEnterToContinue();

        int i = 0;
        while (realPlayer.getMoney() > 0 && bot.getMoney() > 0) {
            // Ход игрока.
            rollTheDice(players[i % 2]);

            pressEnterToContinue();
            i++;
        }
        gameOver(realPlayer, bot);
    }

    /**
     * Конец игры.
     * @param realPlayer Реальный игрок.
     * @param bot Бот.
     */
    static void gameOver(RealPlayer realPlayer, Bot bot){
        if (realPlayer.getMoney() <= 0)
            System.out.println("You have lost...");
        else
            System.out.println("You have won! Congratulations!");

        System.out.println("Your money: " + realPlayer.getMoney() + "$.");
        System.out.println("Bot's money: " + bot.getMoney() + "$.");
    }

    /**
     * Осуществляет ход игрока на случайное (от 2 до 12) число клеток.
     *
     * @param player Игрок
     */
    static void rollTheDice(Player player) {
        int cnt = ThreadLocalRandom.current().nextInt(1, 7) +
                ThreadLocalRandom.current().nextInt(1, 7);

        player.moveForward(cnt);
    }

    /**
     * Определяет очередность ходов игроков.
     *
     * @return Список игроков. На первом месте тот, кто ходит первый.
     */
    static Player[] tossCoin(Player realPlayer, Player bot) {
        int coin = ThreadLocalRandom.current().nextInt(1, 3);

        if (coin == 1) {
            System.out.println("You go first!");
            return new Player[]{realPlayer, bot};
        } else {
            System.out.println("The bot goes first!");
            return new Player[]{bot, realPlayer};
        }
    }

    /**
     * Ожидает, пока игрок нажмет клавишу Enter.
     */
    static void pressEnterToContinue() {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
            System.out.println();
        } catch (Exception e) {
        }
    }
}
