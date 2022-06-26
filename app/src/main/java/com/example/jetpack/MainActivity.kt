package com.example.jetpack

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.ui.theme.JetPackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}
@Composable
fun OnboardingScreen(onContinueClicked:()-> Unit){

    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement =Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the basics CodeLab")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")

            }
        }
    }
}
@Composable
private fun Greetings(names: List<String> =List(1000){"$it"}) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {

        items( items=names ) {
            name-> Greeting(name = name)
        }
    }
}
@Composable
fun Greeting(name:String) {
    Card(backgroundColor = MaterialTheme.colors.primary, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)){
        CardContent(name)
    }
}
@Composable
private fun CardContent(name:String){
    var expanded by remember { mutableStateOf(false)}
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ) 
    ){
        Column(modifier = Modifier
            .weight(1f)
            .padding(12.dp)) {
            Text(text = "Hello")
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if(expanded){
                Text(text = ("Composem ipsum color sit lazy, "+"padding theme elit, sed do bouncy. ").repeat(4))
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    "Show More"
                } else {
                     "Show Less"
                }

            )
        }
    }
}
@Composable
private fun MyApp(){
    var shouldShowOnBoarding by remember { mutableStateOf(true) }
   if (shouldShowOnBoarding){
       OnboardingScreen(onContinueClicked = {shouldShowOnBoarding=false})
   }else{
       Greetings()
   }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    JetPackTheme {
         Greetings()
    }
}
@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun OnboardingPreview() {
    JetPackTheme() {
        OnboardingScreen(onContinueClicked = {})
    }
}