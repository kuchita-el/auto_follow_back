package app

import com.amazonaws.services.lambda.runtime.Context
import twitter4j.Twitter
import twitter4j.TwitterFactory

class App {
    public fun handler(context: Context) {
        val lambdaLogger = context.logger
        val followConditionText = "エンジニア"

        // search followerId
        val twitter: Twitter = TwitterFactory.getSingleton()
        val followersIDs = twitter.getFollowersIDs(-1)
        val friendsIDs = twitter.getFriendsIDs(-1)
        lambdaLogger.log("followersCount: ${followersIDs.iDs.size}, friendsCount: ${friendsIDs.iDs.size}")

        val oneWayFriendIds = followersIDs.iDs.filterNot { e -> friendsIDs.iDs.contains(e) }.toLongArray()
        val oneWayFriends = twitter.lookupUsers(*oneWayFriendIds)
        lambdaLogger.log("oneWayFriendsCount: ${oneWayFriends.size}")

        //if not send follow request and contains keyword
        oneWayFriends
                .stream()
                .filter{f -> !f.isFollowRequestSent && f.description.contains(followConditionText)}
                .peek{t -> twitter.createFriendship(t.id); lambdaLogger.log("follow " + t.screenName)}
    }
}