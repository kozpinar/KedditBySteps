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
import org.junit.Before
import org.junit.Test
import rx.observers.TestSubscriber
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.test.assertEquals

/**
 * Created by onur on 23.08.2017. KedditBySteps
 */

class NewsManagerTest {
    var testSub = TestSubscriber<RedditNews>()
    var apiMock = mock<NewsAPI>()
    var callMock = mock<Call<RedditNewsResponse>>()

    @Before
    fun setup() {
        testSub = TestSubscriber<RedditNews>()
        apiMock = mock<NewsAPI>()
        callMock = mock<Call<RedditNewsResponse>>()

        whenever(apiMock.getNews(any(), any())).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic() {
        val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
        val response = Response.success(redditNewsResponse)
        whenever(callMock.execute()).thenReturn(response)

        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertCompleted()
    }


    @Test
    fun testSucces_checkOneNews(){
        val newsData = RedditNewsDataResponse(
                "author",
                "title",
                10,
                Date().time,
                "thumbnail",
                "url"
        )

        val newsRespnse = RedditChildrenResponse(newsData)
        val redditNewsResponse =
                RedditNewsResponse(RedditDataResponse(listOf(newsRespnse), null, null))
        val response = Response.success(redditNewsResponse)
        whenever(callMock.execute()).thenReturn(response)

        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertCompleted()

        assertEquals(newsData.author, testSub.onNextEvents[0].news[0].author)
        assertEquals(newsData.title, testSub.onNextEvents[0].news[0].title)
    }

    @Test
    fun testError() {
        val responseError = Response.error<RedditNewsResponse>(500,
                ResponseBody.create(MediaType.parse("application/json"), ""))

        whenever(callMock.execute()).thenReturn(responseError)

        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        assertEquals(1, testSub.onErrorEvents.size)
    }


}