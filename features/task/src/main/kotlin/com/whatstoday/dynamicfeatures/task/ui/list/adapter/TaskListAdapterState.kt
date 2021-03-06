 

package com.whatstoday.dynamicfeatures.task.ui.list.adapter

import com.whatstoday.commons.ui.base.BaseViewState

/**
 * Different states for [TaskListAdapter].
 *
 * @see BaseViewState
 */
sealed class TaskListAdapterState(
    val hasExtraRow: Boolean
) {

    /**
     * Listed the added characters into list.
     */
    object Added : TaskListAdapterState(hasExtraRow = true)

    /**
     * Loading for new characters to add into list.
     */
    object AddLoading : TaskListAdapterState(hasExtraRow = true)

    /**
     * Error on add new characters into list.
     */
    object AddError : TaskListAdapterState(hasExtraRow = true)

    /**
     * No more characters to add into list.
     */
    object NoMore : TaskListAdapterState(hasExtraRow = false)

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Added].
     *
     * @return True if is added state, otherwise false.
     */
    fun isAdded() = this is Added

    /**
     * Check if current view state is [AddLoading].
     *
     * @return True if is add loading state, otherwise false.
     */
    fun isAddLoading() = this is AddLoading

    /**
     * Check if current view state is [AddError].
     *
     * @return True if is add error state, otherwise false.
     */
    fun isAddError() = this is AddError

    /**
     * Check if current view state is [NoMore].
     *
     * @return True if is no more elements state, otherwise false.
     */
    fun isNoMore() = this is NoMore
}
