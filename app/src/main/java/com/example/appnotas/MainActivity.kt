package com.example.appnotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appnotas.ui.theme.AppNotasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNotasTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppNotas()
                }

                }
            }
        }
    }

@Composable
fun AppNotas() {
    var notas by remember { mutableStateOf(listOf<Nota>())}
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Titulo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripcion") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (titulo.isNotEmpty() && descripcion.isNotEmpty()) {
                    notas = notas + Nota(
                        id = notas.size + 1,
                        titulo = titulo,
                        descripcion = descripcion
                    )
                }
            
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Nueva Nota")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(notas) { nota ->
                NotaItem(nota = nota, onDelete = {
                    notas = notas.filter { it.id != note.id }
                })
            }
        }

    }

}

@Composable
fun NotaItem(nota: Nota, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

        }
    }
}

@Preview(showBackground = true)
