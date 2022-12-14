package lazycoder21.droid.pull_requests.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import lazycoder21.droid.pull_requests.domain.model.Loading
import lazycoder21.droid.pull_requests.presentation.adapter.base.AbstractViewHolder
import lazycoder21.droid.pull_requests.presentation.adapter.base.BaseItemModel
import lazycoder21.droid.pull_requests.presentation.adapter.factory.ItemTypeFactory

class PullRequestRvAdapter(
    private val adapterTypeFactory: ItemTypeFactory,
) : ListAdapter<BaseItemModel, AbstractViewHolder<BaseItemModel>>(PullRequestAdapterDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AbstractViewHolder<BaseItemModel> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return adapterTypeFactory.createViewHolder(
            layoutInflater, parent, viewType
        ) as AbstractViewHolder<BaseItemModel>
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<BaseItemModel>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type(adapterTypeFactory)
    }

    fun addLoadingState() {
        val newList = currentList.toMutableList().also {
            it.add(Loading)
        }
        submitList(newList)
    }
}