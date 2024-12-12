import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.hangman_gabrielperez_teoaranda.Routes
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            SplashScreenContent(navController = navController, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun SplashScreenContent(navController: NavController, modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Routes.Menu.route) {
            popUpTo(Routes.Splash.route) { inclusive = true }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.hangman6),
            contentDescription = "Logo del juego",
            modifier = Modifier.size(100.dp)
        )

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
    SplashScreenContent(navController = rememberNavController())
}
