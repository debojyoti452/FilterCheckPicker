package com.deb452.filterlistlib

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.deb452.filterlistlib.adapter_classes.ItemListAdapter
import com.deb452.filterlistlib.adapter_classes.SelectedListAdapter
import com.deb452.filterlistlib.customviews_classes.LineDividerDecoration
import com.deb452.filterlistlib.model_classes.ItemListModel

import java.io.Serializable
import java.util.ArrayList

class SelectObjectListActivity : AppCompatActivity() {

    private val context = this@SelectObjectListActivity
    private var itemListAdapter: ItemListAdapter? = null
    private var selectedListAdapter: SelectedListAdapter? = null
    private var listModelList: List<ItemListModel> = ArrayList()
    private val selectedListModelList = ArrayList<ItemListModel>()
    private var limitSize: Int = 0
    private var isVisibles: Boolean = false
    private var bundle: Bundle? = null

    //views
    private var itemListRV: RecyclerView? = null
    private var selectedListRV: RecyclerView? = null
    private var finishButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_object_list)
        init()
        bundle = intent.extras
        if (bundle != null) {
            listModelList = intent.getSerializableExtra("listModelKey") as ArrayList<ItemListModel>
            limitSize = bundle!!.getInt("sizeKey", 3)
            isVisibles = bundle!!.getBoolean("isVisibleKey")
            settingViews()
        } else {
            finish()
        }

    }

    private fun settingViews() {

        if (isVisibles) {
            finishButton!!.visibility = View.VISIBLE
        } else {
            finishButton!!.visibility = View.GONE
        }

        itemListAdapter = ItemListAdapter(context, listModelList)
        selectedListAdapter = SelectedListAdapter(context, selectedListModelList)

        itemListAdapter!!.setRecyclerViewOnClickListener { position ->
            if (selectedListModelList.size < limitSize) {
                selectedListModelList.add(listModelList[position])
                selectedListRV!!.smoothScrollToPosition(position)
                selectedListAdapter!!.notifyDataSetChanged()
            } else {
                println("Full Selected List")
                Toast.makeText(
                    context,
                    "You can select only $limitSize items",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        selectedListAdapter!!.setRecyclerViewOnClickListener { position ->
            selectedListModelList.removeAt(position)
            selectedListAdapter!!.notifyDataSetChanged()
        }

        finishButton!!.setOnClickListener { view -> finishPicking(selectedListModelList) }

        selectedListRV!!.adapter = selectedListAdapter
        itemListRV!!.adapter = itemListAdapter
    }

    private fun finishPicking(selectedListModelList: List<ItemListModel>) {
        val result = Intent()
        result.putExtra(RESULT_SELECTED_LIST_KEY, selectedListModelList as Serializable)
        setResult(Activity.RESULT_OK, result)
        finish()
    }

    private fun init() {
        itemListRV = findViewById(R.id.itemListRV)
        selectedListRV = findViewById(R.id.selectedListRV)
        finishButton = findViewById(R.id.finishButton)

        itemListRV!!.layoutManager = LinearLayoutManager(context)
        itemListRV!!.addItemDecoration(LineDividerDecoration(getDrawable(R.drawable.divider)))

        //selected List
        val selectedLayoutManager = LinearLayoutManager(context)
        selectedLayoutManager.orientation = RecyclerView.HORIZONTAL
        selectedLayoutManager.reverseLayout = true
        selectedLayoutManager.stackFromEnd = true
        selectedListRV!!.layoutManager = selectedLayoutManager
    }

    companion object {
        val RESULT_SELECTED_LIST_KEY = "result_selected_key"
    }

}
