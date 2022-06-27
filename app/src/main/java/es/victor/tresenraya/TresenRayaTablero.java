package es.victor.tresenraya;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TresenRayaTablero extends View {

    private final int TableroColor;
    private final int XColor;
    private final int OColor;
    private final int LineaGanador;
    private final Paint paint = new Paint();
    private int cellSize = getWidth()/3;
    private final Gamelogic game;
    private boolean winningLine = false;

    public TresenRayaTablero(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new Gamelogic();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TresenRayaTablero, 0, 0);

        try {
            TableroColor = a.getInteger(R.styleable.TresenRayaTablero_TableroColor, 0);
            XColor = a.getInteger(R.styleable.TresenRayaTablero_XColor, 0);
            OColor = a.getInteger(R.styleable.TresenRayaTablero_OColor, 0);
            LineaGanador = a.getInteger(R.styleable.TresenRayaTablero_LineaGanador, 0);


        }finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width, height);

        int dimensions = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimensions/3;

        setMeasuredDimension(dimensions, dimensions);
    }

    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawTableroJuego(canvas);
        drawMarkers(canvas);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y/cellSize);
            int col = (int) Math.ceil(x/cellSize);

            if(!winningLine) {
                if (game.updateGameBoard(row, col)) {
                    invalidate();

                    if(game.winnerCheck()){
                        winningLine = true;
                        invalidate();
                    }

                    if (game.getPlayer() % 2 == 0) {
                        game.setPlayer(game.getPlayer() - 1);
                    } else {
                        game.setPlayer(game.getPlayer() + 1);
                    }
                }
            }

            invalidate();

            return true;
        }

        return false;
    }

    private void drawTableroJuego(Canvas canvas){
        paint.setColor(TableroColor);
        paint.setStrokeWidth(16);

        for (int c=1; c<3; c++){
            canvas.drawLine(cellSize*c,0,cellSize*c, canvas.getWidth(), paint);
        }
        for (int r=1; r<3; r++){
            canvas.drawLine(0,cellSize*r,canvas.getWidth(), cellSize*r, paint);
        }
    }

    private void drawMarkers(Canvas canvas){
            for (int r=0; r<3; r++){
                for (int c=0; c<3; c++){
                    if (game.getGameBoard()[r][c] != 0){
                        if (game.getGameBoard()[r][c] == 1){
                            drawX(canvas, r, c);
                        }
                        else {
                            drawO(canvas, r, c);
                        }
                    }
                }
            }

    }

    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(XColor);
        canvas.drawLine((float) ((col+1)*cellSize-cellSize*0.2), (float) (row*cellSize+cellSize*0.2), (float) (col*cellSize+cellSize*0.2), (float) ((row+1)*cellSize-cellSize*0.2), paint);
        canvas.drawLine((float) (col*cellSize+cellSize*0.2), (float) (row*cellSize+cellSize*0.2), (float) ((col+1)*cellSize-cellSize*0.2), (float) ((row+1)*cellSize-cellSize*0.2), paint);
    }
    private void drawO(Canvas canvas, int row, int col){
        paint.setColor(OColor);
        canvas.drawOval((float) (col*cellSize+cellSize*0.2), (float) (row*cellSize+cellSize*0.2), (float) ((col*cellSize+cellSize)-cellSize*0.2), (float) ((row*cellSize+cellSize)-cellSize*0.2), paint);
    }

    public void setUpGame(Button playAgain, Button home, TextView playerDisplay, String[] names){
        game.setPlayAgainBTN(playAgain);
        game.setHomeBTN(home);
        game.setPlayerTurn(playerDisplay);
        game.setPlayerNames(names);
    }

    public void resetGame(){
        game.resetGame();
        winningLine = false;
    }
}
