package com.deb452.filterlistlib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.deb452.filterlistlib.adapter_classes.ItemListAdapter;
import com.deb452.filterlistlib.adapter_classes.SelectedListAdapter;
import com.deb452.filterlistlib.customviews_classes.LineDividerDecoration;
import com.deb452.filterlistlib.interface_classes.RecyclerViewOnClickListener;
import com.deb452.filterlistlib.model_classes.ItemListModel;
import com.deb452.filterlistlib.model_classes.SelectedListModel;

import java.util.ArrayList;
import java.util.List;

public class SelectObjectListActivity extends AppCompatActivity {

    private Context context = SelectObjectListActivity.this;
    private ItemListAdapter itemListAdapter;
    private SelectedListAdapter selectedListAdapter;
    private List<ItemListModel> listModelList = new ArrayList<>();
    private List<ItemListModel> selectedListModelList = new ArrayList<>();
    private Bundle bundle;

    //views
    private RecyclerView itemListRV, selectedListRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_object_list);
        init();
        bundle = getIntent().getExtras();
        if (bundle != null) {
            listModelList = (ArrayList<ItemListModel>) getIntent().getSerializableExtra("listModelKey");
            settingViews();
        } else {
            finish();
        }

    }

    private void settingViews() {
        itemListAdapter = new ItemListAdapter(context, listModelList);
        selectedListAdapter = new SelectedListAdapter(context, selectedListModelList);
        
        itemListAdapter.setRecyclerViewOnClickListener(position -> {
            selectedListModelList.add(listModelList.get(position));
            selectedListAdapter.notifyItemChanged(position);
        });
        
        selectedListAdapter.setRecyclerViewOnClickListener(position -> {
            selectedListModelList.remove(position);
            selectedListAdapter.notifyItemChanged(position);
        });
        
        selectedListRV.setAdapter(selectedListAdapter);
        itemListRV.setAdapter(itemListAdapter);
    }

    private void init() {
        itemListRV = findViewById(R.id.itemListRV);
        selectedListRV = findViewById(R.id.selectedListRV);

        itemListRV.setLayoutManager(new LinearLayoutManager(context));
        itemListRV.addItemDecoration(new LineDividerDecoration(getDrawable(R.drawable.divider)));

        selectedListRV.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }
}
