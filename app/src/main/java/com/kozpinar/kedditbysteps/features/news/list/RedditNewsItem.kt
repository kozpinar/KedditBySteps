package com.kozpinar.kedditbysteps.features.news.list

import android.os.Parcel
import android.os.Parcelable
import com.kozpinar.kedditbysteps.commons.adapters.AdapterConstants
import com.kozpinar.kedditbysteps.commons.adapters.ViewType
import com.kozpinar.kedditbysteps.commons.extensions.createParcel

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */

data class RedditNews(
        val news: List<RedditNewsItem>,
        val after: String?,
        val before: String?
): Parcelable {
    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { RedditNews(it) }
    }

    protected constructor(parcelIn: Parcel) : this(
            mutableListOf<RedditNewsItem>().apply {
                parcelIn.readTypedList(this, RedditNewsItem.CREATOR)
            },
            parcelIn.readString(),
            parcelIn.readString()

    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(news)
        dest.writeString(after)
        dest.writeString(before)
    }

    override fun describeContents() = 0
}

data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
): ViewType, Parcelable {


    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { RedditNewsItem(it) }
    }

    protected constructor(parcelIn: Parcel) : this(
            parcelIn.readString(),
            parcelIn.readString(),
            parcelIn.readInt(),
            parcelIn.readLong(),
            parcelIn.readString(),
            parcelIn.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(author)
        dest.writeString(title)
        dest.writeInt(numComments)
        dest.writeLong(created)
        dest.writeString(thumbnail)
        dest.writeString(url)
    }

    override fun describeContents() = 0

    override fun getViewType(): Int = AdapterConstants.NEWS


}