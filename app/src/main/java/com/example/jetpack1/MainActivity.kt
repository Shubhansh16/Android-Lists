package com.example.jetpack1

import SampleData
import android.content.res.Configuration
import android.os.Bundle
import android.os.Message
import android.provider.Telephony.Sms.Conversations
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


import com.example.jetpack1.ui.theme.Jetpack1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpack1Theme {
               Surface (
                   modifier = Modifier.padding(top = 30.dp)
               ){
                   Conversation(SampleData.conversationSample)
               }
            }
        }
    }
}

data class Message(val author: String, val body: String)


/*@Composable
fun SimpleRow(){
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Text(
            text = "Row Text 1",
            Modifier
                .background(Color.Blue)
                .padding(10.dp),

        )
        Text(text = "Row Text 2",
            Modifier
                .background(Color.Gray)
                .padding(10.dp))
        Text(text = "Row Text 2",
            Modifier
                .background(Color.Yellow)
                .padding(10.dp))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    )
    {
        Text(
            text = "Column 1",
            Modifier.background(Color.Yellow).padding(10.dp)
        )
        Text(
            text = "Column 2",
            Modifier.background(Color.Green).padding(10.dp)
        )
        Text(
            text = "Column 3",
            Modifier.background(Color.Magenta).padding(10.dp)
        )
    }
}

*/

/*@Preview(showBackground = true)
@Composable
fun SimpleRowPreview(){
 SimpleRow()
}
*/

/*@Composable
fun TextDesigns(){
      Text(
          text = "Hello",
          style = TextStyle(
              color = Color.Red,
              fontSize = 16.sp,
              fontStyle = FontStyle.Normal,
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.Bold,
              background = Color.Yellow,
              textAlign = TextAlign.Center,
              textMotion = TextMotion.Animated,
              textDecoration = TextDecoration.LineThrough
          ),
          modifier = Modifier
              .padding(10.dp)
      )
}


@Composable
@Preview(showBackground = true)
fun TextDesignPreview(){
    TextDesigns()
}
*/

@Composable
fun MessageCard(msg: com.example.jetpack1.Message){
    Row(
        modifier = Modifier
            .padding(all = 10.dp),

    ) {
        Image(
            painter = painterResource(R.drawable.whatsapp_image_2024_07_26_at_17_53_53_68b49d7f),
            contentDescription ="null",
            modifier = Modifier
                .size(40.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.width(8.dp))

        //
        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )

        Column (
            modifier = Modifier.clickable { isExpanded= !isExpanded }
        ){
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier
                        .padding(5.dp),
                    maxLines = if(isExpanded)Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}

@Composable
fun Conversation(messages: List<com.example.jetpack1.Message>){
    LazyColumn {
        items(messages) { message->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation(){
    Jetpack1Theme {
      Conversation(SampleData.conversationSample)
    }
}
/*
@Preview(name="Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark theme"
)
@Composable
fun MessCardPreview(){
    MessageCard()
}*/
