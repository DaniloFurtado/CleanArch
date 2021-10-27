package com.example.domain.usecase

import com.example.domain.executor.BaseSchedulerProvider
import com.example.domain.repository.PersonRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Before
import org.junit.Test

class ListUserUseCaseTest {
    private val schedulerTest = mockk<BaseSchedulerProvider>()
    private val repositoryPerson = mockk<PersonRepository>()
    private lateinit var listUserUseCase: ListUserUseCase

    @Before
    fun initTests() {
        listUserUseCase = ListUserUseCase(repositoryPerson, schedulerTest)
    }

    @Test
    fun `request return exception`() {
        `given request return exception`()
        val testObserver = `when request return data`()
        `then request return exception`(testObserver)
    }

    private fun `given request return exception`() {
        every { repositoryPerson.listPerson() } returns Single.error(Exception("Error"))
    }

    private fun `when request return data`() = listUserUseCase.buildUseCaseSingle(null).test()

    private fun <T> `then request return exception`(testObserver: TestObserver<T>) {
        testObserver.assertError(Exception::class.java)
        testObserver.assertNotComplete()
        testObserver.dispose()
    }

    @Test
    fun `request return success`() {
        `given request return success`()
        val testObserver = `when request return data`()
        `then request return success`(testObserver)
    }

    private fun `given request return success`() {
        every { repositoryPerson.listPerson() } returns mockk(relaxed = true)
    }

    private fun <T> `then request return success`(testObserver: TestObserver<T>) {
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.dispose()
    }
}
