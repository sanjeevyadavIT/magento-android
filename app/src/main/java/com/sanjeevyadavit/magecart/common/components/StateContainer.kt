package com.sanjeevyadavit.magecart.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sanjeevyadavit.magecart.common.IState

@Composable
fun <T> StateContainer(modifier: Modifier = Modifier, state: IState<T>, content: @Composable() (data: T) -> Unit) {
    Column(modifier = modifier) {
        if(state.isLoading) {
            CircularProgressIndicator()
        } else if(state.data != null) {
            content(state.data)
        }else {
            val errorMessage = if(state.error.isNotEmpty()) state.error else "Something went wrong!!!!"
            Text(text = errorMessage)
        }
    }
}