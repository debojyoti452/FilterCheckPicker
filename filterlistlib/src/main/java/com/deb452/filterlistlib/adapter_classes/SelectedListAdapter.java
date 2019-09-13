package com.deb452.filterlistlib.adapter_classes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deb452.filterlistlib.R;
import com.deb452.filterlistlib.customviews_classes.CircularTextView;
import com.deb452.filterlistlib.interface_classes.RecyclerViewOnClickListener;
import com.deb452.filterlistlib.model_classes.ItemListModel;

import java.util.List;

public class SelectedListAdapter extends RecyclerView.Adapter<SelectedListAdapter.SelectedListVH> {

    private Context context;
    private List<ItemListModel> selectedListModelList;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;
    private int limitSize;

    public SelectedListAdapter(Context context, List<ItemListModel> selectedListModelList) {
        this.context = context;
        this.selectedListModelList = selectedListModelList;
    }

    @NonNull
    @Override
    public SelectedListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_list_rv, parent, false);
        return new SelectedListVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SelectedListVH holder, int position) {
        ItemListModel selectedListModel = selectedListModelList.get(position);
        holder.selectedItemTV.setCustomText(selectedListModel.getNameList());
        holder.selectedItemTV.setSolidColor(position);
        holder.selectedItemTV.setTextColor(Color.WHITE);
        holder.selectedItemTV.setCustomTextSize(18);
    }

    @Override
    public int getItemCount() { return selectedListModelList == null ? 0 : selectedListModelList.size(); }

    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener recyclerViewOnClickListener) {
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    class SelectedListVH extends RecyclerView.ViewHolder {
        public CircularTextView selectedItemTV;

        public SelectedListVH(@NonNull View itemView) {
            super(itemView);
            selectedItemTV = itemView.findViewById(R.id.selectedItemName);

            itemView.setOnClickListener(view -> recyclerViewOnClickListener.OnClicked(getAdapterPosition()));
        }
    }
}
