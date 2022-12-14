package lazycoder21.droid.github

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import lazycoder21.droid.pull_requests.presentation.pr.PullRequestActivity.Companion.navigateToPullRequestActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()
        navigateToPullRequest()
    }

    private fun navigateToPullRequest() {
        lifecycleScope.launch {
            delay(SPLASH_DELAY)
            navigateToPullRequestActivity()
            finish()
        }
    }

    private companion object {
        const val SPLASH_DELAY = 1000L
    }
}