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
import com.deb452.filterlistlib.model_classes.SelectedListModel;

import java.util.List;

public class SelectedListAdapter extends RecyclerView.Adapter<SelectedListAdapter.SelectedListVH> {

    private Context context;
    private List<ItemListModel> selectedListModelList;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;

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
        holder.selectedItemTV.setText(selectedListModel.getNameList());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewOnClickListener.onClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectedListModelList == null ? 0 : selectedListModelList.size();
    }

    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener recyclerViewOnClickListener) {
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    class SelectedListVH extends RecyclerView.ViewHolder {
        public AppCompatTextView selectedItemTV;

        public SelectedListVH(@NonNull View itemView) {
            super(itemView);
            selectedItemTV = itemView.findViewById(R.id.selectedItemName);
        }
    }
}
