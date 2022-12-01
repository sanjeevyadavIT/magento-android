package com.sanjeevyadavit.magecart.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.sanjeevyadavit.magecart.common.components.StateContainer
import com.sanjeevyadavit.magecart.presentation.MainActivityViewModel
import com.sanjeevyadavit.magecart.presentation.home.components.Home
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    
    private val viewModel: HomeViewModel by viewModels()
    // TODO:@SANJEEV remove this and store config in preference
    private val activityViewModel: MainActivityViewModel by activityViewModels<MainActivityViewModel>()

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
                           Home(it, activityViewModel.storeConfigs.value?.baseMediaUrl)
                        }
                    }
                }
            }
        }
    }
}