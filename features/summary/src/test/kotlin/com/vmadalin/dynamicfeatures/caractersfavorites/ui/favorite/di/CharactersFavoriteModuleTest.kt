

package com.whatstoday.dynamicfeatures.caractersfavorites.ui.favorite.di

import androidx.lifecycle.ViewModel
import com.whatstoday.commons.ui.extensions.viewModel
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteRepository
import com.whatstoday.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteFragment
import com.whatstoday.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteViewModel
import com.whatstoday.dynamicfeatures.charactersfavorites.ui.favorite.di.CharactersFavoriteModule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CharactersFavoriteModuleTest {

    @MockK
    lateinit var fragment: CharactersFavoriteFragment
    lateinit var module: CharactersFavoriteModule

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeCharactersFavoriteModule_ShouldSetUpCorrectly() {
        module = CharactersFavoriteModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedCharactersFavoriteViewModel() {
        mockkStatic("com.whatstoday.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<CharactersFavoriteViewModel>()

        val factoryCaptor = slot<() -> CharactersFavoriteViewModel>()
        val favoriteRepository = mockk<CharacterFavoriteRepository>(relaxed = true)
        module = CharactersFavoriteModule(fragment)
        module.providesCharactersFavoriteViewModel(favoriteRepository)

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        assertEquals(favoriteRepository, factoryCaptor.captured().characterFavoriteRepository)
    }

    @Test
    fun verifyProvidedCharactersFavoriteAdapter() {
        module = CharactersFavoriteModule(fragment)

        assertNotNull(module.providesCharactersFavoriteAdapter())
    }
}
