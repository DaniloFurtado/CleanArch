package com.example.domain.usecase

import com.example.domain.executor.BaseSchedulerProvider
import com.example.domain.repository.PersonRepository
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import kotlin.test.BeforeTest
import kotlin.test.Test

internal class ListUserUseCaseTest {

    private val repositoryMock = mockk<PersonRepository>()
    private val scheduler = mockk<BaseSchedulerProvider>()
    private lateinit var useCase: ListUserUseCase

    @BeforeTest
    fun `init test`() {
        useCase = ListUserUseCase(
            repositoryMock,
            scheduler
        )
    }

    @Test
    fun `should calc right`() {
        val value = 2 + 5
        assertEquals(7, value)
    }
}
