package com.sanjeevyadavit.magecart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sanjeevyadavit.magecart.viewmodel.CategoriesViewModel

class CategoriesFragment : Fragment() {

    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                CategoryTreeUi(viewModel)
            }
        }
    }
}

@Composable
fun CategoryTreeUi(viewModel: CategoriesViewModel) {

    Text(text = "Categories")

}