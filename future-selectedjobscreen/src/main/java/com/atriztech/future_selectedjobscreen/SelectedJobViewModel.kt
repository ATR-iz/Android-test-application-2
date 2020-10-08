package com.atriztech.future_selectedjobscreen

import android.os.Bundle
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class SelectedJobViewModel : ViewModel() {
    var company = ObservableField<String>()
    var company_url = ObservableField<String>()
    var location = ObservableField<String>()
    var title = ObservableField<String>()
    var description = ObservableField<String>()

    fun setDataFromBundle(bundle: Bundle){
        company.set(bundle.getString("company"))
        company_url.set(bundle.getString("company_url"))
        location.set(bundle.getString("location"))
        title.set(bundle.getString("title"))
        description.set(bundle.getString("description"))
    }
}