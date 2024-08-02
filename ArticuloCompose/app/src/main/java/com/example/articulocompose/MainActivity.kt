package com.example.articulocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.articulocompose.ui.theme.ArticuloComposeTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArticuloComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    ArticuloComposeApp()

                }
            }
        }
    }
}

@Composable
fun ArticuloComposeApp() {
    CartaArticulo(
        titulo = stringResource(R.string.titulo),
        descripcionCorta = stringResource(R.string.descripcionCorta),
        descripcionLarga = stringResource(R.string.descripcionLarga),
        imagenEncabezado = painterResource(R.drawable.imagenjetpackcompose)
    )
}



@Composable
private fun CartaArticulo(
    titulo: String,
    descripcionCorta: String,
    descripcionLarga: String,
    imagenEncabezado: Painter,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Image(painter = imagenEncabezado, contentDescription = null)
        Text(
            text = titulo,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            fontSize = 24.sp
        )
        Text(
            text = descripcionCorta,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = descripcionLarga,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
    }
}






@Preview(showBackground = true)
@Composable
fun ArticuloComposeAppPreview() {
    ArticuloComposeTheme {
        ArticuloComposeApp()
    }
}