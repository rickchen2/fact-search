package com.blankmemo.factsearch.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.Color

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val mainViewModel by viewModels<MainViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MessageCard(Message("Android", "Jetpack Compose"))
    }

    fetchData()
//        _binding.imgRefresh.setOnClickListener {
//            fetchResponse()
//        }
  }

  data class Message(val author: String, val body: String)

  @Composable
  fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
      Image(
        painter = rememberImagePainter(
          data = "https://www.looper.com/img/gallery/ways-marvel-lied-to-you-about-iron-man/intro-1626473269.webp",
          builder = {
            transformations(CircleCropTransformation())
          }
        ),
        contentDescription = null,
        modifier = Modifier.size(40.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Column() {
        Text(text = msg.author, color = Color.White)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = msg.body, color = Color.White)
      }
    }


  }

  @Preview
  @Composable
  fun PreviewMessageCard() {
    MessageCard(
      msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
    )
  }


  private fun fetchData() {
    fetchResponse()
//    mainViewModel.response.observe(this) { response ->
//      when (response) {
//                is NetworkResult.Success -> {
//                    response.data?.let {
//                        _binding.imgDog.load(
//                            response.data.message
//                        ) {
//                            transformations(RoundedCornersTransformation(16f))
//                        }
//                    }
//                    _binding.pbDog.visibility = View.GONE
//                }
//
//                is NetworkResult.Error -> {
//                    _binding.pbDog.visibility = View.GONE
//                    Toast.makeText(
//                        this,
//                        response.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//                is NetworkResult.Loading -> {
//                    _binding.pbDog.visibility = View.VISIBLE
//                }
//      }
//    }
  }

  private fun fetchResponse() {
//    mainViewModel.fetchDogResponse()
//        _binding.pbDog.visibility = View.VISIBLE
  }
}
