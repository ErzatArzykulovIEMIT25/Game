import java.util.Random;
import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);
    private static int bestScore = -1;




    public static void startLoop() {
        boolean running = true;
        while (running) {
            clearScreen();
            int menuChoice = showMenu();

            switch (menuChoice) {
                case 1:
                    playRound();
                    break;
                case 2:
                    showScores();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    break;
            }
            if (running && menuChoice != 0) {
                System.out.print("Нажмите Enter, чтобы вернуться в меню...");
                if (scanner.hasNextLine()) scanner.nextLine();
                scanner.nextLine();
            }
        }
    }






    public static int showMenu() {
        System.out.println("=====================================");
        System.out.println("          🌟 ГЛАВНОЕ МЕНЮ 🌟          ");
        System.out.println("=====================================");
        System.out.println("1. 🎮 Начать игру");
        System.out.println("2. 🏆 Показать лучший результат");
        System.out.println("0. 🚪 Выйти из игры");
        System.out.println("=====================================");
        System.out.print("Введите номер опции: ");

        int choice = -1;
        while (true) {
            choice = readInt();

            if (choice == -1) {
                System.out.print("Пожалуйста, введите 1, 2 или 0: ");
                continue;
            }
            if (choice >= 0 && choice <= 2) {
                break;
            } else {
                System.out.print("Неверный выбор. Введите 1, 2 или 0: ");
            }
        }
        return choice;
    }

    public static void showScores() {
        clearScreen();
        System.out.println("=====================================");
        System.out.println("         🏆 ЛУЧШИЙ РЕЗУЛЬТАТ 🏆        ");
        System.out.println("=====================================");
        if (bestScore != -1) {
            System.out.println("Текущий рекорд: " + bestScore + " попыток.");
        } else {
            System.out.println("Пока нет сыгранных игр.");
        }
        System.out.println("=====================================");
    }









    public static void playRound() {
        clearScreen();
        int maxNumber = selectDifficulty();

        Random random = new Random();
        int secretNumber = random.nextInt(maxNumber) + 1;

        int attempts = 0;
        boolean guessed = false;

        System.out.println("--- Начинаем игру! ---");
        System.out.println("Я загадал число от 1 до " + maxNumber + ".");

        final int MAX_ATTEMPTS = 10;

        while (!guessed) {
            if (attempts >= MAX_ATTEMPTS) {
                clearScreen();
                System.out.println("❌ У вас закончились попытки! Загаданное число было: " + secretNumber);
                break;
            }

            attempts++;
            System.out.print("Попытка " + attempts + " (из " + MAX_ATTEMPTS + "): Введите ваше число: ");

            int guess = readInt();

            if (guess == -1) {
                attempts--;
                continue;
            }

            if (guess < secretNumber) {
                System.out.println("⬇️ Слишком маленькое!");
            } else if (guess > secretNumber) {
                System.out.println("⬆️ Слишком большое!");
            } else {
                guessed = true;
                System.out.println("🎉 Вы угадали! Число было: " + secretNumber);
                System.out.println("🔥 Вам понадобилось " + attempts + " попыток.");

                if (bestScore == -1 || attempts < bestScore) {
                    bestScore = attempts;
                    System.out.println("⭐ Новый лучший результат!");
                }
            }
        }
    }







    public static int selectDifficulty() {
        int max = 100;
        System.out.println("Выберите уровень сложности:");
        System.out.println("1. Лёгкий (1 - 50)");
        System.out.println("2. Средний (1 - 100) - По умолчанию");
        System.out.println("3. Сложный (1 - 500)");
        System.out.print("Введите номер (1, 2 или 3): ");

        int choice = -1;
        while (choice == -1) {
            choice = readInt();



            if (choice == -1) {
                System.out.print("Пожалуйста, введите 1, 2 или 3: ");
                continue;
            }


            switch (choice) {
                case 1:
                    max = 50;
                    break;
                case 2:
                    max = 100;
                    break;
                case 3:
                    max = 500;
                    break;
                default:
                    System.out.print("Неверный выбор. Используем Средний (1 - 100). Нажмите Enter для продолжения: ");
                    if (scanner.hasNextLine()) scanner.nextLine();
                    scanner.nextLine();
                    choice = 2;
                    break;
            }
        }
        clearScreen();
        return max;
    }





    public static int readInt() {
        while (!scanner.hasNextInt()) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("🛑 Ввод не может быть пустым. Попробуйте ещё раз.");
            } else {
                System.out.println("🛑 Некорректный ввод: '" + input + "'. Пожалуйста, введите целое число.");
            }
            return -1;
        }

        int number = scanner.nextInt();
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        return number;

    }



    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println(" ");
        }
    }




}