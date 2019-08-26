import java.util.Random;
import java.util.Scanner;

public class Game {

    static final int RAZMER_POLYA = 3;
    static final String NULL = "_";
    static final String[][] field = new String[RAZMER_POLYA][RAZMER_POLYA];
    static Random random = new Random();


    public int stepCounter = 0;

    public Scanner vvod = new Scanner(System.in);

    public String[][] getField() {
        return field;
    }

    public void initField(){
        for (int i = 0; i < RAZMER_POLYA; i++) {
            for (int j = 0; j < RAZMER_POLYA; j++) {
                field[i][j] = NULL;
            }
        }
    }

    void printField() {

        for (int i = 0; i < RAZMER_POLYA; i++) {
            for (int j = 0; j < RAZMER_POLYA; j++) {
                System.out.print(field[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private String selectPlayer() {
        String player;
        if (stepCounter % 2 == 0) {
            player = "X";
        } else {
            player = "0";
        }
        return player;
    }

    public void checkIfFilled(String s) {

            if (!s.equals(NULL)) {
                throw new AlreadyFilledFieldException();
            }

    }

     public void step(int x, int y) {

      try {
          checkIfFilled(field[x][y]);

          field[x][y] = selectPlayer();

          stepCounter++;
      }

      catch (AlreadyFilledFieldException e) {
          if (stepCounter % 2 == 0) { System.out.println("Person have used filling field and will pass move");}
                else { System.out.println("Comp have used filling field and will pass move");}
          stepCounter++;
      }

    }

   public void movePerson() {
        int x, y;

           System.out.println("Enter number of row :");
            x = vvod.nextInt();
            System.out.println("Enter number of column:");
            y = vvod.nextInt();

            step(x,y);

    }

    void moveAI() {
        int x, y;

            x = random.nextInt(RAZMER_POLYA);
            y = random.nextInt(RAZMER_POLYA);

            step(x,y);

    }

    boolean checkWin(String sym) {

        for (int i = 0; i < RAZMER_POLYA; i++) {
            int result = 0;
            for (int j = 0; j < RAZMER_POLYA; j++) {
                if (field[i][j] == sym) result++;
            }
            if (result == RAZMER_POLYA) return true;
        }

        for (int i = 0; i < RAZMER_POLYA; i++) {
            int result = 0;
            for (int j = 0; j < RAZMER_POLYA; j++) {
                if (field[j][i] == sym) result++;
            }
            if (result == RAZMER_POLYA) return true;
        }

        int resultLeftToRight = 0;
        for (int i = 0; i < RAZMER_POLYA; i++) {
            for (int j = 0; j < RAZMER_POLYA; j++) {
                if ((i==j) && (field[i][j] == sym)) resultLeftToRight++;
            }
            if (resultLeftToRight == RAZMER_POLYA) return true;
        }

        int resultRightToLeft = 0;
        for (int i = 0, j = RAZMER_POLYA-1; i<RAZMER_POLYA && j>=0; i++, j--) {
                  if (field[i][j] == sym) resultRightToLeft++;}

            if (resultRightToLeft == RAZMER_POLYA) return true;

        return false;
    }




    public static void main(String[] args) {

        Game game = new Game();
        game.initField();
        game.printField();

        while (true) {
        game.movePerson();
        game.printField();
            if (game.checkWin("X")) {
                System.out.println("You are win");
                break;
            }
            game.moveAI();
        System.out.println();
        game.printField();
            if (game.checkWin("0")) {
                System.out.println("comp is win");
                break;
            }
        }

    }





}
