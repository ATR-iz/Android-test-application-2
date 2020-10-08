package com.atriztech.future_listjobsscreen

sealed class LoadState {
    object Loading : LoadState()
    object Loaded : LoadState()
    object Error : LoadState()
}