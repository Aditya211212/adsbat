package com.sunsetgames.copcarscan

import ai.meson.ads.MesonAdData
import ai.meson.ads.MesonAdRequestStatus
import ai.meson.ads.MesonInterstitial
import ai.meson.ads.listeners.MesonInterstitialAdListener
import ai.meson.common.sdk.BaseMesonInit
import ai.meson.sdk.MesonSdk
import ai.meson.sdk.MesonSdkConfiguration
import ai.meson.sdk.MesonSdkInitializationListener
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sunsetgames.copcarscan.ui.theme.TestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MesonSdk.setLogLevel(BaseMesonInit.LogLevel.DEBUG)
        //Set your configuration with your application context and APP ID.
        val mesonSdkConfiguration = MesonSdkConfiguration.Builder(this, "e4d739e9-971d-434a-abe6-bae5dfbc349a").build()
//Init SDK
        MesonSdk.initialize(mesonSdkConfiguration, object : MesonSdkInitializationListener {
            override fun onComplete(error: Error?) {
                if(error == null) {//use load() to make your interstitial ad request
                    val interstitialAd = MesonInterstitial(this@MainActivity, "b1fa9dee-9830-4b66-b19b-151568192cd3")
                    interstitialAd.load()
                    if(MesonSdk.isSDKInitialized()) {

                        interstitialAd.setAdListener(object : MesonInterstitialAdListener() {
                            override fun onAdImpression(ad: MesonInterstitial, mesonAdData: MesonAdData?) {
                            }

                            override fun onAdDisplayed(ad: MesonInterstitial) {
                            }

                            override fun onAdDisplayFailed(ad: MesonInterstitial) {
                            }

                            override fun onAdDismissed(ad: MesonInterstitial) {
                            }

                            override fun onUserLeftApplication(ad: MesonInterstitial) {
                            }

                            override fun onAdLoadSucceeded(ad: MesonInterstitial) {
                                interstitialAd.show()
                            }

                            override fun onAdLoadFailed(ad: MesonInterstitial, status: MesonAdRequestStatus) {
                            }

                            override fun onAdClicked(ad: MesonInterstitial, params: HashMap<String, Any>) {
                            }
                        })
                    }

                    //Init Success, Ad Integrations may start here.
                } else {
                    //Init Failed, Please check the Error Message.
                }
            }
        })
        setContent {
            TestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Hello Meson")
                }
            }
        }
    }
}

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
    TestAppTheme {
        Greeting("Hello Meson")
    }
}