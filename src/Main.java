import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int bossHealth = 1000;
    public static int bossDamage = 80;
    public static String bossDefenceType;
    public static int[] heroesHealth = {250, 230, 200, 150, 320, 170, 180, 200};
    public static int[] heroesDamage = {30, 25, 20, 0, 15, 20, 15, 20};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        medicHill();
        Golem();
        Lucky();
        Berserk();
        Thor();
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: " + (bossDefenceType == null ? "No defence" : bossDefenceType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + "; damage: "
                    + heroesDamage[i]);
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesAttackType[i] == "Medic"){
                medicHill();
            }
            if (heroesAttackType[i] == "Golem"){
                Golem();
            }
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit = heroesDamage[i];
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(6) + 2;
                    hit = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + hit);
                }
                if (bossHealth - hit < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }

            }
        }
    }
    public static void medicHill(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesAttackType[i] == "Medic"){
                continue;
            }
            if (heroesHealth[i] <= 100 && heroesHealth[i] > 0 ){
                heroesHealth[i] = heroesHealth[i] + 20;
                System.out.println(heroesAttackType[i] + " cured");
            }

            break;
        }

    }
    public static void Golem() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] < 0){
                heroesHealth[4] = 0;
            }
            if (heroesHealth[4] == 0){
                heroesDamage[4] = 0;
            }
            if (i == 4) {
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[4] > 0) {
                heroesHealth[4] -= bossDamage / 5;
                heroesHealth[i] += bossDamage / 5;
            }
        }
        System.out.println("Golem take 1/5 damage");
    }

    public static void Lucky() {
        Random random = new Random();
        boolean uklon = random.nextBoolean();
        if (heroesHealth[5] < 0){
            heroesHealth[5] = 0;
        }
        if (heroesHealth[5] == 0){
            heroesDamage[5] = 0;
        }
        if (uklon){
            heroesHealth[5] += bossDamage - 10;
            System.out.println("lucky evaded");
        }
    }

    public static void Berserk() {
        if (heroesHealth[6] < 0){
            heroesHealth[6] = 0;
        }
        if (heroesHealth[6] == 0){
            heroesDamage[6] = 0;
        }
        if (heroesHealth[6] > 0) {
            heroesHealth[6] += bossDamage/2;
            bossHealth -= bossDamage/2;
            System.out.println("berserk super damage");
        }
    }
    public static void Thor() {
        Random random = new Random();
        boolean stan = random.nextBoolean();
        if (heroesHealth[7] < 0){
            heroesHealth[7] = 0;
        }
        if (heroesHealth[7] == 0){
            heroesDamage[7] = 0;
        }
        if (heroesHealth[7] > 0 && stan){
            bossDamage -= 80;
            System.out.println("Boss stunned");
        }else {
            bossDamage = 80;
        }
    }
}