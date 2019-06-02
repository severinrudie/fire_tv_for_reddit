/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package seveida.firetvforreddit.subreddit

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import baron.severin.business_logic.dagger.INITIAL_STATE
import baron.severin.presentation_objects.Colors
import com.squareup.picasso.Picasso
import seveida.firetvforreddit.R
import baron.severin.presentation_objects.ThreadItemState
import kotlinx.android.synthetic.main.thread_item.view.*
import javax.inject.Inject
import javax.inject.Named

class SubredditAdapter @Inject constructor(
        @Named(INITIAL_STATE) var colors: Colors
) : RecyclerView.Adapter<SubredditViewHolder>() {

    private var items = emptyList<ThreadItemState>()

    fun setItems(items: List<ThreadItemState>) {
        this.items = items
        notifyDataSetChanged() // TODO diffutil
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
            Picasso.get().load(thread.imageUrl).into(threadImageView)
            threadTitle.text = thread.title
            threadAuthor.text = thread.author
            threadVoteCount.text = thread.voteCount
            threadCommentCount.text = thread.comments

            val imageWrapperBackground = StateListDrawable()
            imageWrapperBackground.addState(
                    intArrayOf(android.R.attr.state_focused), // Focused
                    ColorDrawable(colors.accent)
            )
            imageWrapperBackground.addState(
                    intArrayOf(android.R.attr.state_focused), // Unfocused
                    ColorDrawable(if (thread.viewed) colors.read else colors.unread)
            )
            threadImageWrapper.background = imageWrapperBackground

            listOf(threadUpvote, threadDownvote).forEach { view ->
                view.setOnFocusChangeListener { v, hasFocus ->
                    val color = when (hasFocus) {
                        true -> colors.accent
                        false -> colors.text
                    }
                    val iv = v as ImageView
                    iv.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                }
            }
            threadWrapper.setBackgroundColor(colors.unread)
            threadWrapper.setOnFocusChangeListener { v, hasFocus ->
                when (hasFocus) {
                    true -> v.setBackgroundColor(colors.accent)
                    false -> v.setBackgroundColor(colors.unread)
                }
            }
        }
    }
}

class SubredditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
