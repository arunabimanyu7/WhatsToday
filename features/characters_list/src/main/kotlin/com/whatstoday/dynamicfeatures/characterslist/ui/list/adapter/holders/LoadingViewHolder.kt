 

package com.whatstoday.dynamicfeatures.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.whatstoday.commons.ui.base.BaseViewHolder
import com.whatstoday.dynamicfeatures.characterslist.databinding.ListItemLoadingBinding

/**
 * Class describes characters loading view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class LoadingViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemLoadingBinding>(
    binding = ListItemLoadingBinding.inflate(inflater)
)
