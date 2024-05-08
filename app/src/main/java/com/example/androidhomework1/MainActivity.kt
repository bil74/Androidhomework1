package com.example.androidhomework1

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.core.widget.TextViewCompat
import com.example.androidhomework1.ui.theme.AndroidHomework1Theme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidHomework1Theme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val c = LocalContext.current
    val appName = getString(c, R.string.app_name)
    val nameList: Array<String> = c.resources.getStringArray(R.array.names_array)
    var searchStr by remember { mutableStateOf("") }
    var fltList by remember { mutableStateOf("") }
    fltList = filterDataStr(nameList, "")
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleRow("Search in list - $appName")
            SearchField(str = searchStr, onTextChange = { searchStr = getSearchStr(it)})
            SearchButton(onButtonClicked = { fltList = filterDataStr(nameList, searchStr) })
            ResultField(fltList)
        }
    }

}

fun filterDataStr(lst: Array<String>, strFilt: String ): String{
    var outStr: String = ""
    for(s in lst){
        if(s.contains(strFilt, true)){
            outStr += "$s\n"
        }
    }
    return outStr
}

fun getSearchStr(str: String): String{
    Log.d("MyApp", str)
    return str
}

@Composable
fun TitleRow(str: String){
    Text(str)
}

@Composable
fun SearchField(str: String, onTextChange: (String) -> Unit){
    OutlinedTextField(
        value = str,
        onValueChange = onTextChange,
        label = { Text("search text") }
    )
}

@Composable
fun SearchButton(
    onButtonClicked: () -> Unit
){
    OutlinedButton(onClick = onButtonClicked) {
        Text("Search")
    }
}

@Composable
fun ResultField(str: String){
    val state = rememberScrollState(0)
    Text(str, textAlign = TextAlign.Left,  modifier = Modifier.verticalScroll(state, true))
}



@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun MyAppPreview() {
    AndroidHomework1Theme {
        MyApp()
    }
}

//default stuff
//-------------------------------------------------------------------
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidHomework1Theme {
        Greeting("Android")
        //MyApp()
    }
}
*/
