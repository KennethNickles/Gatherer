package com.github.kennethnickles.gatherer.demo

import android.content.Context
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.util.Preconditions

/**
 * @author kenneth.nickles
 * @since 2016-05-30.
 */
class CardAdapter(context: Context, cards: MutableList<Card>,
                  cardSelectionListener: CardSelectionListener) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private val mLayoutInflater: LayoutInflater
    private val mCards: MutableList<Card>
    private val mCardSelectionListener: CardSelectionListener

    init {
        mLayoutInflater = LayoutInflater.from(context)
        mCards = Preconditions.checkNotNull(cards, "Cards")
        mCardSelectionListener = Preconditions.checkNotNull(cardSelectionListener, "MatchCreationListener")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = mLayoutInflater.inflate(R.layout.view_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = mCards[position]
        holder.bind(card)
        holder.bindListener(mCardSelectionListener)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mCards.size
    }

    override fun onViewRecycled(holder: CardViewHolder?) {
        holder!!.unbind()
    }

    fun addCards(@NonNull cards: List<Card>) {
        mCards.clear()
        mCards.addAll(cards)
        notifyDataSetChanged()
    }

    class CardViewHolder(formatView: View) : RecyclerView.ViewHolder(formatView), Bindable<Card> {

        @BindView(R.id.view_card)
        lateinit var mViewCard: ImageView

        private var mCardSelectionListener: CardSelectionListener? = null
        private var mCard: Card? = null

        init {
            ButterKnife.bind(this, formatView)
        }

        override fun bind(card: Card) {
            mCard = Preconditions.checkNotNull(card, "Card")
            Glide.with(mViewCard!!.context).load(mCard!!.url).asBitmap().diskCacheStrategy(
                    DiskCacheStrategy.SOURCE).placeholder(R.drawable.card_back_180dp).into(mViewCard)
        }

        override fun unbind() {
            this.mCardSelectionListener = null
        }

        fun bindListener(cardSelectionListener: CardSelectionListener) {
            mCardSelectionListener = Preconditions.checkNotNull(cardSelectionListener, "MatchCreationListener")
        }

        @OnClick(R.id.view_card)
        internal fun onClick() {
            Preconditions.checkState(mCard != null, "Card never bound, launch failed")
            mCardSelectionListener!!.onCardSelected(mCard!!)
        }
    }
}
