package com.android.weathergojek.matcher

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.util.HumanReadables
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.weathergojek.screen.notNull
import org.hamcrest.Matcher

/**
 * Created by Novyandi N. on 03/06/2019.
 * novyandinurahmad@hotmail.com
 */
class RecyclerViewInteraction private constructor(private val viewMatcher: Matcher<View>) {
    private var recyclerViewResource: RecyclerViewResource? = null

    fun withRecyclerViewResource(recyclerViewResource: RecyclerViewResource): RecyclerViewInteraction {
        this.recyclerViewResource = recyclerViewResource
        return this
    }

    fun check(itemViewAssertion: ItemViewAssertion): RecyclerViewInteraction {
        recyclerViewResource.notNull {
            for (index in 0 until it.itemCount() - 1) {
                onView(viewMatcher).perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                    .check { view, noViewFoundException ->
                        val viewHolder = findViewHolder(index, view)
                        it.withViewModel(index).notNull {
                            itemViewAssertion.check(it, viewHolder, viewHolder.itemView, noViewFoundException)
                        }
                    }
            }
        }
        return this
    }

    fun checkViewModel(viewModelAssertion: ViewModelAssertion): RecyclerViewInteraction {
        recyclerViewResource.notNull {
            for (index in 0 until it.itemCount() - 1) {
                onView(viewMatcher).perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                    .check { view, noViewFoundException ->
                        val viewHolder = findViewHolder(index, view)
                        it.withViewModel(index).notNull {
                            viewModelAssertion.check(it, viewHolder.itemView, noViewFoundException)
                        }
                    }
            }
        }
        return this
    }


    fun checkBinding(bindingAssertion: ViewItemAssertion): RecyclerViewInteraction {
        recyclerViewResource.notNull {
            for (index in 0 until it.itemCount() - 1) {
                onView(viewMatcher).perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                    .check { view, noViewFoundException ->
                        val viewHolder = findViewHolder(index, view)
                        bindingAssertion.check(viewHolder, view, noViewFoundException)
                    }
            }
        }
        return this
    }

    private fun findViewHolder(index: Int, view: View): RecyclerView.ViewHolder {
        val recyclerView = view as RecyclerView
        return recyclerView.findViewHolderForLayoutPosition(index)
            ?: throw PerformException.Builder()
                .withActionDescription(toString())
                .withViewDescription(HumanReadables.describe(view))
                .withCause(IllegalStateException("No view holder at index: " + index))
                .build()
    }

    interface ItemViewAssertion {
        fun check(item: Any, binding: Any, view: View, e: NoMatchingViewException?)
    }

    interface ViewModelAssertion {
        fun check(item: Any, view: View, e: NoMatchingViewException?)
    }

    interface ViewItemAssertion {
        fun check(binding: Any, view: View, e: NoMatchingViewException?)
    }

    interface RecyclerViewResource {
        fun withViewModel(index: Int): Any?
        fun itemCount(): Int
    }

    companion object {
        fun onRecyclerView(viewMatcher: Matcher<View>): RecyclerViewInteraction {
            return RecyclerViewInteraction(viewMatcher)
        }
    }
}
