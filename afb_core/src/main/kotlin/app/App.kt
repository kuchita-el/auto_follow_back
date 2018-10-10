package app

import com.amazonaws.services.lambda.runtime.Context
import twitter4j.Twitter
import twitter4j.TwitterFactory

class App {
    public fun handler(context: Context) {
        val lambdaLogger = context.logger
        val followConditionText = "エンジニア"

        // search follower
        val twitter: Twitter = TwitterFactory.getSingleton()
        val followersList = twitter.getFollowersList(null, 1)

        //if not follow and contain engineer, follow back
        followersList
                .stream()
                .filter{t ->  !t.isFollowRequestSent || t.description.contains(followConditionText)}
                .peek{t -> twitter.createFriendship(t.id); lambdaLogger.log("follow " + t.screenName)}
    }
}