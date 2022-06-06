package ai.shield.app.shieldaichallenge.data

import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNot.not
import org.hamcrest.number.OrderingComparison.greaterThan
import org.junit.Before
import org.junit.Test

class EpisodesJsonParsing {
    private var episodes: Episodes? = null

    @Before
    fun setUp() {
        this.episodes = Episodes.fromJson(sampleJsonInput)
    }

    @Test
    fun isNotNull() {
        assertThat(episodes, not(nullValue()))
        assertThat(episodes?.episodes, not(nullValue()))
    }

    @Test
    fun containsTwoEpisodes() {
        assertThat(episodes!!.episodes.count(), equalTo(2))
    }

    @Test
    fun containsValidId() {
        assertThat(episodes!!.episodes[0].id, greaterThan(0))
        assertThat(episodes!!.episodes[1].id, greaterThan(0))
    }

    @Test
    fun containsValidUrl() {
        assertThat(
            episodes!!.episodes[0].url.toString(),
            equalTo("http://www.tvmaze.com/episodes/4952/game-of-thrones-1x01-winter-is-coming")
        )
        assertThat(
            episodes!!.episodes[1].url.toString(),
            equalTo("http://www.tvmaze.com/episodes/4953/game-of-thrones-1x02-the-kingsroad")
        )
    }

    private val sampleJsonInput = "{ \"episodes\": [\n" +
            "        {\n" +
            "            \"id\": 4952,\n" +
            "            \"url\": \"http://www.tvmaze.com/episodes/4952/game-of-thrones-1x01-winter-is-coming\",\n" +
            "            \"name\": \"Winter is Coming\",\n" +
            "            \"season\": 1,\n" +
            "            \"number\": 1,\n" +
            "            \"airdate\": \"2011-04-17\",\n" +
            "            \"airtime\": \"21:00\",\n" +
            "            \"airstamp\": \"2011-04-18T01:00:00+00:00\",\n" +
            "            \"runtime\": 60,\n" +
            "            \"image\": {\n" +
            "                \"medium\": \"http://static.tvmaze.com/uploads/images/medium_landscape/1/2668.jpg\",\n" +
            "                \"original\": \"http://static.tvmaze.com/uploads/images/original_untouched/1/2668.jpg\"\n" +
            "            },\n" +
            "            \"summary\": \"<p>Lord Eddard Stark, ruler of the North, is summoned to court by his old friend, King Robert Baratheon, to serve as the King's Hand. Eddard reluctantly agrees after learning of a possible threat to the King's life. Eddard's bastard son Jon Snow must make a painful decision about his own future, while in the distant east Viserys Targaryen plots to reclaim his father's throne, usurped by Robert, by selling his sister in marriage.</p>\",\n" +
            "            \"_links\": {\n" +
            "                \"self\": {\n" +
            "                    \"href\": \"http://api.tvmaze.com/episodes/4952\"\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 4953,\n" +
            "            \"url\": \"http://www.tvmaze.com/episodes/4953/game-of-thrones-1x02-the-kingsroad\",\n" +
            "            \"name\": \"The Kingsroad\",\n" +
            "            \"season\": 1,\n" +
            "            \"number\": 2,\n" +
            "            \"airdate\": \"2011-04-24\",\n" +
            "            \"airtime\": \"21:00\",\n" +
            "            \"airstamp\": \"2011-04-25T01:00:00+00:00\",\n" +
            "            \"runtime\": 60,\n" +
            "            \"image\": {\n" +
            "                \"medium\": \"http://static.tvmaze.com/uploads/images/medium_landscape/1/2669.jpg\",\n" +
            "                \"original\": \"http://static.tvmaze.com/uploads/images/original_untouched/1/2669.jpg\"\n" +
            "            },\n" +
            "            \"summary\": \"<p>An incident on the Kingsroad threatens Eddard and Robert's friendship. Jon and Tyrion travel to the Wall, where they discover that the reality of the Night's Watch may not match the heroic image of it.</p>\",\n" +
            "            \"_links\": {\n" +
            "                \"self\": {\n" +
            "                    \"href\": \"http://api.tvmaze.com/episodes/4953\"\n" +
            "                }\n" +
            "            }\n" +
            "        }" +
            "     ]" +
            "   }"
}

