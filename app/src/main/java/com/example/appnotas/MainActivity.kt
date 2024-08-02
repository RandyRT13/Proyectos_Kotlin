package com.example.appnotas

//noinspection UsingMaterialAndMaterial3Libraries
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appnotas.ui.theme.AppNotasTheme

data class Nota(val id: Int, var titulo: String, var descripcion: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNotasTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNotas()
                }

                }
            }
        }
    }

@Composable
fun AppNotas() {
    var notas by remember { mutableStateOf(listOf<Nota>())}
    var titulo by remember { mutableStateOf(TextFieldValue("")) }
    var descripcion by remember { mutableStateOf(TextFieldValue("")) }

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
                if (titulo.text.isNotEmpty() && descripcion.text.isNotEmpty()) {
                    notas = notas + Nota(notas.size, titulo.text, descripcion.text)
                    titulo = TextFieldValue("")
                    descripcion = TextFieldValue("")
                }
            
            },
        ) {
            Text("Nueva Nota")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(notas) { nota ->
                NotaItem(nota,
                    onEdit = {id: Int, newTitulo: String, newDescripcion: String ->
                        notas = notas.map {
                            if (it.id == id) it.copy(titulo = newTitulo, descripcion = newDescripcion) else it
                        }
                    },
                    onDelete = { id: Int ->
                        notas = notas.filter { it.id != id }
                    }
                )
            }
        }
    }
}


@Composable
fun NotaItem(
    nota: Nota,
    onEdit: (Int, String, String) -> Unit,
    onDelete: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = nota.titulo, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = nota.descripcion, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(onClick = {
                    val newTitulo = "Nuevo Titulo"
                    val newDescripcion = "Nueva Descripcion"
                    onEdit(nota.id, newTitulo, newDescripcion)
                }) {
                    Text("Editar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { onDelete(nota.id )}) {
                    Text("Borrar")
                    
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppNotas() {
    AppNotas()
}


