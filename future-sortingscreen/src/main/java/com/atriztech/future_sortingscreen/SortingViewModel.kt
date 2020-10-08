package com.atriztech.future_sortingscreen

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel


class SortingViewModel : ViewModel() {
    var language = ObservableField<String>()
    var location = ObservableField<String>()

    var listLanguage = listOf<String>("", "ruby", "java", "python", "kotlin")
    var listLocation = listOf<String>("", "Moskow", "Hamburg")

}