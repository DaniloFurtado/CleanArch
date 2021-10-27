package com.example.cleanarch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.model.Person
import com.example.domain.usecase.ListUserUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeoutException
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class PersonViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val captorError = slot<(Throwable) -> Unit>()
    private val captorShowLoading = slot<() -> Unit>()
    private val captorHideLoading = slot<() -> Unit>()
    private val captorResultList = slot<(List<Person>) -> Unit>()
    private val listUserUseCase = mockk<ListUserUseCase>()
    private lateinit var viewModel: PersonViewModel

    @Before
    fun initTest() {
        viewModel = PersonViewModel(listUserUseCase)
    }

    @Test
    fun `request list person exception`() {
        `given request list person exception`(Exception("Exception Crazy"))
        `when request list person`()
        `then request list person exception`()
    }

    private fun `given request list person exception`(throwable: Throwable) {
        every {
            listUserUseCase
                .execute(
                    null,
                    any(),
                    capture(captorError),
                    any(),
                    any()
                )
        } answers {
            captorError.captured.invoke(throwable)
        }
    }

    private fun `when request list person`() {
        viewModel.onInitViewModel()
    }

    private fun `then request list person exception`() {
        `verify list execute once`()
        assertNotNull(viewModel.actionError.value)
        assertEquals("Exception Crazy", viewModel.actionError.value!!.message)
    }

    @Test
    fun `request list person time out exception`() {
        `given request list person exception`(TimeoutException("Time Expired"))
        `when request list person`()
        `then request list person time out exception`()
    }

    private fun `then request list person time out exception`() {
        `verify list execute once`()
        assertNotNull(viewModel.actionError.value)
        assertEquals("Time Expired", viewModel.actionError.value!!.message)
        assertTrue {
            viewModel.actionError.value!! is TimeoutException
        }
    }

    @Test
    fun `request list person successful`() {
        `given request list person successful`()
        `when request list person`()
        `then request list person successful`()
    }

    private fun `given request list person successful`() {
        every {
            listUserUseCase
                .execute(
                    null,
                    capture(captorResultList),
                    any(),
                    any(),
                    any()
                )
        } answers {
            captorResultList.captured.invoke(dummyListPerson())
        }
    }

    private fun `then request list person successful`() {
        `verify list execute once`()
        assertNotNull(viewModel.liveDataListUsers.value)
        assertEquals(2, viewModel.liveDataListUsers.value!!.size)
    }

    @Test
    fun `request list person control load`() {
        `given request list person control load`(true)
        `when request list person`()
        `then request list person control load true`()
        `given request list person control load`(false)
        `when request list person`()
        `then request list person control load false`()
    }

    private fun `given request list person control load`(showLoad: Boolean) {
        every {
            listUserUseCase
                .execute(
                    null,
                    any(),
                    any(),
                    capture(captorShowLoading),
                    capture(captorHideLoading)
                )
        } answers {
            when (showLoad) {
                true -> captorShowLoading.captured.invoke()
                else -> captorHideLoading.captured.invoke()
            }
        }
    }

    private fun `then request list person control load true`() {
        assertNotNull(viewModel.loading.value)
        assertTrue(viewModel.loading.value!!)
    }

    private fun `then request list person control load false`() {
        assertNotNull(viewModel.loading.value)
        assertFalse(viewModel.loading.value!!)
    }

    private fun `verify list execute once`() {
        verify(exactly = 1) {
            listUserUseCase
                .execute(null, any(), any(), any(), any())
        }
    }
}
