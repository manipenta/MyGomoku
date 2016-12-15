package hu.penzestamas.gomoku.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by penzes.tamas on 2016.10.19..
 */

public class Board  {

    public static final int STATE_O = 2;

    public static final int STATE_X = 1;

    private int rownum;

    private int colnum;

    private FieldModel[][] boardModel;




    public Board(int rownum, int colnum, FieldModel[][] boardInt) {
        this.rownum = rownum;
        this.colnum = colnum;
        this.boardModel = boardInt;
    }


    public Board(int rownum, int colnum) {
        this.rownum = rownum;
        this.colnum = colnum;
        initiateFields();
    }

    public void initiateFields(){
        boardModel = new FieldModel[rownum][colnum];
        for(int i = 0; i < rownum; i++){
            for (int j = 0; j < colnum; j++){
                boardModel[i][j] = new FieldModel(0,i,j,0);
            }
        }
    }

    public boolean move(int position, int player){
        if(getField(position).getState() == 0){
            getField(position).setState(player);
            return true;
        } else {
            return false;
        }
    }

    public boolean isRowWin(FieldModel model){
        StringBuilder builder = new StringBuilder();

        for(int i = model.getYcord()-4; i < model.getYcord() + 5; i++){
            if(i >=0 && i < colnum) {
                builder.append(boardModel[model.getXcord()][i].toString());
            }
        }
        if(model.getState() == Board.STATE_O){
            return builder.toString().contains("OOOOO");
        } else if(model.getState()==Board.STATE_X){
            return builder.toString().contains("XXXXX");
        } else {
            return false;
        }
    }

    public boolean isColWin(FieldModel model){
        StringBuilder builder = new StringBuilder();

        for(int i = model.getXcord()-4; i < model.getXcord() + 5; i++){
            if(i >=0 && i < rownum) {
                builder.append(boardModel[i][model.getYcord()].toString());
            }
        }
        if(model.getState() == Board.STATE_O){
            return builder.toString().contains("OOOOO");
        } else if(model.getState()==Board.STATE_X){
            return builder.toString().contains("XXXXX");
        } else {
            return false;
        }
    }

    public boolean isColRowWin(FieldModel model){
        StringBuilder builder = new StringBuilder();

        for(int i = -4; i < 5; i++){
            if(model.getXcord()+i >=0 && model.getXcord() +i < rownum && model.getYcord()+i >= 0 && model.getYcord()+i < colnum) {
                builder.append(boardModel[model.getXcord()+i][model.getYcord()+i].toString());
            }
        }
        if(model.getState() == Board.STATE_O){
            return builder.toString().contains("OOOOO");
        } else if(model.getState()==Board.STATE_X){
            return builder.toString().contains("XXXXX");
        } else {
            return false;
        }
    }

    public boolean isRowColWin(FieldModel model){
        StringBuilder builder = new StringBuilder();

        for(int i = -4; i < 5; i++){
            if(model.getXcord()+i >=0 && model.getXcord() +i < rownum && model.getYcord()-i >= 0 && model.getYcord()-i < colnum) {
                builder.append(boardModel[model.getXcord()+i][model.getYcord()-i].toString());
            }
        }
        if(model.getState() == Board.STATE_O){
            return builder.toString().contains("OOOOO");
        } else if(model.getState()==Board.STATE_X){
            return builder.toString().contains("XXXXX");
        } else {
            return false;
        }
    }

    public boolean isTotalWin(FieldModel model){
        return isRowWin(model) || isColWin(model) || isColRowWin(model) || isRowColWin(model);
    }

