package app.supersslc.search.algorithms.engine.provider.web

import android.content.Context
import app.supersslc.preferences.PreferenceManager
import app.supersslc.preferences2.PreferenceManager2
import app.supersslc.search.algorithms.engine.SearchProvider
import app.supersslc.search.algorithms.engine.SearchResult
import com.patrykmichalik.opto.core.firstBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take

object WebSuggestionProvider : SearchProvider {
    override val id = "web_suggestions"

    override fun search(
        context: Context,
        query: String,
    ): Flow<List<SearchResult>> {
        val prefs = PreferenceManager.getInstance(context)
        val prefs2 = PreferenceManager2.getInstance(context)

        if (query.isBlank() || !prefs.searchResultStartPageSuggestion.get()) {
            return flow { emit(emptyList()) }
        }

        val provider = prefs2.webSuggestionProvider.firstBlocking()
        val maxResults = prefs2.maxWebSuggestionResultCount.firstBlocking()

        val webProvider = provider
            .configure(context)

        return webProvider.getSuggestions(query)
            .map { suggestions ->
                suggestions
                    .take(maxResults)
                    .map { suggestion ->
                        SearchResult.WebSuggestion(suggestion = suggestion, provider = webProvider.id)
                    }
            }
    }
}
