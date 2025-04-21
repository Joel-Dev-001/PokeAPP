package com.jotadev.pokeapp.ui.presentation.screens.settings

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jotadev.pokeapp.ui.presentation.MainActivity
import com.jotadev.pokeapp.ui.presentation.screens.components.TopAppBar
import com.jotadev.pokeapp.ui.presentation.theme.orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDarkModeEnabled: Boolean,
    onDarkModeToggle: (Boolean) -> Unit,
    onLogout: () -> Unit
) {
    var showDialog = remember { mutableStateOf(false) } // Estado para mostrar diálogo
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = "CONFIGURACIÓN")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Modo Oscuro",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Switch(
                    checked = isDarkModeEnabled,
                    onCheckedChange = { onDarkModeToggle(it) } // Cambia el estado del tema
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        Toast.makeText(context, "Disponible muy pronto", Toast.LENGTH_SHORT).show()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Idioma",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Button(
                onClick = { showDialog.value = true }, // Muestra el cuadro de diálogo al hacer clic
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orange)
            ) {
                Text(
                    text = "Cerrar Sesión",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
            if (showDialog.value) {
                AlertDialog(
                    modifier = Modifier.fillMaxWidth(),
                    onDismissRequest = { showDialog.value = false },
                    title = {
                        Text(text = "¿Seguro que desea cerrar la sesión?", fontSize = 16.sp)
                    },
                    confirmButton = {
                        Text(
                            text = "ACEPTAR",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = orange,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .clickable {
                                    onLogout()
                                    (context as MainActivity).finish()
                                    showDialog.value = false
                                },
                            textAlign = TextAlign.End
                        )

                    },
                    dismissButton = {
                        Text(
                            text = "CANCELAR",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.Gray,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .clickable {
                                    showDialog.value = false
                                },
                            textAlign = TextAlign.End
                        )

                    },
                    shape = RoundedCornerShape(10.dp),
                )
            }
        }
    }
}