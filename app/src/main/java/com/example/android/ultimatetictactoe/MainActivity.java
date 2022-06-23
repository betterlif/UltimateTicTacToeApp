package com.example.android.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    private final List<int[]> combinationsList = new ArrayList<>();

    private volatile int[] boxPositions;

    private volatile int playerTurn = 1;

    private volatile int totalSelectedBoxes = 1;

    private String player1, player2;

    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerOneName,  playerTwoName, scoreCard;
    private volatile ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    private volatile int playerOneScore = 0, playerTwoScore = 0;

    private boolean isAIselected;

    private String difficultyLevel;

    private Vibrator myVib;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        isAIselected = extras.getBoolean("AI_SELECTED");

        if(isAIselected) {
            player1 = "AI";
            player2 = "You";
            difficultyLevel = extras.getString("DIFFICULTY_LEVEL");
            if(difficultyLevel.equals("EASY")) {
                setTitle("Play against AI - Easy");
                setTheme(R.style.AIEasyAppTheme);
            }
            else {
                setTitle("Play against AI - Hard");
                setTheme(R.style.AIHardAppTheme);
            }
        }
        else {
            setTitle("Multiplayer");
            setTheme(R.style.MPAppTheme);
            player1 = "Player 1";
            player2 = "Player 2";
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneName = findViewById(R.id.player_one_name);
        playerTwoName = findViewById(R.id.player_two_name);
        scoreCard = findViewById(R.id.score_card);

        playerOneLayout = findViewById(R.id.player_one_layout);
        playerTwoLayout = findViewById(R.id.player_two_layout);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{0, 4, 8});
        combinationsList.add(new int[]{2, 4, 6});

        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        playerOneName.setText(player1);
        playerTwoName.setText(player2);

        startGame();

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(0)) {
                    myVib.vibrate(50);
                    if(isAIselected) {
                        humansTurn((ImageView)view, 0);
                    }
                    else {
                        performAction((ImageView)view, 0);
                    }
                }
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isBoxSelectable(1)) {
                    myVib.vibrate(50);
                    if(isAIselected) {
                        humansTurn((ImageView)view, 1);
                    }
                    else {
                        performAction((ImageView)view, 1);
                    }
                }
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isBoxSelectable(2)) {
                    myVib.vibrate(50);
                    if(isAIselected) {
                        humansTurn((ImageView)view, 2);
                    }
                    else {
                        performAction((ImageView)view, 2);
                    }
                }
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isBoxSelectable(3)) {
                    myVib.vibrate(50);
                    if(isAIselected) {
                        humansTurn((ImageView)view, 3);
                    }
                    else {
                        performAction((ImageView)view, 3);
                    }
                }
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isBoxSelectable(4)) {
                    myVib.vibrate(50);
                    if(isAIselected) {
                        humansTurn((ImageView)view, 4);
                    }
                    else {
                        performAction((ImageView)view, 4);
                    }
                }
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isBoxSelectable(5)) {
                    myVib.vibrate(50);
                    if(isAIselected) {
                        humansTurn((ImageView)view, 5);
                    }
                    else {
                        performAction((ImageView)view, 5);
                    }
                }
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isBoxSelectable(6)) {
                    myVib.vibrate(50);
                    if(isAIselected) {
                        humansTurn((ImageView)view, 6);
                    }
                    else {
                        performAction((ImageView)view, 6);
                    }
                }
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isBoxSelectable(7)) {
                    myVib.vibrate(50);
                    if(isAIselected) {
                        humansTurn((ImageView)view, 7);
                    }
                    else {
                        performAction((ImageView)view, 7);
                    }
                }
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isBoxSelectable(8)) {
                    myVib.vibrate(50);
                    if(isAIselected) {
                        humansTurn((ImageView)view, 8);
                    }
                    else {
                        performAction((ImageView)view, 8);
                    }
                }
            }
        });
    }

    private List<Integer> getAvailableMoves() {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            if(boxPositions[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }

    private void humansTurn(ImageView imageView, int selectBoxPosition) {
        boxPositions[selectBoxPosition] = playerTurn;
        imageView.setImageResource(R.drawable.zero_icon);

        if(checkPlayerWin()) {
            playerTwoScore++;
            setScore();
            WinDialog winDialog = new WinDialog(MainActivity.this, "X wins.", MainActivity.this);
            winDialog.setCancelable(false);
            winDialog.show();
        }
        else if(totalSelectedBoxes == 9) {
            WinDialog winDialog = new WinDialog(MainActivity.this, "Match Draw.", MainActivity.this);
            winDialog.setCancelable(false);
            winDialog.show();
        }
        else {
            changePlayerTurn(1);
            totalSelectedBoxes++;

            if(difficultyLevel.equals("EASY")) {
                mHandler.postDelayed(computersTurnEasy, 500);
            }
            else {
                mHandler.postDelayed(computersTurnHard, 500);
            }

        }
    }

    private Runnable computersTurnEasy = new Runnable() {
        @Override
        public void run() {
            List<Integer> moves = getAvailableMoves();
            Random rand = new Random();
            int randomMove = moves.get(rand.nextInt(moves.size()));

            boxPositions[randomMove] = playerTurn;

            switch(randomMove) {
                case 0:
                    image1.setImageResource(R.drawable.cross_icon);
                    break;
                case 1:
                    image2.setImageResource(R.drawable.cross_icon);
                    break;
                case 2:
                    image3.setImageResource(R.drawable.cross_icon);
                    break;
                case 3:
                    image4.setImageResource(R.drawable.cross_icon);
                    break;
                case 4:
                    image5.setImageResource(R.drawable.cross_icon);
                    break;
                case 5:
                    image6.setImageResource(R.drawable.cross_icon);
                    break;
                case 6:
                    image7.setImageResource(R.drawable.cross_icon);
                    break;
                case 7:
                    image8.setImageResource(R.drawable.cross_icon);
                    break;
                case 8:
                    image9.setImageResource(R.drawable.cross_icon);
            }

            if(checkPlayerWin()) {
                playerOneScore++;
                setScore();
                WinDialog winDialog = new WinDialog(MainActivity.this, "X wins.", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if(totalSelectedBoxes == 9) {
                WinDialog winDialog = new WinDialog(MainActivity.this, "Match Draw.", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        }
    };

    private Runnable computersTurnHard = new Runnable() {

        private int player = 1, opponent = 2;

        @Override
        public void run() {
            int bestMove;
            if(totalSelectedBoxes == 1) {
                Random rand = new Random();
                List<Integer> moves = getAvailableMoves();
                bestMove = moves.get(rand.nextInt(moves.size()));
            }
            else {
                bestMove = findBestMove(boxPositions);
            }

            boxPositions[bestMove] = playerTurn;

            switch(bestMove) {
                case 0:
                    image1.setImageResource(R.drawable.cross_icon);
                    break;
                case 1:
                    image2.setImageResource(R.drawable.cross_icon);
                    break;
                case 2:
                    image3.setImageResource(R.drawable.cross_icon);
                    break;
                case 3:
                    image4.setImageResource(R.drawable.cross_icon);
                    break;
                case 4:
                    image5.setImageResource(R.drawable.cross_icon);
                    break;
                case 5:
                    image6.setImageResource(R.drawable.cross_icon);
                    break;
                case 6:
                    image7.setImageResource(R.drawable.cross_icon);
                    break;
                case 7:
                    image8.setImageResource(R.drawable.cross_icon);
                    break;
                case 8:
                    image9.setImageResource(R.drawable.cross_icon);
            }

            if(checkPlayerWin()) {
                playerOneScore++;
                setScore();
                WinDialog winDialog = new WinDialog(MainActivity.this, "X wins.", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if(totalSelectedBoxes == 9) {
                WinDialog winDialog = new WinDialog(MainActivity.this, "Match Draw.", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }

        }

        private Boolean isMovesLeft(int board[]) {
            for(int box: board) {
                if(box == 0) {
                    return true;
                }
            }
            return false;
        }

        // This is the evaluation function as discussed
        // in the previous article ( http://goo.gl/sJgv68 )
        int evaluate(int board[]) {
            for(int i = 0; i < combinationsList.size(); i++) {
                final int[] combination = combinationsList.get(i);

                if(board[combination[0]] == board[combination[1]] &&  board[combination[1]] == board[combination[2]]) {
                    if(board[combination[0]] == player) {
                        return +10;
                    }
                    else {
                        return -10;
                    }
                }
            }
            return 0;
        }

        // This is the minimax function. It considers all
        // the possible ways the game can go and returns
        // the value of the board
        int minimax(int board[], int depth, Boolean isMax) {
            int score = evaluate(board);

            // If Maximizer has won the game
            // return his/her evaluated score
            if (score == 10)
                return score;

            // If Minimizer has won the game
            // return his/her evaluated score
            if (score == -10)
                return score;

            // If there are no more moves and
            // no winner then it is a tie
            if (!isMovesLeft(board))
                return 0;

            // If this maximizer's move
            if(isMax) {
                int best = -1000;

                // Traverse all cells
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3 && (i * 3 + j) < board.length; j++) {
                        // Check if cell is empty
                        if (board[i * 3 + j] == 0) {
                            // Make the move
                            board[i * 3 + j] = player;

                            // Call minimax recursively and choose
                            // the maximum value
                            best = Math.max(best, minimax(board, depth + 1, false));

                            // Undo the move
                            board[i * 3 + j] = 0;
                        }
                    }
                }
                return best;
            }

            // If this minimizer's move
            else
            {
                int best = 1000;

                // Traverse all cells
                for (int i = 0; i < 3; i++)
                {
                    for (int j = 0; j < 3 && (i * 3 + j) < board.length; j++)
                    {
                        // Check if cell is empty
                        if (board[i * 3 + j] == 0)
                        {
                            // Make the move
                            board[i * 3 + j] = opponent;

                            // Call minimax recursively and choose
                            // the minimum value
                            best = Math.min(best, minimax(board, depth + 1, true));

                            // Undo the move
                            board[i * 3 + j] = 0;
                        }
                    }
                }
                return best;
            }
        }

        // This will return the best possible
        // move for the player
        int findBestMove(int board[]) {
            int bestVal = -1000;
            int bestMove = -1;

            // Traverse all cells, evaluate minimax function
            // for all empty cells. And return the cell
            // with optimal value.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3 && (i * 3 + j) < board.length; j++) {
                    // Check if cell is empty
                    if (board[i * 3 + j] == 0) {
                        // Make the move
                        board[i * 3 + j] = player;

                        // compute evaluation function for this
                        // move.
                        int moveVal = minimax(board, 0, false);

                        // Undo the move
                        board[i * 3 + j] = 0;

                        // If the value of the current move is
                        // more than the best value, then update
                        // best/
                        if (moveVal > bestVal) {
                            bestMove = i * 3 + j;
                            bestVal = moveVal;
                        }
                    }
                }
            }
            return bestMove;
        }
    };

    private void performAction(ImageView imageView, int selectBoxPosition) {
        boxPositions[selectBoxPosition] = playerTurn;
        if(playerTurn == 1) {
            imageView.setImageResource(R.drawable.cross_icon);

            if(checkPlayerWin()) {
                playerOneScore++;
                setScore();
                WinDialog winDialog = new WinDialog(MainActivity.this, "X wins.", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if(totalSelectedBoxes == 9) {
                WinDialog winDialog = new WinDialog(MainActivity.this, "Match Draw.", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else {
                changePlayerTurn(2);

                totalSelectedBoxes++;
            }
        }
        else {
            imageView.setImageResource(R.drawable.zero_icon);

            if(checkPlayerWin()) {
                playerTwoScore++;
                setScore();
                WinDialog winDialog = new WinDialog(MainActivity.this, "O wins.", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if(selectBoxPosition == 9) {
                WinDialog winDialog = new WinDialog(MainActivity.this, "Match Draw.", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;

        if(playerTurn == 1) {
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
        else {
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
    }

    private boolean checkPlayerWin() {
        for(int i = 0; i < combinationsList.size(); i++) {
            final int[] combination = combinationsList.get(i);

            if(boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn && boxPositions[combination[2]] == playerTurn) {
                return true;
            }
        }

        return false;
    }

    private boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0;
    }

    public void startGame() {
        boxPositions = new int[9];

        playerTurn = 1;

        totalSelectedBoxes = 1;

        image1.setImageResource(R.drawable.transparent_background);
        image2.setImageResource(R.drawable.transparent_background);
        image3.setImageResource(R.drawable.transparent_background);
        image4.setImageResource(R.drawable.transparent_background);
        image5.setImageResource(R.drawable.transparent_background);
        image6.setImageResource(R.drawable.transparent_background);
        image7.setImageResource(R.drawable.transparent_background);
        image8.setImageResource(R.drawable.transparent_background);
        image9.setImageResource(R.drawable.transparent_background);

        playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
        playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);

        if(isAIselected) {
            if(difficultyLevel.equals("EASY")) {
                mHandler.postDelayed(computersTurnEasy, 500);
            }
            else {
                mHandler.postDelayed(computersTurnHard, 500);
            }
        }
    }

    private void setScore() {
        scoreCard.setText(playerOneScore + " - " + playerTwoScore);
    }



    public void goToMenu() {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        if(Build.VERSION.SDK_INT>=16 && Build.VERSION.SDK_INT<21){
            finishAffinity();
        } else if(Build.VERSION.SDK_INT>=21){
            finishAndRemoveTask();
        }
    }
}