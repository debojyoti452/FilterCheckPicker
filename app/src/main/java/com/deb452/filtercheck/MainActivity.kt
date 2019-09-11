package com.deb452.filtercheck

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.deb452.filterlistlib.SelectObjectListActivity
import com.deb452.filterlistlib.model_classes.ItemListModel

class MainActivity : AppCompatActivity() {

    private var listItem: ArrayList<ItemListModel> = ArrayList()
    private var button: Button? = null
    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.buttonActivity)


        button!!.setOnClickListener {
            listItem = generateListItem()

            try {
                val intent = Intent().setClass(
                    context,
                    Class.forName("com.deb452.filterlistlib.SelectObjectListActivity")
                )
                intent.putExtra("listModelKey", listItem)
                startActivity(intent)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    private fun generateListItem(): ArrayList<ItemListModel> {
        val list: ArrayList<ItemListModel> = ArrayList()
        list.add(ItemListModel(1, "Sun"))
        list.add(ItemListModel(2, "Mercury"))
        list.add(ItemListModel(3, "Venus"))
        list.add(ItemListModel(4, "Earth"))
        list.add(ItemListModel(5, "Mars"))
        list.add(ItemListModel(6, "Jupiter"))
        list.add(ItemListModel(7, "Saturn"))
        list.add(ItemListModel(8, "Neptune"))
        list.add(ItemListModel(9, "Uranus"))
        list.add(ItemListModel(10, "Pluto"))
        return list
    }
}
