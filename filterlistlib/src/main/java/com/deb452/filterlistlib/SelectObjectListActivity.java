package com.deb452.filterlistlib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deb452.filterlistlib.adapter_classes.ItemListAdapter;
import com.deb452.filterlistlib.adapter_classes.SelectedListAdapter;
import com.deb452.filterlistlib.customviews_classes.LineDividerDecoration;
import com.deb452.filterlistlib.model_classes.ItemListModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectObjectListActivity extends AppCompatActivity {

    private Context context = SelectObjectListActivity.this;
    public static final String RESULT_SELECTED_LIST_KEY = "result_selected_key";
    private ItemListAdapter itemListAdapter;
    private SelectedListAdapter selectedListAdapter;
    private List<ItemListModel> listModelList = new ArrayList<>();
    private List<ItemListModel> selectedListModelList = new ArrayList<>();
    private int limitSize;
    private Bundle bundle;

    //views
    private RecyclerView itemListRV, selectedListRV;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_object_list);
        init();
        bundle = getIntent().getExtras();
        if (bundle != null) {
            listModelList = (ArrayList<ItemListModel>) getIntent().getSerializableExtra("listModelKey");
            limitSize = bundle.getInt("sizeKey", 0);
            settingViews();
        } else {
            finish();
        }

    }

    private void settingViews() {
        itemListAdapter = new ItemListAdapter(context, listModelList);
        selectedListAdapter = new SelectedListAdapter(context, selectedListModelList);



        itemListAdapter.setRecyclerViewOnClickListener(position -> {
            if (selectedListModelList.size() < limitSize) {
                selectedListModelList.add(listModelList.get(position));
                selectedListRV.smoothScrollToPosition(position);
                selectedListAdapter.notifyDataSetChanged();
                finishButton.setVisibility(View.GONE);
            } else {
                System.out.println("Full Selected List");
                Toast.makeText(context, "You can select only " + limitSize + " items", Toast.LENGTH_SHORT).show();
                finishButton.setVisibility(View.VISIBLE);
            }
        });

        selectedListAdapter.setRecyclerViewOnClickListener(position -> {
            selectedListModelList.remove(position);
            selectedListAdapter.notifyDataSetChanged();
            finishButton.setVisibility(View.GONE);
        });

        finishButton.setOnClickListener(view -> finishPicking(selectedListModelList));

        selectedListRV.setAdapter(selectedListAdapter);
        itemListRV.setAdapter(itemListAdapter);
    }

    private void finishPicking(List<ItemListModel> selectedListModelList) {
        Intent result = new Intent();
        result.putExtra(RESULT_SELECTED_LIST_KEY, (Serializable) selectedListModelList);
        setResult(RESULT_OK, result);
        finish();
    }

    private void init() {
        itemListRV = findViewById(R.id.itemListRV);
        selectedListRV = findViewById(R.id.selectedListRV);
        finishButton = findViewById(R.id.finishButton);

        itemListRV.setLayoutManager(new LinearLayoutManager(context));
        itemListRV.addItemDecoration(new LineDividerDecoration(getDrawable(R.drawable.divider)));

        //selected List
        LinearLayoutManager selectedLayoutManager = new LinearLayoutManager(context);
        selectedLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        selectedLayoutManager.setReverseLayout(true);
        selectedLayoutManager.setStackFromEnd(true);
        selectedListRV.setLayoutManager(selectedLayoutManager);
    }

}
