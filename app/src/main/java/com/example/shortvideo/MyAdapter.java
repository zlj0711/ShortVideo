package com.example.shortvideo;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private OnItemClickListener onItemClickListener;
    private static final String TAG = "PerAdapter";
    private List<VideoResponse> myDataSet;
    public MyAdapter(OnItemClickListener listener) {
        onItemClickListener = listener;
    }
    public void setData(List<VideoResponse> mDataSet) {
        myDataSet = new ArrayList<>();
        Log.i(TAG, "in set data");
        myDataSet = mDataSet;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.glide_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(holder.image.getContext()).setDefaultRequestOptions(new RequestOptions()
                .frame(1000000).fitCenter().error(R.mipmap.fail).placeholder(R.mipmap.load))
                .load(myDataSet.get(position).url)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return myDataSet == null ? 0 : myDataSet.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick in PerAdapt");
            int clickedPosition = getLayoutPosition();
            Log.d(TAG, "clicked position:" +Integer.toString(clickedPosition));
            if (onItemClickListener!=null){
                Log.d(TAG, "in");
                onItemClickListener.onItemClick(clickedPosition);
            }
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int clickedItemIndex);
    }
}
