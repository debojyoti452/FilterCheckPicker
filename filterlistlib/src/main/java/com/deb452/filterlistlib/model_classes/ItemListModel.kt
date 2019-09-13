package com.deb452.filterlistlib.model_classes

import androidx.annotation.Keep

import java.io.Serializable

@Keep
class ItemListModel : Serializable {

    var nameList: String? = null

    constructor() {}

    constructor(nameList: String) {
        this.nameList = nameList
    }
}
