package fvtc.edu.fileiodemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActorAdapter extends RecyclerView.Adapter{
    private ArrayList<Actor> actorData;
    private View.OnClickListener onItemClickListener;
    public static final String TAG = "ActorAdapter";

    private Context parentContext;

    public class ActorViewHolder extends RecyclerView.ViewHolder{
        public TextView tvFirstName;
        public TextView tvLastName;
        public Button btnDelete;
        private View.OnClickListener onClickListener;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            //code involved with clicking an item in the list

            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }

        public TextView getTvFirstName()
        {
            return tvFirstName;
        }
        public TextView getTvLastName() { return tvLastName; }
        public Button getBtnDelete() { return btnDelete; }

    }

    public ActorAdapter(ArrayList<Actor> data, Context context)
    {
        actorData = data;
        Log.d(TAG, "ActorAdapter: " + data.size());
        parentContext = context;
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener){
        Log.d(TAG, "setOnItemClickListener: ");
        onItemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.complex_item_view, parent, false);
        return new ActorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + actorData.get(position));
        ActorViewHolder actorViewHolder = (ActorViewHolder) holder;
        actorViewHolder.getTvFirstName().setText(actorData.get(position).getFirstName());
        actorViewHolder.getTvLastName().setText(actorData.get(position).getLastName());
        actorViewHolder.getBtnDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: delete");
                deleteItem(position);
            }
        });
    }

    @Override
    public int getItemCount() { return actorData.size(); }

    private void deleteItem(int position){
        actorData.remove(position);
        notifyDataSetChanged();
    }
}
