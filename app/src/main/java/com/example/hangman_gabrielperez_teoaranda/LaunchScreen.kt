import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hangman_gabrielperez_teoaranda.MainActivity
import com.example.hangman_gabrielperez_teoaranda.R


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreenContent(modifier = Modifier.fillMaxSize())
        }

        // Programar la transición a la actividad principal
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Finaliza la actividad SplashScreen
        }, 3000) // 3 segundos de duración
    }
}

@Composable
fun SplashScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.screen1),
            contentDescription = "Logo del juego",
            modifier = Modifier.size(100.dp)
        )

        // Texto de carga
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Cargando el juego...",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = ComposeColor.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreenContent() {
    SplashScreenContent()
}
