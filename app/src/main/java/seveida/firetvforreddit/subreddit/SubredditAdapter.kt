/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package seveida.firetvforreddit.subreddit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.thread_item.view.threadImageView
import kotlinx.android.synthetic.main.thread_item.view.threadTitle
import kotlinx.android.synthetic.main.thread_item.view.threadVoteCount
import seveida.firetvforreddit.R
import baron.severin.domain_objects.ThreadMetadata

class SubredditAdapter : RecyclerView.Adapter<SubredditViewHolder>() {

    private var items = emptyList<ThreadMetadata>()

    fun setItems(items: List<ThreadMetadata>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubredditViewHolder {
        val view =  LayoutInflater.from(parent.context)
            .inflate(R.layout.thread_item, parent, false)
        return SubredditViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SubredditViewHolder, position: Int) {
        val thread = items[position]
        with (holder.itemView) {
            threadTitle.text = thread.title
            threadVoteCount.text = thread.voteCount.toString()
            Picasso.get().load(thread.previewImageUrl).into(threadImageView)
        }
    }
}

class SubredditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
