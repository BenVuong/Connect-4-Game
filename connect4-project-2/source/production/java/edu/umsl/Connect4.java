package edu.umsl;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Connect4 {

    char[][] table = new char [6][7];
    public Connect4()// constructor
    {
        for(int row = 0; row<table.length; row++ )
        {
            for(int col = 0; col<table[row].length; col++)
            {
                table[row][col] = ' ';
            }
        }
    }
    public void displayBoard()//updates and print out the current state of the board
    {
        System.out.println(" 0 1 2 3 4 5 6 ");
        for(int row = 0; row<table.length; row++ )
        {
            System.out.print("|");
            for(int col = 0; col<table[row].length; col++)
            {
                System.out.print(table[row][col]+"|");
            }
            System.out.println(" ");
        }
        System.out.println("---------------");
    }
    public void player1placeChip()//let player 1 drop in their chip onto the board
    {
        Scanner input = new Scanner(System.in);
        int col;
        boolean good=true, continueInput =true, valid=true;
        do{
            try{
                System.out.print("Player 1 please enter in a column (0,6): ");
                col = input.nextInt();
                while(good)
                {
                    if(col<0||col>6)
                    {
                        System.out.print("Error: input needs to be from 0 to 6: ");
                        col = input.nextInt();
                    }
                    else
                    {
                        if(table[0][col]!=' ')
                        {
                            System.out.print("Error: column is filled, try another: ");
                            col = input.nextInt();
                        }
                        else
                        {
                            good = false;
                        }

                    }

                }


                for(int r=5;r>=0;r--)
                {
                    if(table[r][col]==' ')
                    {
                        table[r][col]='x';
                        break;
                    }
                }
                displayBoard();
                continueInput = false;
            }
            catch(InputMismatchException ex)
            {
                System.out.print("Error: input must a number: ");
                input.nextLine();
            }
        }
        while(continueInput);
    }

    public void player2placeChip()//let player 2 drop their chip on the board
    {
        Scanner input = new Scanner(System.in);
        int col;
        boolean good=true, continueInput =true;
        do{
            try{
                System.out.print("Player 2 please enter in a column (0,6): ");
                col = input.nextInt();
                while(good)
                {
                    if(col<0||col>6)
                    {
                        System.out.print("Error: input needs to be from 0 to 6: ");
                        col = input.nextInt();
                    }
                    else
                    {
                        if(table[0][col]!=' ')
                        {
                            System.out.print("Error: column is filled, try another: ");
                            col = input.nextInt();
                        }
                        else
                        {
                            good = false;
                        }
                    }
                }
                for(int r=5;r>=0;r--)
                {
                    if(table[r][col]==' ')
                    {
                        table[r][col]='o';
                        break;
                    }
                }
                displayBoard();
                continueInput = false;
            }
            catch(InputMismatchException ex)
            {
                System.out.print("Error: input must a number: ");
                input.nextLine();
            }
        }
        while(continueInput);
    }

    public boolean isFull()// checks to see if there is any empty space left on board.
    {
        boolean empty =true;
        for(int row = 0; row<table.length; row++ )
        {
            for(int col = 0; col<table[row].length; col++)
            {
                if (table[row][col] == ' ')
                {
                    empty = false;
                    return empty;
                }
                else
                {
                    empty = true;
                }
            }
        }
        return empty;
    }

    public boolean checkHorizontal (char token)//check for 4 of a kind in any row
    {
        int count = 0;
        for(int row = 5; row>=0; row-- )
        {
            count = 0;
            for(int col = 0; col<table[row].length; col++)
            {
                if(table[row][col] == token)
                {
                    count++;
                }
                else
                {
                    count = 0;
                }
                if(count>=4)
                {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean checkVertical (char token)//check to see if there is any 4 of a kind in any column
    {
        int count = 0;
        for(int col = 0; col <7; col++)
        {
            for(int row = 0; row<6; row++ )
            {
                if(table[row][col] == token)
                {
                    count++;
                }
                else
                {
                    count = 0;
                }
                if(count>=4)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDiagonalForward (char token)// check to see if 4 of a kind found in a forward diagonal
    {
        int count;
        for(int row = 0; row<table.length-3; row++)
        {
            for(int col = 0; col<table[row].length-3; col++)
            {
                count =0;
                for (int d=0; d<4; d++)
                {
                    if(table[row+d][col+d]==token)
                    {
                        count++;
                    }
                    else
                    {
                        count=0;
                    }
                    if(count>=4)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDiagonalBackwards( char token)// check to see if 4 of a kind found in a backwards diagonal
    {
        int count;
        for(int row = 0; row<table.length-3; row++)
        {
            for(int col = 3; col<table[row].length; col++)
            {
                count = 0;
                for (int d=0; d<4; d++)
                {
                    if(table[row+d][col-d]==token)
                    {
                        count++;
                    }
                    else
                    {
                        count=0;
                    }
                    if(count>=4)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkWinnerPlayer1(char token)//check to see if player 1 has won
    {

        if(checkHorizontal(token)||checkVertical(token)||
                checkDiagonalBackwards(token)||checkDiagonalForward(token))
        {
            System.out.println("Player 1 win!");
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkWinnerPlayer2(char token)//check to see if player 2 has won
    {
        if(checkHorizontal(token)||checkVertical(token)||
                checkDiagonalBackwards(token)||checkDiagonalForward(token))
        {
            System.out.println("Player 2 win!");
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main (String[] args )//main method
    {

        Connect4 table = new Connect4();
        boolean win;
        System.out.println("Welcome to Connect 4!\nPlayer 1 is X and Player 2 is O");
        table.displayBoard();
        do {
            table.player1placeChip();
            win = table.checkWinnerPlayer1('x');
            if(win==true)
            {
                break;
            }
            table.player2placeChip();
            win = table.checkWinnerPlayer2('o');
            if(win==true)
            {
                break;
            }

            if(table.isFull()==true)
            {
                System.out.println("Its a draw!");
                win=true;
            }
        }
        while(win==false);
        System.out.print("Thank you for playing");
    }
}