package com.hso.mytictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hso.mytictactoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    Button button0,button1,button2,button3,button4,button5,button6,button7,button8;
    TextView gameText;

    int playerO=0;
    int playerX=1;

    int[] filledPos = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean isGameActive = true;
    int activePlayer=playerO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        button0 = binding.button0;
        button1 = binding.button1;
        button2 = binding.button2;
        button3 = binding.button3;
        button4 = binding.button4;
        button5 = binding.button5;
        button6 = binding.button6;
        button7 = binding.button7;
        button8 = binding.button8;

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);

        gameText= binding.gamerText;


    }

    @Override
    public void onClick(View v) {
        //button press
        if (!isGameActive){
            return;
        }

        Button clickedButton = findViewById(v.getId());
        int clickedTag = Integer.parseInt(v.getTag().toString());

        if (filledPos[clickedTag] != -1){
            return;
        }

        filledPos[clickedTag]=activePlayer;

        if (activePlayer == playerO){
            clickedButton.setText("O");
            clickedButton.setBackground(getDrawable(R.color.O));
            clickedButton.setTextColor(getColor(R.color.O_Text));
            activePlayer= playerX;
            gameText.setText("Player X");
        }else {
            clickedButton.setText("X");
            clickedButton.setBackground(getDrawable(R.color.X));
            clickedButton.setTextColor(getColor(R.color.X_Text));
            activePlayer= playerO;
            gameText.setText("Player O");
        }
        checkForWin();
    }
    private void checkForWin(){
        int[][] winPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for (int i=0;i<8;i++){
            int val0 = winPos[i][0];
            int val1 = winPos[i][1];
            int val2 = winPos[i][2];

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]){
                if (filledPos[val0] != -1){
                    isGameActive=false;

                    if (filledPos[val0] == playerO){
                        showDialog("O winner !");
                    }else if (filledPos[val0] == playerX){
                        showDialog("X winner !");
                    }
                }

            }
        }
    }

    private void showDialog(String text){
        new AlertDialog.Builder(this)
                .setTitle(text)
                .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }
                }).show();
    }
    private void restartGame(){
        activePlayer = playerO;
        gameText.setText("Player O");
        filledPos = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");

        button0.setBackground(getDrawable(R.color.background_button));
        button1.setBackground(getDrawable(R.color.background_button));
        button2.setBackground(getDrawable(R.color.background_button));
        button3.setBackground(getDrawable(R.color.background_button));
        button4.setBackground(getDrawable(R.color.background_button));
        button5.setBackground(getDrawable(R.color.background_button));
        button6.setBackground(getDrawable(R.color.background_button));
        button7.setBackground(getDrawable(R.color.background_button));
        button8.setBackground(getDrawable(R.color.background_button));

        isGameActive=true;
    }
}