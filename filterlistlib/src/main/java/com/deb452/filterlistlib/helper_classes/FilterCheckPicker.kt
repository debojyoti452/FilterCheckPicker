package com.deb452.filterlistlib.helper_classes

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.fragment.app.Fragment

import com.deb452.filterlistlib.SelectObjectListActivity
import com.deb452.filterlistlib.model_classes.ItemListModel

import java.io.Serializable
import java.util.ArrayList

/**
 * Created by Debojyoti Singha on 11,September,2019.
 */
class FilterCheckPicker {

    class Builder : Serializable {
        @Transient
        protected var activity: Activity? = null
        @Transient
        protected var fragment: Fragment? = null
        @Transient
        protected var context: Context?= null
        protected var itemListModels: List<ItemListModel> = ArrayList()
        protected var limitOfSelections: Int = 0
        protected var isVisible: Boolean = false

        constructor(context: Context) {
            this.context = context
        }

        constructor(activity: Activity) {
            this.activity = activity
        }

        constructor(fragment: Fragment) {
            this.fragment = fragment
        }

        fun setLists(stringArrayList: List<ItemListModel>): Builder {
            this.itemListModels = stringArrayList
            return this
        }

        fun setLimitsOfSelections(limitOfSelections: Int): Builder {
            this.limitOfSelections = limitOfSelections
            return this
        }

        fun setShowFinishButton(isVisible: Boolean): Builder {
            this.isVisible = isVisible
            return this
        }

        fun GetPickerForResult(requestCode: Int) {
            if (activity != null) {
                val intent = Intent(activity, SelectObjectListActivity::class.java)
                intent.putExtra("listModelKey", itemListModels as Serializable)
                intent.putExtra("sizeKey", limitOfSelections)
                intent.putExtra("isVisibleKey", isVisible)
                activity!!.startActivityForResult(intent, requestCode)
            } else if (fragment != null) {
                if (fragment!!.activity != null) {
                    val intent = Intent(fragment!!.activity, SelectObjectListActivity::class.java)
                    intent.putExtra("listModelKey", itemListModels as Serializable)
                    intent.putExtra("sizeKey", limitOfSelections)
                    intent.putExtra("isVisibleKey", isVisible)
                    fragment!!.startActivityForResult(intent, requestCode)
                }
            } else {
                throw RuntimeException("Unable to find a context for intent. Is there a valid activity or fragment passed in the builder?")
            }
        }
    }
}
