package hu.penzestamas.gomoku.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by penzes.tamas on 2016.10.19..
 */

public class FieldModel implements Parcelable {

    private int state;

    private int xcord;

    private int ycord;


    private long score;

    public FieldModel(int state, int xcord, int ycord, long score) {
        this.state = state;
        this.xcord = xcord;
        this.ycord = ycord;
        this.score = score;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getXcord() {
        return xcord;
    }

    public void setXcord(int xcord) {
        this.xcord = xcord;
    }

    public int getYcord() {
        return ycord;
    }

    public void setYcord(int ycord) {
        this.ycord = ycord;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.state);
        dest.writeInt(this.xcord);
        dest.writeInt(this.ycord);
        dest.writeLong(this.score);
    }

    protected FieldModel(Parcel in) {
        this.state = in.readInt();
        this.xcord = in.readInt();
        this.ycord = in.readInt();
        this.score = in.readLong();
    }

    public static final Creator<FieldModel> CREATOR = new Creator<FieldModel>() {
        @Override
        public FieldModel createFromParcel(Parcel source) {
            return new FieldModel(source);
        }

        @Override
        public FieldModel[] newArray(int size) {
            return new FieldModel[size];
        }
    };

    @Override
    public String toString() {
        if(state == Board.STATE_O){
            return "O";
        } else if(state == Board.STATE_X){
            return "X";
        } else return "1";
    }
}
