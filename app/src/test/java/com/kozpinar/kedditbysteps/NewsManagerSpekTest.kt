package com.kozpinar.kedditbysteps

import com.kozpinar.kedditbysteps.features.news.list.NewsManager
import com.kozpinar.kedditbysteps.features.news.list.RedditNews
import com.kozpinar.kedditbysteps.models.RedditChildrenResponse
import com.kozpinar.kedditbysteps.models.RedditDataResponse
import com.kozpinar.kedditbysteps.models.RedditNewsDataResponse
import com.kozpinar.kedditbysteps.models.RedditNewsResponse
import com.kozpinar.kedditbysteps.network.NewsAPI
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.jetbrains.spek.api.Spek
import retrofit2.Call
import retrofit2.Response
import rx.observers.TestSubscriber
import java.util.*

/**
 * Created by onur on 23.08.2017. KedditBySteps
 */

class NewsManagerSpekTest : Spek({

    given("a NewsManager") {
        var testSub = TestSubscriber<RedditNews>()
        var apiMock = mock<NewsAPI>()
        var callMock = mock<Call<RedditNewsResponse>>()

        beforeEach {
            testSub = TestSubscriber<RedditNews>()
            apiMock = mock<NewsAPI>()
            callMock = mock<Call<RedditNewsResponse>>()
            whenever(apiMock.getNews(any(), any())).thenReturn(callMock)
        }

        on("service returns something") {
            beforeEach {
                // prepare
                val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
                val response = Response.success(redditNewsResponse)

                whenever(callMock.execute()).thenReturn(response)

                // call
                val newsManager = NewsManager(apiMock)
                newsManager.getNews("").subscribe(testSub)
            }

            it("should receive something and no errors") {
                testSub.assertNoErrors()
                testSub.assertValueCount(1)
                testSub.assertCompleted()
            }
        }

        on("service returns just one news") {
            val newsData = RedditNewsDataResponse(
                    "author",
                    "title",
                    10,
                    Date().time,
                    "thumbnail",
                    "url"
            )
            beforeEach {
                // prepare
                val newsResponse = RedditChildrenResponse(newsData)
                val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(newsResponse), null, null))
                val response = Response.success(redditNewsResponse)

                whenever(callMock.execute()).thenReturn(response)

                // call
                val newsManager = NewsManager(apiMock)
                newsManager.getNews("").subscribe(testSub)
            }

            it("should process only one news successfully") {
                testSub.assertNoErrors()
                testSub.assertValueCount(1)
                testSub.assertCompleted()

                assert(testSub.onNextEvents[0].news[0].author == newsData.author)
                assert(testSub.onNextEvents[0].news[0].title == newsData.title)
            }
        }

        on("service returns a 500 error") {
            beforeEach {
                // prepare
                val responseError = Response.error<RedditNewsResponse>(500,
                        ResponseBody.create(MediaType.parse("application/json"), ""))

                whenever(callMock.execute()).thenReturn(responseError)

                // call
                val newsManager = NewsManager(apiMock)
                newsManager.getNews("").subscribe(testSub)
            }

            it("should receive an onError message") {
                assert(testSub.onErrorEvents.size == 1)
            }
        }
    }
})