    public boolean isRowPlusWin(FieldModel model){
        for(int i = 1; i < 5; i++){
            if(model.getXcord()+i < rownum) {
                if (boardModel[model.getXcord()+i][model.getYcord()].getState() != model.getState()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isRowMinusWin(FieldModel model){
        for(int i = 1; i < 5; i++){
            if(model.getXcord()-i >= 0) {
                if (boardModel[model.getXcord()-i][model.getYcord()].getState() != model.getState()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isColPlusWin(FieldModel model){
        for(int i = 1; i < 5; i++){
            if(model.getYcord()+i < colnum) {
                if (boardModel[model.getXcord()][model.getYcord()+i].getState() != model.getState()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isColMinusWin(FieldModel model){
        for(int i = 1; i < 5; i++){
            if(model.getYcord()-i >= 0) {
                if (boardModel[model.getXcord()][model.getYcord()-i].getState() != model.getState()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isRCPlusWin(FieldModel model){
        for(int i = 1; i < 5; i++){
            if(model.getYcord()+i < colnum && model.getXcord()+i < rownum) {
                if (boardModel[model.getXcord()+i][model.getYcord()+i].getState() != model.getState()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isRCMinusWin(FieldModel model){
        for(int i = 1; i < 5; i++){
            if(model.getYcord()-i >= 0 && model.getXcord()-i >= 0) {
                if (boardModel[model.getXcord()-i][model.getYcord()-i].getState() != model.getState()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isCRWin(FieldModel model){
        for(int i = 1; i < 5; i++){
            if(model.getYcord()+i < colnum && model.getXcord()-i >=0) {
                if (boardModel[model.getXcord()-i][model.getYcord()+i].getState() != model.getState()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isRCWin(FieldModel model){
        for(int i = 1; i < 5; i++){
            if(model.getYcord()-i >=0 && model.getXcord()+i < rownum) {
                if (boardModel[model.getXcord()+i][model.getYcord()-i].getState() != model.getState()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }



    public boolean move(FieldModel model, int player){
        if(model.getState() == 0){
            model.setState(player);
            return true;
        } else {
            return false;
        }
    }

    public FieldModel evaluateBoard(int player){

        FieldModel strongest = boardModel[0][0];

        for(int sor = 0; sor < rownum; sor++){
            for (int col = 0; col < colnum; col++){
                if(boardModel[sor][col].getState() == 0){

                    //INICIALIZÁLÓ RÉSZ

                    int osornum = 0;
                    int xsornum = 0;
                    int oynum = 0;
                    int xynum = 0;
                    int oznum = 0;
                    int xznum = 0;
                    int ojnum = 0;
                    int xjnum = 0;


                    long value = 0;

                    //INNEN INDUL A POZITÍV 4 ESEK


                    for(int i = 1; i < 5; i++){
                        if(col+i < colnum){
                            if (boardModel[sor][col+i].getState() == STATE_X)
                                xsornum++;
                            if(boardModel[sor][col + i].getState() == STATE_O)
                                osornum++;
                        }

                        if(sor+i < rownum){
                            if (boardModel[sor+i][col].getState() == STATE_X)
                                xynum++;
                            if(boardModel[sor+i][col].getState() == STATE_O)
                                oynum++;
                        }

                        if(sor+i < rownum && col + i < colnum){
                            if (boardModel[sor+i][col+i].getState() == STATE_X)
                                xznum++;
                            if(boardModel[sor+i][col + i].getState() == STATE_O)
                                oznum++;
                        }

                        if(sor+i < rownum && col - i >= 0){
                            if (boardModel[sor+i][col-i].getState() == STATE_X)
                                xjnum++;
                            if(boardModel[sor+i][col - i].getState() == STATE_O)
                                ojnum++;
                        }


                    }

                    if(player == STATE_X){
                        value += Config.AI_VALUES[osornum][xsornum];
                        value += Config.AI_VALUES[oynum][xynum];
                        value += Config.AI_VALUES[oznum][xznum];
                        value += Config.AI_VALUES[ojnum][xjnum];
                    } else {
                        value += Config.PLAYER_VALUES[osornum][xsornum];
                        value += Config.PLAYER_VALUES[oynum][xynum];
                        value += Config.PLAYER_VALUES[oznum][xznum];
                        value += Config.PLAYER_VALUES[ojnum][xjnum];
                    }

                     osornum = 0;
                     xsornum = 0;
                     oynum = 0;
                     xynum = 0;
                     oznum = 0;
                     xznum = 0;
                     ojnum = 0;
                     xjnum = 0;


                    //INNEN INDUL A 3 + 1 -

                    for(int i = 1; i < 4; i++){
                        if(col+i < colnum){
                            if (boardModel[sor][col+i].getState() == STATE_X)
                                xsornum++;
                            if(boardModel[sor][col + i].getState() == STATE_O)
                                osornum++;
                        }

                        if(sor+i < rownum){
                            if (boardModel[sor+i][col].getState() == STATE_X)
                                xynum++;
                            if(boardModel[sor+i][col].getState() == STATE_O)
                                oynum++;
                        }

                        if(sor+i < rownum && col + i < colnum){
                            if (boardModel[sor+i][col+i].getState() == STATE_X)
                                xznum++;
                            if(boardModel[sor+i][col + i].getState() == STATE_O)
                                oznum++;
                        }

                        if(sor+i < rownum && col - i >= 0){
                            if (boardModel[sor+i][col-i].getState() == STATE_X)
                                xjnum++;
                            if(boardModel[sor+i][col-i].getState() == STATE_O)
                                ojnum++;
                        }
                    }

                    if(col-1 >= 0){
                        if (boardModel[sor][col-1].getState() == STATE_X)
                            xsornum++;
                        if(boardModel[sor][col-1].getState() == STATE_O)
                            osornum++;
                    }

                    if(sor-1 >=0){
                        if (boardModel[sor-1][col].getState() == STATE_X)
                            xynum++;
                        if(boardModel[sor-1][col].getState() == STATE_O)
                            oynum++;
                    }

                    if(sor-1 >= 0 && col -1 >=0){
                        if (boardModel[sor-1][col-1].getState() == STATE_X)
                            xznum++;
                        if(boardModel[sor-1][col-1].getState() == STATE_O)
                            oznum++;
                    }

                    if(sor-1 >=0 && col + 1 < colnum){
                        if (boardModel[sor-1][col+1].getState() == STATE_X)
                            xjnum++;
                        if(boardModel[sor-1][col+1].getState() == STATE_O)
                            ojnum++;
                    }

                    if(player == STATE_X){
                        value += Config.AI_VALUES[osornum][xsornum];
                        value += Config.AI_VALUES[oynum][xynum];
                        value += Config.AI_VALUES[oznum][xznum];
                        value += Config.AI_VALUES[ojnum][xjnum];
                    } else {
                        value += Config.PLAYER_VALUES[osornum][xsornum];
                        value += Config.PLAYER_VALUES[oynum][xynum];
                        value += Config.PLAYER_VALUES[oznum][xznum];
                        value += Config.PLAYER_VALUES[ojnum][xjnum];
                    }

                    osornum = 0;
                    xsornum = 0;
                    oynum = 0;
                    xynum = 0;
                    oznum = 0;
                    xznum = 0;
                    ojnum = 0;
                    xjnum = 0;

                    //INNEN INDUL A 2 + 2 -

                    for(int i = 1; i < 3; i++){
                        if(col+i < colnum){
                            if (boardModel[sor][col+i].getState() == STATE_X)
                                xsornum++;
                            if(boardModel[sor][col + i].getState() == STATE_O)
                                osornum++;
                        }

                        if(col-i >= 0){
                            if (boardModel[sor][col-i].getState() == STATE_X)
                                xsornum++;
                            if(boardModel[sor][col-i].getState() == STATE_O)
                                osornum++;
                        }

                        if(sor+i < rownum){
                            if (boardModel[sor+i][col].getState() == STATE_X)
                                xynum++;
                            if(boardModel[sor+i][col].getState() == STATE_O)
                                oynum++;
                        }

                        if(sor-i >=0){
                            if (boardModel[sor-i][col].getState() == STATE_X)
                                xynum++;
                            if(boardModel[sor-i][col].getState() == STATE_O)
                                oynum++;
                        }

                        if(sor+i < rownum && col + i < colnum){
                            if (boardModel[sor+i][col+i].getState() == STATE_X)
                                xznum++;
                            if(boardModel[sor+i][col + i].getState() == STATE_O)
                                oznum++;
                        }

                        if(sor-i >= 0 && col -i >=0){
                            if (boardModel[sor-i][col-i].getState() == STATE_X)
                                xznum++;
                            if(boardModel[sor-i][col-i].getState() == STATE_O)
                                oznum++;
                        }

                        if(sor+i < rownum && col - i >= 0){
                            if (boardModel[sor+i][col-i].getState() == STATE_X)
                                xjnum++;
                            if(boardModel[sor+i][col-i].getState() == STATE_O)
                                ojnum++;
                        }

                        if(sor-i >=0 && col + i < colnum){
                            if (boardModel[sor-i][col+i].getState() == STATE_X)
                                xjnum++;
                            if(boardModel[sor-i][col+i].getState() == STATE_O)
                                ojnum++;
                        }
                    }

                    if(player == STATE_X){
                        value += Config.AI_VALUES[osornum][xsornum];
                        value += Config.AI_VALUES[oynum][xynum];
                        value += Config.AI_VALUES[oznum][xznum];
                        value += Config.AI_VALUES[ojnum][xjnum];
                    } else {
                        value += Config.PLAYER_VALUES[osornum][xsornum];
                        value += Config.PLAYER_VALUES[oynum][xynum];
                        value += Config.PLAYER_VALUES[oznum][xznum];
                        value += Config.PLAYER_VALUES[ojnum][xjnum];
                    }

                    osornum = 0;
                    xsornum = 0;
                    oynum = 0;
                    xynum = 0;
                    oznum = 0;
                    xznum = 0;
                    ojnum = 0;
                    xjnum = 0;

                    //INNEN INDUL A 1- 3 +

                    if(col+1 < colnum){
                        if (boardModel[sor][col+1].getState() == STATE_X)
                            xsornum++;
                        if(boardModel[sor][col+1].getState() == STATE_O)
                            osornum++;
                    }

                    if(sor+1 < rownum){
                        if (boardModel[sor+1][col].getState() == STATE_X)
                            xynum++;
                        if(boardModel[sor+1][col].getState() == STATE_O)
                            oynum++;
                    }

                    if(sor+1 < rownum && col + 1 < colnum){
                        if (boardModel[sor+1][col+1].getState() == STATE_X)
                            xznum++;
                        if(boardModel[sor+1][col+1].getState() == STATE_O)
                            oznum++;
                    }

                    if(sor+1 < rownum && col - 1 >= 0){
                        if (boardModel[sor+1][col-1].getState() == STATE_X)
                            xjnum++;
                        if(boardModel[sor+1][col-1].getState() == STATE_O)
                            ojnum++;
                    }

                    for(int i = 1; i < 4; i++){
                        if(col-i >= 0){
                            if (boardModel[sor][col-i].getState() == STATE_X)
                                xsornum++;
                            if(boardModel[sor][col-i].getState() == STATE_O)
                                osornum++;
                        }

                        if(sor-i >=0){
                            if (boardModel[sor-i][col].getState() == STATE_X)
                                xynum++;
                            if(boardModel[sor-i][col].getState() == STATE_O)
                                oynum++;
                        }

                        if(sor-i >= 0 && col -i >=0){
                            if (boardModel[sor-i][col-i].getState() == STATE_X)
                                xznum++;
                            if(boardModel[sor-i][col-i].getState() == STATE_O)
                                oznum++;
                        }

                        if(sor-i >=0 && col + i < colnum){
                            if (boardModel[sor-i][col+i].getState() == STATE_X)
                                xjnum++;
                            if(boardModel[sor-i][col+i].getState() == STATE_O)
                                ojnum++;
                        }
                    }

                    if(player == STATE_X){
                        value += Config.AI_VALUES[osornum][xsornum];
                        value += Config.AI_VALUES[oynum][xynum];
                        value += Config.AI_VALUES[oznum][xznum];
                        value += Config.AI_VALUES[ojnum][xjnum];
                    } else {
                        value += Config.PLAYER_VALUES[osornum][xsornum];
                        value += Config.PLAYER_VALUES[oynum][xynum];
                        value += Config.PLAYER_VALUES[oznum][xznum];
                        value += Config.PLAYER_VALUES[ojnum][xjnum];
                    }

                    osornum = 0;
                    xsornum = 0;
                    oynum = 0;
                    xynum = 0;
                    oznum = 0;
                    xznum = 0;
                    ojnum = 0;
                    xjnum = 0;

                    //INNEN INDUL a 4 -

                    for(int i = 1; i < 5; i++){
                        if(col-i >= 0){
                            if (boardModel[sor][col-i].getState() == STATE_X)
                                xsornum++;
                            if(boardModel[sor][col-i].getState() == STATE_O)
                                osornum++;
                        }

                        if(sor-i >=0){
                            if (boardModel[sor-i][col].getState() == STATE_X)
                                xynum++;
                            if(boardModel[sor-i][col].getState() == STATE_O)
                                oynum++;
                        }

                        if(sor-i >= 0 && col -i >=0){
                            if (boardModel[sor-i][col-i].getState() == STATE_X)
                                xznum++;
                            if(boardModel[sor-i][col-i].getState() == STATE_O)
                                oznum++;
                        }

                        if(sor-i >=0 && col + i < colnum){
                            if (boardModel[sor-i][col+i].getState() == STATE_X)
                                xjnum++;
                            if(boardModel[sor-i][col+i].getState() == STATE_O)
                                ojnum++;
                        }
                    }

                    if(player == STATE_X){
                        value += Config.AI_VALUES[osornum][xsornum];
                        value += Config.AI_VALUES[oynum][xynum];
                        value += Config.AI_VALUES[oznum][xznum];
                        value += Config.AI_VALUES[ojnum][xjnum];
                    } else {
                        value += Config.PLAYER_VALUES[osornum][xsornum];
                        value += Config.PLAYER_VALUES[oynum][xynum];
                        value += Config.PLAYER_VALUES[oznum][xznum];
                        value += Config.PLAYER_VALUES[ojnum][xjnum];
                    }

                    boardModel[sor][col].setScore(value);

                    if(strongest.getScore() < value){
                        strongest = boardModel[sor][col];
                    }

                }
            }
        }

        return strongest;
    }


    public int getPositionOf(FieldModel model){
        int temp = model.getXcord() * colnum;
        return temp + model.getYcord();
    }


    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public int getColnum() {
        return colnum;
    }

    public void setColnum(int colnum) {
        this.colnum = colnum;
    }

    public FieldModel[][] getBoardModel() {
        return boardModel;
    }

    public void setBoardModel(FieldModel[][] boardModel) {
        this.boardModel = boardModel;
    }

    public FieldModel getField(int position){
        int temp = position /colnum;
        int temp2 = position- (colnum*temp);
        return boardModel[temp][temp2];
    }

    public int getCount(){
        return colnum * rownum;
    }

}
