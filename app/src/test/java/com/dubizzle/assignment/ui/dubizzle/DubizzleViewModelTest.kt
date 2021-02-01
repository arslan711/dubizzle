package com.dubizzle.assignment.ui.dubizzle

import androidx.test.core.app.ApplicationProvider
import com.dubizzle.assignment.repository.local.database.pojo.Dubizzle
import com.dubizzle.assignment.util.InstantExecutorExtension
import com.dubizzle.assignment.util.LiveDataTestUtil
import com.dubizzle.assignment.viewmodel.DetailViewModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.MockitoAnnotations

@ExtendWith(InstantExecutorExtension::class)
class DubizzleViewModelTest {
    // system under test
    private var detailViewModel: DetailViewModel? = null
    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        detailViewModel = DetailViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    @Throws(Exception::class)
    fun observeEmptyDubizzleWhenNoteSet() {
        // Arrange
        val liveDataTestUtil = LiveDataTestUtil<Dubizzle?>()

        // Act
        val dubizzle = liveDataTestUtil.getValue(detailViewModel!!.dubizzleLiveData("1"))

        // Assert
        Assertions.assertNull(dubizzle)
    }
}