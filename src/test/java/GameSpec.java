import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class GameSpec {

    private Game game;

    @Before
    public void init() {
        game = new Game();


    }


    @Test
    public void mustGenerateTicTacToeField() {
            int expectedSize = 3;
            String[][] field = game.getField();
             assertEquals(field.length, expectedSize);

            for (int i = 0; i < field.length; i++) {
               int actual = field[i].length;
                assertEquals(expectedSize, actual);
        }
    }

    @Test
    public void mustGenerateAllFieldsEmptyStrings(){
        game.initField();
        String[][] field = game.getField();

        for (int i = 0; i < field.length; i++) {
            String[] row = field[i];
            for (int j = 0; j < row.length; j++) {
                assertEquals("_", field[i][j]);
            }
        }
    }

    @Test
    public void testCheckIfFilled(){
        game.checkIfFilled(Game.NULL);

    }

    @Test
    public void mustSetSymbolByCoords(){

        String expected = "X";
        int stepCounterExpected = 1;
        game.initField();
        game.step(1,1);

        assertEquals(expected, game.getField()[1][1]);
        assertEquals(stepCounterExpected, game.stepCounter);

       // System.out.println(game.getField()[2][2]);
    }

    @Test
    public void mustChangePlayerAfterMove() {
        game.initField();

        game.step(1, 1);
        game.step(1, 2);

        String[][] field = game.getField();

        assertEquals("X", field[1][1]);
        assertEquals("0", field[1][2]);
    }

    @Test//(expected = AlreadyFilledFieldException.class)
    public void mustNotOverrideExistingSteps() {

        game.initField();

        game.step(1, 1);
        game.step(1, 1);
        game.step(1, 1);

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void mustNotSetOutOfBoundsOfField() {

        game.initField();
        game.step(5, 5);
    }

    @Test
    public void mustWinPlayerIfFillColumn(){

        boolean winPlayer = true;

        game.initField();
        game.step(0, 0) ;
        game.step(1, 1);
        game.step(1,0);
        game.step(0, 1);
        game.step(2,0);

        assertEquals(winPlayer, game.checkWin("X"));


       // System.out.println(game.checkWin("X"));

    }

    @Test
    public void mustWinPlayerIfFillRow(){

        boolean winPlayer = true;

        game.initField();
        game.step(0,0);
        game.step(1, 1);
        game.step(0,1);
        game.step(2, 2);
        game.step(0,2);

        assertEquals(winPlayer, game.checkWin("X"));


       // System.out.println(game.checkWin("X"));

    }

    @Test
    public void mustWinPlayerIfFillLeftToRightDiagonal(){

        boolean winPlayer = true;

        game.initField();
        game.step(0,0);
        game.step(0,1);
        game.step(1,1);
        game.step(0,2);
        game.step(2,2);

        assertEquals(winPlayer, game.checkWin("X"));


       // System.out.println(game.checkWin("X"));

    }

    @Test
    public void mustWinPlayerIfFillRightToLeftDiagonal(){

        boolean winPlayer = true;

        game.initField();
        game.step(0,2);
        game.step(0,1);
        game.step(1,1);
        game.step(0,0);
        game.step(2,0);

        assertEquals(winPlayer, game.checkWin("X"));


       // System.out.println(game.checkWin("X"));

    }

    @Test
    public void mustWinCompIfFillColumn(){

        boolean winComp = true;

        game.initField();
        game.step(0,1);
        game.step(0,0);
        game.step(0,2);
        game.step(1,0);
        game.step(1,2);
        game.step(2,0);

        assertEquals(winComp, game.checkWin("0"));


       // System.out.println(game.checkWin("0"));

    }

    @Test
    public void mustWinCompIfFillRow(){

        boolean winComp = true;

        game.initField();
        game.step(2,0);
        game.step(0,0);
        game.step(1,0);
        game.step(0,1);
        game.step(2,2);
        game.step(0,2);

        assertEquals(winComp, game.checkWin("0"));


        System.out.println(game.checkWin("0"));

    }

    @Test
    public void mustWinCompIfFillLeftToRightDiagonal(){

        boolean winComp = true;

        game.initField();
        game.step(2,0);
        game.step(0,0);
        game.step(1,0);
        game.step(1,1) ;
        game.step(1,2);
        game.step(2,2);

        assertEquals(winComp, game.checkWin("0"));


        //System.out.println(game.checkWin("0"));

    }

    @Test
    public void mustWinCompIfFillRightToLeftDiagonal(){

        boolean winComp = true;

        game.initField();
        game.step(2,1);
        game.step(0,2);
        game.step(0,0);
        game.step(1,1);
        game.step(1,2);
        game.step(2,0);

        assertEquals(winComp, game.checkWin("0"));


       // System.out.println(game.checkWin("0"));

    }

}




