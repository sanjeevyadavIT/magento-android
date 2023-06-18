package com.sanjeevyadavit.magecart.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sanjeevyadavit.magecart.R
import com.sanjeevyadavit.magecart.common.components.StateContainer
import com.sanjeevyadavit.magecart.common.model.IState
import com.sanjeevyadavit.magecart.domain.model.User

@Composable
fun Profile(state: IState<User>, onLogoutClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.DarkGray)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Profile photo",
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.White)
                        .border(1.dp, Color.DarkGray),
                )
                Text(text = "${stringResource(R.string.greetings_user)} ${state.data?.firstname ?: "You!"}")
            }

        }
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedButton(onClick = onLogoutClick) {
            Text(text = "Logout")
        }

    }

}