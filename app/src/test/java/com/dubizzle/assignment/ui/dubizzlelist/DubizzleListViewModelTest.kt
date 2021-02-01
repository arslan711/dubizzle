package com.dubizzle.assignment.ui.dubizzlelist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dubizzle.assignment.repository.DubizzleRepository
import com.dubizzle.assignment.repository.local.database.pojo.Dubizzle
import com.dubizzle.assignment.util.InstantExecutorExtension
import com.dubizzle.assignment.util.LiveDataTestUtil
import com.dubizzle.assignment.util.TestUtil
import com.dubizzle.assignment.viewmodel.ListViewModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

@ExtendWith(InstantExecutorExtension::class)
class DubizzleListViewModelTest {
    // system under test
    private var viewModel: ListViewModel? = null

    @Mock
    private val dubizzleRepository: DubizzleRepository? = null
    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = ListViewModel(Mockito.mock(Application::class.java))
    }


    @Test
    @Throws(Exception::class)
    suspend fun retrieveDubizzles_returnDubizzlesList() {
        // Arrange
        val returnedData = TestUtil.DUBIZZLE_LIST
        val liveDataTestUtil = LiveDataTestUtil<List<Dubizzle>>()
        val returnedValue = MutableLiveData<List<Dubizzle>>()
        returnedValue.setValue(returnedData)
        Mockito.`when`<Any>(dubizzleRepository!!.getDubizzlesLiveData()).thenReturn(returnedValue)

        // Act
        val observedData = liveDataTestUtil.getValue(viewModel!!.dubizzleLiveData)

        // Assert
        Assertions.assertEquals(returnedData, observedData)
    }



    @Throws(Exception::class)
    @Test
    suspend fun retrieveDubizzles_returnEmptyDubizzlesList() {
        // Arrange
        val returnedData: List<Dubizzle> = ArrayList()
        val liveDataTestUtil = LiveDataTestUtil<List<Dubizzle>>()
        val returnedValue = MutableLiveData<List<Dubizzle>>()
        returnedValue.setValue(returnedData)
        Mockito.`when`<Any>(dubizzleRepository!!.getDubizzlesLiveData()).thenReturn(returnedValue)

        // Act
        val observedData = liveDataTestUtil.getValue(viewModel!!.dubizzleLiveData)

        // Assert
        Assertions.assertEquals(returnedData, observedData)
    }
}