package com.example.saper;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements UserAction {

    private ConstraintLayout mConstraintLayout;
    private LinearLayout drawLayout;
    private TextView mInfoTextView;
    private Button yellowButton;
    private Button redButton;
    private Button greenwButton;
    MyButtonLictener myButtonLictener;
    MainActivity mainActivity;

    GUIBoard board;
    private SaperLogic logic;
    private GeneratorBoard generatorBoard;

    TextView textView;

    TextView voronCount;
    int intVoronCount = 0;
    private static final String KEY_COUNT = "COUNT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        mConstraintLayout = findViewById(R.id.constraintLayout);
        mInfoTextView = findViewById(R.id.textViewInfo);

        yellowButton = findViewById(R.id.buttonYellow);
        redButton = findViewById(R.id.buttonRed);
        greenwButton = findViewById(R.id.buttonGreen);

        myButtonLictener = new MyButtonLictener();
        yellowButton.setOnClickListener(myButtonLictener);
        redButton.setOnClickListener(myButtonLictener);
        greenwButton.setOnClickListener(myButtonLictener);

        drawLayout = findViewById(R.id.drawLayout);

        /**
         * класс рисования доски
         */
        board = drawLayout.findViewWithTag("board");

        logic = new Easy();
        generatorBoard = new GeneratorBoard() {
            @Override
            public Cell[][] generate() {
                return new Cell[][]{
                        {new GUICell(true), new GUICell(true), new GUICell(true)},
                        {new GUICell(true), new GUICell(true), new GUICell(true)},
                        {new GUICell(true), new GUICell(true), new GUICell(true)}};
            }
        };

        initGame();

        drawLayout.setOnTouchListener((v, e) -> {
            int x = (int) (e.getX() / 50);
            int y = (int) (e.getY() / 50);
            boolean suggestBomb = false;
            suggestBomb = true;

            logic.suggest(x, y, suggestBomb);
            board.drawCell(x, y);

            if (this.logic.shouldBang(x, y)) {
                this.board.drawBang();
            }
            if (this.logic.finish()) {
                board.drawCongratulate();
            }
            board.invalidate();

            return false;
        });


//        drawClass = new DrawClass(drawLayout.getContext());
//        drawClass.setBackgroundColor(Color.WHITE);

//       addContentView();
//       setContentView(drawClass);


        voronCount = findViewById(R.id.voronCount);

        if (savedInstanceState != null) {
            intVoronCount = savedInstanceState.getInt(KEY_COUNT);
            voronCount.setText("Ворон: " + intVoronCount);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, intVoronCount);
    }

    public void changeColorGreen(View view) {
    }

    public void changeColorRed(View view) {
    }

    @Override
    public void initGame() {
        final Cell[][] cells = generatorBoard.generate();
        this.board.drawBoard(cells);
        this.logic.LoadBoard(cells);
    }

    @Override
    public void select(int x, int y, boolean bomb) {
        this.logic.suggest(x, y, bomb);
        board.drawCell(x, y);
        if (this.logic.shouldBang(x, y)) {
            this.board.drawBang();
        }
        if (this.logic.finish()) {
            board.drawCongratulate();
            textView = findViewById(R.id.bombMessage);
            textView.setText("!!!!!!!!!!!!!!!!!!!!");
        }
    }

    /**
     * подсчет ворон
     * @param view
     */
    public void voronCount(View view) {
        intVoronCount++;
        voronCount.setText("Ворон: " + intVoronCount);

    }

    /**
     * изменение цвета
     */
    class MyButtonLictener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonRed: {
                    mInfoTextView.setText(R.string.red);
                    mConstraintLayout.setBackgroundColor(ContextCompat.getColor(mainActivity, R.color.redColor));
                    break;
                }
                case R.id.buttonYellow: {
                    mInfoTextView.setText(R.string.tellow);
                    mConstraintLayout.setBackgroundColor(getResources().getColor(R.color.yellowColor));
                    break;
                }
                case R.id.buttonGreen: {
                    mInfoTextView.setText(R.string.green);
                    mConstraintLayout.setBackgroundColor(ContextCompat.getColor(mainActivity, R.color.greenColor));
                    break;
                }
            }

        }
    }

}
