package com.sanjeevyadavit.magecart.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sanjeevyadavit.magecart.R
import com.sanjeevyadavit.magecart.common.components.StateContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        
        return ComposeView(requireContext()).apply { 
            setContent { 
                MaterialTheme {
                    Surface {
                        val state = viewModel.state.value
                        
                        StateContainer(state = state) {
                            Text(text = "Slider item = ${state.data?.sliders?.size ?: 0} :: No of Categories = ${state.data?.featuredCategories?.size ?: 0}")
                        }
                    }
                }
            }
        }
    }
}