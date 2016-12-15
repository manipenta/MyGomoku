package hu.penzestamas.gomoku.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.penzestamas.gomoku.R;
import hu.penzestamas.gomoku.models.Board;
import hu.penzestamas.gomoku.models.FieldModel;
import hu.penzestamas.gomoku.views.FieldView;

import static android.R.id.list;

/**
 * Created by penzes.tamas on 2016.10.18..
 */

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private Context mContext;
    private int mRowResource;
    private Board mBoard;
    private FieldClickedListener mListener;

    public BoardAdapter(Context mContext, int mRowResource,Board board,FieldClickedListener listener) {
        this.mContext = mContext;
        this.mRowResource = mRowResource;
        this.mBoard = board;
        this.mListener = listener;
    }

    public BoardAdapter(Context mContext, int mRowResource,FieldClickedListener listener) {
        this.mContext = mContext;
        this.mRowResource = mRowResource;
        this.mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mRowResource, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mModel = mBoard.getField(position);
        holder.mView.animateState(holder.mModel.getState());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFielClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBoard.getCount();
    }

    public interface FieldClickedListener {
        void onFielClicked(int position);
    }

    public void updateBoardItem(int position){
        notifyItemChanged(position);
    }

    public void setItemState(int state, int position){

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private FieldView mView;
        private FieldModel mModel;

       public ViewHolder(View itemView) {
           super(itemView);
           mView = (FieldView) itemView.findViewById(R.id.item_field);

       }
   }
}
