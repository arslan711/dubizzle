package com.dubizzle.assignment.repository
import androidx.test.core.app.ApplicationProvider;
import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.test.platform.app.InstrumentationRegistry
import com.dubizzle.assignment.repository.local.database.dao.DubizzleDAO
import com.dubizzle.assignment.repository.local.database.pojo.Dubizzle
import com.dubizzle.assignment.util.InstantExecutorExtension
import com.dubizzle.assignment.util.LiveDataTestUtil
import com.dubizzle.assignment.util.TestUtil
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


@ExtendWith(InstantExecutorExtension::class)
@RunWith(MockitoJUnitRunner::class)
class DubizzleRepositoryTest {



    // system under test
    private var dubizzleRepository: DubizzleRepository? = null
    private var dubizzleDAO: DubizzleDAO? = null




    @BeforeEach
    fun initEach() {
        dubizzleDAO = Mockito.mock(DubizzleDAO::class.java)
        dubizzleRepository = DubizzleRepository(Mockito.mock(Application::class.java))
    }

    @Throws(Exception::class)
    @Test
    fun insertDubizzle_returnRow() {

        // Arrange
        val insertedRow = 1L
        val returnedData = Single.just(insertedRow)
        Mockito.`when`(
            dubizzleDAO!!.insertDubizzle(
                ArgumentMatchers.any(
                    Dubizzle::class.java
                )
            )
        ).thenReturn(returnedData)

        // Act
        val returnedValue = dubizzleDAO!!.insertDubizzle(DUBIZZLE1)

        // Assert
        Mockito.verify(dubizzleDAO)!!.insertDubizzle(
            ArgumentMatchers.any(
                Dubizzle::class.java
            )
        )
        Mockito.verifyNoMoreInteractions(dubizzleDAO)
        Assertions.assertEquals(insertedRow, returnedValue.blockingGet())
    }


    @Throws(Exception::class)
    @Test
    fun insertDubizzle_returnFailure() {

        // Arrange
        val insertedRow = -1L
        val returnedData = Single.just(insertedRow)
        Mockito.`when`(
            dubizzleDAO!!.insertDubizzle(
                ArgumentMatchers.any(
                    Dubizzle::class.java
                )
            )
        ).thenReturn(returnedData)

        // Act
        val returnedValue = dubizzleDAO!!.insertDubizzle(DUBIZZLE1)

        // Assert
        Mockito.verify(dubizzleDAO)!!.insertDubizzle(
            ArgumentMatchers.any(
                Dubizzle::class.java
            )
        )
        Mockito.verifyNoMoreInteractions(dubizzleDAO)
        Assertions.assertEquals(insertedRow, returnedValue.blockingGet())
    }

    @Throws(Exception::class)
    @Test
    fun insertDubizzle_nullId_throwException()  {
        val exception = Assertions.assertThrows(Exception::class.java) {
            val dubizzle = TestUtil.TEST_DUBIZZLE_1
            dubizzle.id = null.toString()
            dubizzleDAO!!.insertDubizzle(dubizzle)
        }
        Assertions.assertEquals("Dubizzle id cannot be null", exception.message)
    }

    // Arrange
    @Throws(Exception::class)
    @Test
    fun dubizzle_returnListWithDubizzles()= runBlocking {


            // Arrange
            val notes = TestUtil.DUBIZZLE_LIST
            val liveDataTestUtil = LiveDataTestUtil<List<Dubizzle>>()
            val returnedData = MutableLiveData<List<Dubizzle>>()
            returnedData.setValue(notes)
            Mockito.`when`(dubizzleDAO!!.getAllDubizzles()).thenReturn(returnedData)

            // Act
            val observedData = liveDataTestUtil.getValue(dubizzleRepository!!.getDubizzlesLiveData())

            // Assert
            Assertions.assertEquals(notes, observedData)
        }

    // Arrange
    @Throws(Exception::class)
    @Test
    fun dubizzle_returnEmptyList()= runBlocking {


            val dubizzles: List<Dubizzle> = ArrayList()
            val liveDataTestUtil = LiveDataTestUtil<List<Dubizzle>>()
            val returnedData = MutableLiveData<List<Dubizzle>>()
            returnedData.setValue(dubizzles)
            Mockito.`when`(dubizzleDAO!!.getAllDubizzles()).thenReturn(returnedData)

            // Act
            val observedData = liveDataTestUtil.getValue(
                dubizzleRepository!!.getDubizzlesLiveData()
            )

            // Assert
            Assertions.assertEquals(dubizzles, observedData)
        }

    companion object {
        private val DUBIZZLE1 = TestUtil.TEST_DUBIZZLE_1
    }
}