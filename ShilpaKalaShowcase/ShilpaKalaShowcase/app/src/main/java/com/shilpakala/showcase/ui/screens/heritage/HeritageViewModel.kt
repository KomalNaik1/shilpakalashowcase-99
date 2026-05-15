package com.shilpakala.showcase.ui.screens.heritage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shilpakala.showcase.data.local.dao.HeritageDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HeritageViewModel @Inject constructor(
    private val heritageDao: HeritageDao
) : ViewModel() {
    val heritageStories = heritageDao.getAllHeritageStories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
