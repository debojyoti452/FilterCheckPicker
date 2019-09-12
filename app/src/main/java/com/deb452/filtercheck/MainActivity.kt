package com.deb452.filtercheck

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.deb452.filterlistlib.SelectObjectListActivity
import com.deb452.filterlistlib.customviews_classes.tag_layout.TagContainerLayout
import com.deb452.filterlistlib.helper_classes.FilterCheckPicker
import com.deb452.filterlistlib.model_classes.ItemListModel

class MainActivity : AppCompatActivity() {

    private var REQUEST_CODE = 5
    private var listItem: ArrayList<ItemListModel> = ArrayList()
    private var selectedList: ArrayList<ItemListModel> = ArrayList()
    private var stringList : ArrayList<String> = ArrayList()
    private var button: Button? = null
    private var tagView : TagContainerLayout?= null
    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.buttonActivity)
        tagView = findViewById(R.id.tagContainerLayout)

        button!!.setOnClickListener {
            listItem = generateListItem()
            FilterCheckPicker.Builder(this)
                .setLists(listItem)
                .setLimitsOfSelections(3)
                .GetPickerForResult(REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null && data.extras != null) {
            selectedList.clear()
            stringList.clear()
            selectedList = data.getSerializableExtra(SelectObjectListActivity.RESULT_SELECTED_LIST_KEY) as ArrayList<ItemListModel>

            if (selectedList.size != 0) {
                for (i in 0 until selectedList.size) {
                    Log.d("GotList", selectedList[i].getNameList())
                    stringList.addAll(listOf(selectedList[i].getNameList()))
                    tagView!!.tags = stringList
                }
            }

        } else {
            throw Exception("Not get any data..")
        }
    }

    private fun generateListItem(): ArrayList<ItemListModel> {
        val list: ArrayList<ItemListModel> = ArrayList()
        list.add(ItemListModel("Sun"))
        list.add(ItemListModel("Mercury"))
        list.add(ItemListModel("Venus"))
        list.add(ItemListModel("Earth"))
        list.add(ItemListModel("Mars"))
        list.add(ItemListModel("Jupiter"))
        list.add(ItemListModel("Saturn"))
        list.add(ItemListModel("Neptune"))
        list.add(ItemListModel("Uranus"))
        list.add(ItemListModel("Pluto"))
        return list
    }
}
