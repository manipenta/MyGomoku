package hu.penzestamas.gomoku;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.FitWindowsFrameLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import hu.penzestamas.gomoku.adapters.BoardAdapter;
import hu.penzestamas.gomoku.models.Board;
import hu.penzestamas.gomoku.models.Config;
import hu.penzestamas.gomoku.models.FieldModel;
import hu.penzestamas.gomoku.views.FieldView;

public class MainActivity extends AppCompatActivity implements BoardAdapter.FieldClickedListener{

    private RecyclerView mBoardView;
    private GridLayoutManager mLayoutManager;
    private BoardAdapter mAdapter;
    private Board mBoard;
    private int playerOnTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        mBoardView = (RecyclerView)findViewById(R.id.board_recycler_view);
        mBoardView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,10);
        mBoardView.setLayoutManager(mLayoutManager);

        if (savedInstanceState != null) {
            restoreGame(savedInstanceState);
        } else {
            initializeGame();
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * Saves the current point, the number of cards on board and the card list.
     *
     * @param outState the Bundle the data are saved into.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putInt(TOTAL_POINTS, mTotalPoint);
//        outState.putInt(CARD_NUMBER, mCardNum);
//        outState.putParcelableArrayList(CARD_LIST, mCardList);
    }


    private void initializeGame() {
        playerOnTurn = Board.STATE_O;
        mBoard = new Board(15,10);
        mAdapter = new BoardAdapter(this,R.layout.board_item,mBoard,this);
        mBoardView.setAdapter(mAdapter);
    }

    private void restoreGame(Bundle savedInstanceState) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFielClicked(int position) {
       if(mBoard.move(position,playerOnTurn)){
           mAdapter.updateBoardItem(position);
           if(mBoard.isTotalWin(mBoard.getField(position))){
               Config.alertPopUp(this,"Játék Vége!!","Gratulálok győztél a körrel");
           } else {
               playerOnTurn = Board.STATE_X;
               FieldModel model = mBoard.evaluateBoard(playerOnTurn);
               if (mBoard.move(model, playerOnTurn)) {
                   int temp = mBoard.getPositionOf(model);
                   mAdapter.updateBoardItem(temp);
                   if(mBoard.isTotalWin(model)) {
                       Config.alertPopUp(this, "Játék Vége!!", "Sajnos győzött a számítógép az X el");
                   }
                   playerOnTurn = Board.STATE_O;

               }
           }

       }

    }
}
