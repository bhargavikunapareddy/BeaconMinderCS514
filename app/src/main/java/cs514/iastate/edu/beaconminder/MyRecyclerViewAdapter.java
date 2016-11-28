package cs514.iastate.edu.beaconminder;

import android.content.Context;
import android.content.Intent;
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

    private List<ItemData> sourceItem;
    private Context mContext;

    public MyRecyclerViewAdapter(Context context, List<ItemData> sourceItem) {
        this.sourceItem = sourceItem;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final ItemData item = sourceItem.get(position);
        holder.imageView1.setImageResource(item.getIconID());
        holder.imageView2.setImageResource(item.getIndicatorID());
        holder.textView.setText(item.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BeaconDetail.class);
                intent.putExtra(MainActivity.EXTRA_BEACON_ID, item.getBeaconID());
                intent.putExtra(MainActivity.EXTRA_BINDING, item.getBinding());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sourceItem.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;
        protected ImageView imageView1;
        protected ImageView imageView2;

        public CustomViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.imageView1 = (ImageView) view.findViewById(R.id.icon);
            this.imageView2 = (ImageView) view.findViewById(R.id.indicator);
        }
    }
}
