package com.deb452.filterlistlib.adapter_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.deb452.filterlistlib.R;
import com.deb452.filterlistlib.interface_classes.RecyclerViewOnClickListener;
import com.deb452.filterlistlib.model_classes.ItemListModel;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListVH> {

    private Context context;
    private List<ItemListModel> itemListModelList;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;

    public ItemListAdapter(Context context, List<ItemListModel> itemListModelList) {
        this.context = context;
        this.itemListModelList = itemListModelList;
    }

    @NonNull
    @Override
    public ItemListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_rv, parent, false);
        return new ItemListVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemListVH holder, int position) {
        ItemListModel itemListModel = itemListModelList.get(position);
        holder.itemNameTV.setText(itemListModel.getNameList());
    }

    @Override
    public int getItemCount() {
        return itemListModelList.size();
    }

    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener recyclerViewOnClickListener) {
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    class ItemListVH extends RecyclerView.ViewHolder {
        public AppCompatTextView itemNameTV;

        public ItemListVH(@NonNull View itemView) {
            super(itemView);
            itemNameTV = itemView.findViewById(R.id.itemNameTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewOnClickListener.onClicked(getAdapterPosition());
                }
            });
        }
    }
}
