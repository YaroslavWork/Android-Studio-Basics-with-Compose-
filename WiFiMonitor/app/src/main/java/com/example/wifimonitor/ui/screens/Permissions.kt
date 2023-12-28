package com.example.wifimonitor.ui.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import com.example.wifimonitor.R
import com.example.wifimonitor.ui.navigation.NavigationDestination
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState


object PermissionsDestination : NavigationDestination {
    override val route: String = "permission"
    override val titleRes: Int = R.string.app_name
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermission(
    permission: String,
    deniedMessage: String = stringResource(R.string.permission_denied),
    rationaleMessage: String = stringResource(R.string.permission_rational),
    navigateToHomeList: () -> Unit = {},
    checkPermission: Boolean,
    checkPermissionAction: () -> Unit = {},
) {
   val permissionState = rememberPermissionState(permission)

    if (checkPermission){
        (LocalContext.current as? Activity)?.finish()
    }

    HandleRequest(
        permissionState = permissionState,
        deniedContent = { shouldShowRationale ->
            PermissionDeniedContent(
                deniedMessage = deniedMessage,
                rationaleMessage = rationaleMessage,
                shouldShowRationale = shouldShowRationale,
                onRequestPermission = {
                    permissionState.launchPermissionRequest()
                }
            )
        },
        content = {
            checkPermissionAction()
            navigateToHomeList()
        }
    )
}

@ExperimentalPermissionsApi
@Composable
private fun HandleRequest(
    permissionState: PermissionState,
    deniedContent: @Composable (Boolean) -> Unit,
    content: () -> Unit = {}
) {
    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            //(LocalContext.current as? Activity)?.finish()
            content()
        }
        is PermissionStatus.Denied -> {
            deniedContent((permissionState.status as PermissionStatus.Denied).shouldShowRationale)
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun PermissionDeniedContent(
    deniedMessage: String,
    rationaleMessage: String,
    shouldShowRationale: Boolean,
    onRequestPermission: () -> Unit
) {
    if (shouldShowRationale) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    text = stringResource(R.string.permission_request),
                    style = MaterialTheme.typography.labelLarge
                )
            },
            text = {
                Text(rationaleMessage)
            },
            confirmButton = {
                Button(onClick = onRequestPermission) {
                    Text(
                        text = stringResource(R.string.give_permission),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        )
    } else {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    text = stringResource(R.string.permission_request),
                    style = MaterialTheme.typography.labelLarge
                )
            },
            text = {
                Text(deniedMessage)
            },
            confirmButton = {

            }
        )
    }

}