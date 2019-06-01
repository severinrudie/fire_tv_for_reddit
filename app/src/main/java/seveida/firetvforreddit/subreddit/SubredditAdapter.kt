/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package seveida.firetvforreddit.subreddit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import seveida.firetvforreddit.R
import baron.severin.domain_objects.ThreadMetadata
import kotlinx.android.synthetic.main.thread_item.view.*
import javax.inject.Inject

class SubredditAdapter @Inject constructor() : RecyclerView.Adapter<SubredditViewHolder>() {

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
            Picasso.get().load(thread.previewImageUrl).into(threadImageView)
            threadTitle.text = thread.title
            threadAuthor.text = "u/${thread.op.username}" // TODO move this to the data layer
            threadVoteCount.text = (thread.voteCount.up - thread.voteCount.down).toString() // TODO move this to the data layer
            threadCommentCount.text = "${thread.commentCount} comments" // TODO move this to the data layer
        }
    }
}

class SubredditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
