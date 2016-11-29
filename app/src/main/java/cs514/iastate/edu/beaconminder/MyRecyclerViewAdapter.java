package cs514.iastate.edu.beaconminder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yi on 11/27/16.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder>{

    private final List<ItemData> sourceItem;
    private final OnItemClickListener listener;

    public MyRecyclerViewAdapter(List<ItemData> sourceItem, OnItemClickListener listener) {
        this.sourceItem = sourceItem;
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(ItemData itemData);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.bind(sourceItem.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return sourceItem.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private View view;
        protected TextView textView;
        protected ImageView imageView1;
        protected ImageView imageView2;

        public CustomViewHolder(View view) {
            super(view);
            this.view = view;
            this.textView = (TextView) view.findViewById(R.id.title);
            this.imageView1 = (ImageView) view.findViewById(R.id.icon);
            this.imageView2 = (ImageView) view.findViewById(R.id.indicator);
        }

        public void bind(final ItemData itemData, final OnItemClickListener listener) {
            textView.setText(itemData.getTitle());
            imageView1.setImageResource(itemData.getIconID());
            imageView2.setImageResource(itemData.getIndicatorID());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(itemData);
                }
            });
        }
    }
}
