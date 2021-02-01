package com.dubizzle.assignment.repository.remote

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dubizzle.assignment.R
import com.dubizzle.assignment.view.activities.HomeActivity
import com.jakewharton.espresso.OkHttp3IdlingResource
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.coroutines.delay
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

  @get:Rule
  val activityRule = ActivityTestRule(HomeActivity::class.java, true, false)

  private val mockWebServer = MockWebServer()

  @Before
  fun setup() {
    mockWebServer.start(8080)
    IdlingRegistry.getInstance().register(
        OkHttp3IdlingResource.create(
            "okhttp",
            OkHttpProvider.getOkHttpClient()
        )
    )
  }

  @Test
   fun testSuccessfulResponse() {
    mockWebServer.dispatcher = object : Dispatcher() {
      override fun dispatch(request: RecordedRequest): MockResponse {

        return MockResponse()
            .setResponseCode(200)
            .setBody(FileReader.readStringFromFile("success_response.json"))
      }
    }
    activityRule.launchActivity(null)


    onView(withId(R.id.dubizzle_recycler_view))
        .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

  }

  @Test
  fun testFailedResponse() {
    mockWebServer.dispatcher = object : Dispatcher() {
      override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
      }
    }

    activityRule.launchActivity(null)


    onView(withId(R.id.dubizzle_recycler_view))
        .check(matches(withEffectiveVisibility(Visibility.GONE)))

  }

  @After
  fun teardown() {
    mockWebServer.shutdown()
  }
}
