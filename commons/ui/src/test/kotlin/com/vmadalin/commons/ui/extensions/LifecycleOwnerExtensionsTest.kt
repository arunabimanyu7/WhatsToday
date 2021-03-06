

package com.whatstoday.commons.ui.extensions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.whatstoday.libraries.testutils.lifecycle.TestLifecycleOwner
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LifecycleOwnerExtensionsTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var lifecycleOwner: LifecycleOwner

    @Before
    fun setUp() {
        lifecycleOwner = TestLifecycleOwner()
    }

    @Test
    fun observingMutableLiveData_WhenPostStringValue_ShouldTrigger() {
        val mutableLiveData = MutableLiveData<String>()
        val observerPostValue = "Event Value"
        val observer = mockk<(String) -> Unit>(relaxed = true)
        val observerCaptor = slot<String>()

        lifecycleOwner.observe(mutableLiveData, observer)
        mutableLiveData.postValue(observerPostValue)

        verify { observer.invoke(capture(observerCaptor)) }
        assertEquals(observerPostValue, observerCaptor.captured)
    }

    @Test
    fun observingMutableLiveData_WhenPostMultipleIntValue_ShouldTriggerMultipleTimes() {
        val mutableLiveData = MutableLiveData<Int>()
        val observerPostValue = 3
        val observer = mockk<(Int) -> Unit>(relaxed = true)
        val observerCaptor = slot<Int>()

        lifecycleOwner.observe(mutableLiveData, observer)
        mutableLiveData.postValue(observerPostValue)
        mutableLiveData.postValue(observerPostValue)

        verify(exactly = 2) { observer.invoke(capture(observerCaptor)) }
        assertEquals(observerPostValue, observerCaptor.captured)

        mutableLiveData.postValue(observerPostValue)

        verify(exactly = 3) { observer.invoke(capture(observerCaptor)) }
        assertEquals(observerPostValue, observerCaptor.captured)
    }

    @Test
    fun observingMutableLiveDat_WhenPostNullValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val observer = mockk<(Int) -> Unit>()

        lifecycleOwner.observe(mutableLiveData, observer)
        mutableLiveData.postValue(null)

        verify(exactly = 0) { observer.invoke(any()) }
    }

    @Test
    fun observingMutableLiveData_WithoutPostValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val observer = mockk<(Int) -> Unit>()

        lifecycleOwner.observe(mutableLiveData, observer)

        verify(exactly = 0) { observer.invoke(any()) }
    }

    @Test
    fun observingLiveData_WhenPostStringValue_ShouldTrigger() {
        val mutableLiveData = MutableLiveData<String>()
        val liveData: LiveData<String> = mutableLiveData
        val observerPostValue = "Event Value"
        val observer = mockk<(String) -> Unit>(relaxed = true)
        val observerCaptor = slot<String>()

        lifecycleOwner.observe(liveData, observer)
        mutableLiveData.postValue(observerPostValue)

        verify { observer.invoke(capture(observerCaptor)) }
        assertEquals(observerPostValue, observerCaptor.captured)
    }

    @Test
    fun observingLiveData_WhenPostMultipleIntValue_ShouldTriggerMultipleTimes() {
        val mutableLiveData = MutableLiveData<Int>()
        val liveData: LiveData<Int> = mutableLiveData
        val observerPostValue = 3
        val observer = mockk<(Int) -> Unit>(relaxed = true)
        val observerCaptor = slot<Int>()

        lifecycleOwner.observe(liveData, observer)
        mutableLiveData.postValue(observerPostValue)
        mutableLiveData.postValue(observerPostValue)

        verify(exactly = 2) { observer.invoke(capture(observerCaptor)) }
        assertEquals(observerPostValue, observerCaptor.captured)

        mutableLiveData.postValue(observerPostValue)

        verify(exactly = 3) { observer.invoke(capture(observerCaptor)) }
        assertEquals(observerPostValue, observerCaptor.captured)
    }

    @Test
    fun observingLiveData_WhenPostNullValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val liveData: LiveData<Int> = mutableLiveData
        val observer = mockk<(Int) -> Unit>()

        lifecycleOwner.observe(liveData, observer)
        mutableLiveData.postValue(null)

        verify(exactly = 0) { observer.invoke(any()) }
    }

    @Test
    fun observingLiveData_WithoutPostValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val liveData: LiveData<Int> = mutableLiveData
        val observer = mockk<(Int) -> Unit>()

        lifecycleOwner.observe(liveData, observer)

        verify(exactly = 0) { observer.invoke(any()) }
    }
}
