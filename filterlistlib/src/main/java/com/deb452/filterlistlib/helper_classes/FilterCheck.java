package com.deb452.filterlistlib.helper_classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.deb452.filterlistlib.SelectObjectListActivity;
import com.deb452.filterlistlib.model_classes.ItemListModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Debojyoti Singha on 11,September,2019.
 */
public class FilterCheck {

    public static class Builder implements Serializable {
        protected transient Activity activity;
        protected transient Fragment fragment;
        protected transient Context context;
        protected List<ItemListModel> itemListModels = new ArrayList<>();
        protected int limitOfSelections;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        public Builder(@NonNull Activity activity) {
            this.activity = activity;
        }

        public Builder(@NonNull Fragment fragment) {
            this.fragment = fragment;
        }

        public Builder setLists(List<ItemListModel> stringArrayList) {
            this.itemListModels = stringArrayList;
            return this;
        }

        public Builder setLimitsOfSelections(int limitOfSelections) {
            this.limitOfSelections = limitOfSelections;
            return this;
        }

        public void GetPickerForResult(int requestCode) {
            if (activity != null) {
                Intent intent = new Intent(activity, SelectObjectListActivity.class);
                intent.putExtra("listModelKey", (Serializable) itemListModels);
                intent.putExtra("sizeKey", limitOfSelections);
                activity.startActivityForResult(intent, requestCode);
            } else if (fragment != null) {
                if (fragment.getActivity() != null) {
                    Intent intent = new Intent(fragment.getActivity(), SelectObjectListActivity.class);
                    intent.putExtra("listModelKey", (Serializable) itemListModels);
                    intent.putExtra("sizeKey", limitOfSelections);
                    fragment.startActivityForResult(intent, requestCode);
                }
            } else {
                throw new RuntimeException("Unable to find a context for intent. Is there a valid activity or fragment passed in the builder?");
            }
        }
    }
}
